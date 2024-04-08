package group.msg.at.cloud.cloudtrain.loadtest;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class TaskLifecycleSimulation extends Simulation {

    Configuration config = Configuration.load();

    TaskBodyFactory factory = new TaskBodyFactory();

    ChainBuilder fetchToken = exec(
            http("Authorize")
                    .post(config.getAuthTokenEndpointUrl())
                    .ignoreProtocolHeaders()
                    .ignoreProtocolChecks()
                    .header("Accept", "*/*")
                    .header("Accept-Encoding", "gzip,deflate,br")
                    .header("Connection", "keep-alive")
                    .formParam("scope", "openid microprofile-jwt")
                    .formParam("grant_type", "password")
                    .formParam("username", config.getAuthUsername())
                    .formParam("password", config.getAuthPassword())
                    .formParam("client_id", config.getAuthClientId())
                    .formParam("client_secret", config.getAuthClientSecret())
                    .asFormUrlEncoded()
                    .check(status().is(200))
                    .check(jsonPath("$.access_token").saveAs("accessToken"))
                    .check(jsonPath("$.id_token").saveAs("idToken"))
    );
    ChainBuilder addTask = exec(
            http("AddTask")
                    .post("/api/v1/tasks")
                    .header("Authorization", "Bearer #{accessToken}")
                    .body(StringBody(factory.createTaskJsonString()))
                    .asJson()
                    .check(status().is(201))
                    .check(header("Location").exists())
                    .check(header("Location").find().transform(factory::extractTaskIdFromLocation).saveAs("taskId"))
    );
    ChainBuilder getTask = exec(
            http("GetTask")
                    .get("/api/v1/tasks/#{taskId}")
                    .header("Authorization", "Bearer #{accessToken}")
                    .asJson()
                    .check(status().is(200))
    );
    ChainBuilder deleteTask = exec(
            http("DeleteTask")
                    .delete("/api/v1/tasks/#{taskId}")
                    .header("Authorization", "Bearer #{accessToken}")
                    .asJson()
                    .check(status().is(204))
    );
    HttpProtocolBuilder httpProtocol =
            http.baseUrl(config.getApplicationEndpointUrl())
                    .acceptHeader("application/json")
                    .acceptLanguageHeader("en-US,en;q=0.5")
                    .acceptEncodingHeader("gzip, deflate")
                    .userAgentHeader(
                            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0"
                    );
    ScenarioBuilder users = scenario("Users").exec(fetchToken, addTask, getTask, deleteTask);

    {
        setUp(
                users.injectOpen(rampUsers(config.getSimulationRampUpUsers()).during(config.getSimulationRampDuration().toSeconds()))
        ).protocols(httpProtocol);
    }
}

# cnj-cloudtrain-loadtest

Provides a gatling test suite which can be run on any given CloudTrain showcase application.

This gatling test suite is mainly intended to generate some load on CloudTrain showcase applications to demonstrate
logging, monitoring and tracing. 

Currently, the gatling test suite only contains one simulation [TaskLifecycleSimulation](src/test/java/group/msg/at/cloud/cloudtrain/loadtest/TaskLifecycleSimulation.java) which:
* authenticates on Keycloak
* creates a new task
* gets the newly created task
* deletes the newly created task


## Status

![Build status]()

## Release information

Check [changelog](changelog.md) for latest version and release information.

## Supported configuration values

The following environment variable are supported to control the behaviour of the gatling simulation:

| ENVVAR NAME | ENVVAR TYPE    | Required | Example | Description                                                                                                          |
| --- |----------------|----------| --- |----------------------------------------------------------------------------------------------------------------------|
| CNJ_AUTH_TOKEN_ENDPOINT_URL | string         | x        | https://oidc.cloudtrain.aws.msgoat.eu/realms/cloudtrain/protocol/openid-connect/token | Token endpoint URL of the OpenId Connect Provider                                                                    |
| CNJ_AUTH_USER_NAME | string         | x        | cnj-tester | Name of the test user                                                                                                |
| CNJ_AUTH_PASSWORD | string         | x        |  | Password of the test user                                                                                            |
| CNJ_AUTH_CLIENT_ID | string         | x        | cnj-rest-assured | Client ID of the registered CloudTrain application                                                                   |
| CNJ_AUTH_CLIENT_SECRET | string         | x        | | Client secret of the registered CloudTrain application                                                               |
| CNJ_APPLICATION_ENDPOINT_URL | string         | x        | https://train2024-dev.k8s.cloudtrain.aws.msgoat.eu/cloudtrain/cnj-tracing-backend-spring | REST endpoint URL of the application under test                                                                      |
| CNJ_SIMULATION_RAMPUP_USERS | int            |          | 50 | Number of users the simulation should ramp up; default: `50`                                                         |
| CNJ_SIMULATION_RAMPUP_DURATION | duration |          | PT60S | Duration of the simulation ramp up in ISO-8601 format (i.e. "PT60S" for 60 seconds); default: `PTS60` for 60 seconds |

## HOW-TO run the simulation locally

### Step 1: Export configuration via envvars

Set all required configuration values via environment variables.

### Step 2: Run Maven build

Run a Maven build using the following command:

````shell
mvn clean verify
````

### Step 3: Check the Gatling report

A Gatling HTML report will be stored at `target/gatling/tasklifecyclesimulation-*/index.html`.

## TODO's

* clean up sources
* split build and run phase into two different maven invocations 
* add AWS CodeBuild build project which builds the gatling simulation executable
* add parameterizable AWS CodeBuild build project which runs the gatling simulation executable
* add more complex simulations

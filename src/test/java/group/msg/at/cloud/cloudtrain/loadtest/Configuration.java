package group.msg.at.cloud.cloudtrain.loadtest;

import java.time.Duration;

/**
 * Configuration class for all simulations which pulls configuration properties from the environment.
 */
final class Configuration {

    public static final String CNJ_AUTH_TOKEN_ENDPOINT_URL_EVNAME = "CNJ_AUTH_TOKEN_ENDPOINT_URL";
    public static final String CNJ_AUTH_USERNAME_EVNAME = "CNJ_AUTH_USER_NAME";
    public static final String CNJ_AUTH_PASSWORD_EVNAME = "CNJ_AUTH_PASSWORD";
    public static final String CNJ_AUTH_CLIENT_ID_EVNAME = "CNJ_AUTH_CLIENT_ID";
    public static final String CNJ_AUTH_CLIENT_SECRET_EVNAME = "CNJ_AUTH_CLIENT_SECRET";
    public static final String CNJ_APPLICATION_ENDPOINT_URL_EVNAME = "CNJ_APPLICATION_ENDPOINT_URL";
    public static final String CNJ_SIMULATION_RAMPUP_USERS_EVNAME = "CNJ_SIMULATION_RAMPUP_USERS";
    public static final String CNJ_SIMULATION_RAMPUP_DURATION_EVNAME = "CNJ_SIMULATION_RAMPUP_DURATION";
    public static final String CNJ_AUTH_TOKEN_ENDPOINT_URL_SPNAME = "cnj.auth.tokenEndpointUrl";
    public static final String CNJ_AUTH_USERNAME_SPNAME = "cnj.auth.username";
    public static final String CNJ_AUTH_PASSWORD_SPNAME = "cnj.auth.password";
    public static final String CNJ_AUTH_CLIENT_ID_SPNAME = "cnj.auth.clientId";
    public static final String CNJ_AUTH_CLIENT_SECRET_SPNAME = "cnj.auth.clientSecret";
    public static final String CNJ_APPLICATION_ENDPOINT_URL_SPNAME = "cnj.application.endpointUrl";
    public static final String CNJ_SIMULATION_RAMPUP_USERS_SPNAME = "cnj.simulation.rampUpUsers";
    public static final String CNJ_SIMULATION_RAMPUP_DURATION_SPNAME = "cnj.simulation.rampUpDuration";
    private String authTokenEndpointUrl;
    private String authUsername;
    private String authPassword;
    private String authClientId;
    private String authClientSecret;
    private String applicationEndpointUrl;
    private int simulationRampUpUsers = 50;
    private Duration simulationRampUpDuration = Duration.ofSeconds(60);

    public static Configuration load() {
        Configuration result = new Configuration();
        result.authTokenEndpointUrl = loadStringValue(CNJ_AUTH_TOKEN_ENDPOINT_URL_EVNAME, result.authTokenEndpointUrl);
        result.authUsername = loadStringValue(CNJ_AUTH_USERNAME_EVNAME, result.authUsername);
        result.authPassword = loadStringValue(CNJ_AUTH_PASSWORD_EVNAME, result.authPassword);
        result.authClientId = loadStringValue(CNJ_AUTH_CLIENT_ID_EVNAME, result.authClientId);
        result.authClientSecret = loadStringValue(CNJ_AUTH_CLIENT_SECRET_EVNAME, result.authClientSecret);
        result.applicationEndpointUrl = loadStringValue(CNJ_APPLICATION_ENDPOINT_URL_EVNAME, result.applicationEndpointUrl);
        result.simulationRampUpUsers = loadIntegerValue(CNJ_SIMULATION_RAMPUP_USERS_EVNAME, result.simulationRampUpUsers);
        result.simulationRampUpDuration = loadDurationValue(CNJ_SIMULATION_RAMPUP_DURATION_EVNAME, result.simulationRampUpDuration);
        return result;
    }

    private static String loadStringValue(String envVarName, String defaultValue) {
        String result = System.getenv(envVarName);
        if (result == null) {
            result = defaultValue;
        }
        return result;
    }

    private static int loadIntegerValue(String envVarName, int defaultValue) {
        String value = loadStringValue(envVarName, null);
        return value != null ? Integer.parseInt(value) : defaultValue;
    }

    private static Duration loadDurationValue(String envVarName, Duration defaultValue) {
        String value = loadStringValue(envVarName, null);
        return value != null ? Duration.parse(value) : defaultValue;
    }

    public String getAuthTokenEndpointUrl() {
        return authTokenEndpointUrl;
    }

    public String getAuthUsername() {
        return authUsername;
    }

    public String getAuthPassword() {
        return authPassword;
    }

    public String getAuthClientId() {
        return authClientId;
    }

    public String getAuthClientSecret() {
        return authClientSecret;
    }

    public String getApplicationEndpointUrl() {
        return applicationEndpointUrl;
    }

    public int getSimulationRampUpUsers() {
        return simulationRampUpUsers;
    }

    public Duration getSimulationRampDuration() {
        return simulationRampUpDuration;
    }

    private Configuration() {}
}

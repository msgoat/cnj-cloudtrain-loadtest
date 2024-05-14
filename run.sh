#!/bin/bash

export CNJ_AUTH_TOKEN_ENDPOINT_URL=https://oidc.cloudtrain.aws.msgoat.eu/realms/cloudtrain/protocol/openid-connect/token
export CNJ_AUTH_USER_NAME=cnj-tester
export CNJ_AUTH_PASSWORD=FILLME
export CNJ_AUTH_CLIENT_ID=cnj-rest-assured
export CNJ_AUTH_CLIENT_SECRET=FILLME
export CNJ_APPLICATION_ENDPOINT_URL=https://train2024-dev.k8s.cloudtrain.aws.msgoat.eu/cloudtrain/cnj-tracing-backend-spring
export CNJ_SIMULATION_RAMPUP_USERS=50
export CNJ_SIMULATION_RAMPUP_DURATION=PT60s


mvn clean verify


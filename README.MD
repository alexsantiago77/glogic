# Challenge GlobalLogic

This microservice in Java SpringBoot 2.5.14 was built as a challenge to GlobalLogic.
It's is a Sign-up/Login auth service.

## Requirements

- Java 8 or higher
- Gradle 6.0 o higher

## Dependencies
- Spring web
- Spring security (to build JWT)
- Mockito (test)
- lombok
- modelmapper

## Setup

### Step 1: build

```bash
./gradlew clean build
```
### Step 2: run
The service will be available at http://localhost:8090

## Considerations

The AuthorizationManager, TokenStorage, or any persistence in SpringSecurity wasn't used by simplicity.
Was used logic of user "lastLogin" date persisted in H2 and enrich JWT claim "created" allowed in standard OpenAPI 3.0 Instead.


## API access

## Curl example  '/sign-up'
```bash
curl --location 'localhost:8090/sign-up' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Freddy",
"email": "12@gmaidlaxss.jcm",
"password": "a2asfGfdfdf4",
"phones": [
{
"number": 23423423,
"cityCode": 12,
"countryCode": "CL"
}
]
}'
```

## Curl example  '/login'
```bash
curl --location 'localhost:8090/login' \
--header 'Authorization: Bearer [TOKEN_GENERATED_IN_SIGN_UP]' \
```

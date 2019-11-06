import org.springframework.cloud.contract.spec.Contract
[
    Contract.make {
        name("Persist and return activationCode")
        request {
            method POST()
            url "/api/account/activationCode"
            headers {
                accept applicationJson()
                header 'id': 5
            }
        }
        response {
            status CREATED()
            headers {
                contentType(applicationJson())
            }
            body(
                id: anyPositiveInt(),
                accountId: fromRequest().header("id"),
                activationCode: anyUuid(),
                applyTime: anyNonEmptyString()
            )
        }
    },
    Contract.make {
        name("Account not found during activation code creation")
        request {
            method POST()
            url "/api/account/activationCode"
            headers {
                accept applicationJson()
                header 'id': 1000
            }
        }
        response {
            status NOT_FOUND()
            headers {
                contentType(applicationJson())
            }
            body(
                "timestamp": value(regex("2[0-9]{3}-(1[0-2]|0[1-9])-(0[1-9]|1[0-9]|2[0-8])T([0-1][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]\\.[0-9]{1,6}Z")),
                "status": 404,
                "statusName": "NOT_FOUND",
                "message": "Account not found. id: 1000"
            )
        }
    }
]

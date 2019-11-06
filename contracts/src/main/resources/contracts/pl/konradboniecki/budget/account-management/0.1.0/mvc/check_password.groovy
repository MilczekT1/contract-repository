import org.springframework.cloud.contract.spec.Contract
[
    Contract.make {
        name("Return 200 if credentials are ok")
        request {
            method GET()
            url("/api/account/credentials")
            headers {
                accept applicationJson()
                header("accountId", 5)
                header("password", "correctHashValue")
            }
        }
        response {
            status OK()
        }
    },
    Contract.make {
        name("Return 400 if credentials are not ok")
        request {
            method GET()
            url("/api/account/credentials")
            headers {
                accept applicationJson()
                header("accountId", 5)
                header("password", "incorrectHashValue")
            }
        }
        response {
            status BAD_REQUEST()
        }
    },
    Contract.make {
        name("Return 404 if account not found")
        request {
            method GET()
            url("/api/account/credentials")
            headers {
                accept applicationJson()
                header("accountId", 4)
                header("password", "notImportantHashValue")
            }
        }
        response {
            status NOT_FOUND()
            headers {
                contentType applicationJson()
            }
            body(
                "timestamp": value(regex("2[0-9]{3}-(1[0-2]|0[1-9])-(0[1-9]|1[0-9]|2[0-8])T([0-1][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]\\.[0-9]{1,6}Z")),
                "status": 404,
                "statusName": "NOT_FOUND",
                "message": "Account with id: 4 not found during credentials check."
            )
        }
    }
]

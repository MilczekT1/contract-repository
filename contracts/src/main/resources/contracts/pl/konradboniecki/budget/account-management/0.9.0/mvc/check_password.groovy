import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("check_password__return200IfCredentialsAreOk")
		request {
			method GET()
			url("/api/account-mgt/v1/accounts/b6e13d85-6c2e-4635-8f76-8b36a9184c86/credentials")
			headers {
				accept applicationJson()
				header("password", "correctHashValue")
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
		}
		response {
			status OK()
		}
	},
	Contract.make {
		name("check_password__return400IfCredentialsAreNotOk")
		request {
			method GET()
			url("/api/account-mgt/v1/accounts/b6e13d85-6c2e-4635-8f76-8b36a9184c86/credentials")
			headers {
				accept applicationJson()
				header("password", "incorrectHashValue")
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
		}
		response {
			status BAD_REQUEST()
		}
	},
	Contract.make {
		name("check_password__return404IfAccountNotFound")
		request {
			method GET()
			url("/api/account-mgt/v1/accounts/bbd30a00-8d3f-4c61-8368-a10038ed2637/credentials")
			headers {
				accept applicationJson()
				header("password", "notImportantHashValue")
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
		}
		response {
			status NOT_FOUND()
			headers {
				contentType applicationJson()
			}
			body(
				"timestamp": value(regex("([0-9]{4})-(1[0-2]|0[1-9])-(3[01]|0[1-9]|[12][0-9])T(2[0-3]|[01][0-9]):([0-5][0-9]):([0-5][0-9])(\\.\\d{1,9}Z)")),
				"status": 404,
				"statusName": "NOT_FOUND",
				"message": "Account with id: bbd30a00-8d3f-4c61-8368-a10038ed2637 not found during credentials check."
			)
		}
	}
]

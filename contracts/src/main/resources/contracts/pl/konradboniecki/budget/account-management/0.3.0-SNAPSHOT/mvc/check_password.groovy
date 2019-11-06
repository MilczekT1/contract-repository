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
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
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
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
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
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
		}
		response {
			status NOT_FOUND()
			headers {
				contentType applicationJson()
			}
			body(
				"timestamp": value(regex("([0-9]{4})-(1[0-2]|0[1-9])-(3[01]|0[1-9]|[12][0-9])T(2[0-3]|[01][0-9]):([0-5][0-9]):([0-5][0-9])(\\.\\d{6}Z)")),
				"status": 404,
				"statusName": "NOT_FOUND",
				"message": "Account with id: 4 not found during credentials check."
			)
		}
	}
]

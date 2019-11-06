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
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
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
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
		}
		response {
			status NOT_FOUND()
			headers {
				contentType(applicationJson())
			}
			body(
				"timestamp": value(regex("([0-9]{4})-(1[0-2]|0[1-9])-(3[01]|0[1-9]|[12][0-9])T(2[0-3]|[01][0-9]):([0-5][0-9]):([0-5][0-9])(\\.\\d{6}Z)")),
				"status": 404,
				"statusName": "NOT_FOUND",
				"message": "Account not found. id: 1000"
			)
		}
	}
]

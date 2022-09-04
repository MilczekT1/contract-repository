import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("create_activation_code__persistAndReturnActivationCode")
		request {
			method POST()
			url "/api/account-mgt/v1/accounts/246b0ae2-d943-4d1a-a418-fdadfcb80455/activation-codes"
			headers {
				accept applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
		}
		response {
			status CREATED()
			headers {
				contentType(applicationJson())
			}
			body(
				id: anyUuid(),
				accountId: fromRequest().path(4),
				activationCodeValue: anyUuid(),
				created: anyNonEmptyString()
			)
		}
	},
	Contract.make {
		name("create_activation_code__accountNotFoundDuringActivationCodeCreation")
		request {
			method POST()
			url "/api/account-mgt/v1/accounts/af138a5a-365c-4708-a0b9-0df76bd6b754/activation-codes"
			headers {
				accept applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
		}
		response {
			status NOT_FOUND()
			headers {
				contentType(applicationJson())
			}
			body(
				"timestamp": value(regex("([0-9]{4})-(1[0-2]|0[1-9])-(3[01]|0[1-9]|[12][0-9])T(2[0-3]|[01][0-9]):([0-5][0-9]):([0-5][0-9])(\\.\\d{1,9}Z)")),
				"status": 404,
				"statusName": "NOT_FOUND",
				"message": "Account not found. id: af138a5a-365c-4708-a0b9-0df76bd6b754"
			)
		}
	}
]

import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("find_account_by_email__shouldReturn200WhenEmailFound")
		request {
			url("/api/account-mgt/v1/accounts/existing_email@find-by-mail.com") {
				queryParameters {
					parameter("findBy", "email")
				}
			}
			method GET()
			headers {
				accept applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
		}
		response {
			status OK()
			body(
				"id": "178a715a-4d54-403d-b559-def430dd8a5b",
				"familyId": value(anyUuid()),
				"firstName": "testFirstName",
				"lastName": "testLastName",
				"email": fromRequest().path(4),
				"enabled": true
			)
			headers {
				contentType applicationJson()
			}
		}
	},
	Contract.make {
		name("find_account_by_email__shouldReturn404WhenEmailNotFound")
		request {
			url("/api/account-mgt/v1/accounts/not_existing_email@mail.com") {
				queryParameters {
					parameter("findBy", "email")
				}
			}
			method GET()
			headers {
				accept applicationJson()
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
				"message": "Account with email: not_existing_email@mail.com not found."
			)
		}
	}
]

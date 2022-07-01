import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("find_account_by_email__shouldReturn200WhenEmailFound")
		request {
			url("/api/account-mgt/v1/accounts/existing_email@password-management.com") {
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
				"id": "d19391f3-66e0-434c-ba2b-01d64cf37a95",
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
			url("/api/account-mgt/v1/accounts/not_existing_email@password-management.com") {
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
				"timestamp": value(regex("([0-9]{4})-(1[0-2]|0[1-9])-(3[01]|0[1-9]|[12][0-9])T(2[0-3]|[01][0-9]):([0-5][0-9]):([0-5][0-9])(\\.\\d{6}Z)")),
				"status": 404,
				"statusName": "NOT_FOUND",
				"message": "Account with email: not_existing_email@password-management.com not found."
			)
		}
	}
]

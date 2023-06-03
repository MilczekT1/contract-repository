import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("find_account_by_id__shouldReturn200WhenAccountFoundByIdWithParam")
		request {
			url("/api/account-mgt/v1/accounts/ead1f1a2-d178-4204-9ec1-b78c5bf6402c") {
				queryParameters {
					parameter("findBy", "id")
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
				"id": fromRequest().path(4),
				"familyId": value(anyUuid()),
				"firstName": "testFirstName",
				"lastName": "testLastName",
				"email": "existing_email@mail.com",
				"enabled": true
			)
			headers {
				contentType applicationJson()
			}
		}
	},
	Contract.make {
		name("find_account_by_id__shouldReturn404WhenAccountNotFoundById")
		request {
			method GET()
			url("/api/account-mgt/v1/accounts/cc2871a2-e6b1-4490-8840-9d50502074b0") {
				queryParameters {
					parameter("findBy", "id")
				}
			}
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
				"message": "Account with id: cc2871a2-e6b1-4490-8840-9d50502074b0 not found."
			)
		}
	}
]

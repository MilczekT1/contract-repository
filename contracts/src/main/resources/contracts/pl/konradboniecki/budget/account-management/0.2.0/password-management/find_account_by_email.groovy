import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("password_management_shouldReturn200WhenEmailFound")
		request {
			url("/api/account/existing_email@password_management.com") {
				queryParameters {
					parameter("findBy", "email")
				}
			}
			method GET()
			headers {
				accept applicationJson()
			}
		}
		response {
			status OK()
			body(
				"id": 1,
				"familyId": 1,
				"firstName": "testFirstName",
				"lastName": "testLastName",
				"email": fromRequest().path(2),
				"role": "USER",
				"enabled": true,
				"budgetGranted": value(consumer(true), producer(anyBoolean())),
				"horseeGranted": value(consumer(false), producer(anyBoolean()))
			)
			headers {
				contentType applicationJson()
			}
		}
	},
	Contract.make {
		name("password_management_shouldReturn404WhenEmailNotFound")
		request {
			url("/api/account/not_existing_email@password_management.com") {
				queryParameters {
					parameter("findBy", "email")
				}
			}
			method GET()
			headers {
				accept applicationJson()
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
				"message": "Account with email: not_existing_email@password_management.com not found."
			)
		}
	}
]

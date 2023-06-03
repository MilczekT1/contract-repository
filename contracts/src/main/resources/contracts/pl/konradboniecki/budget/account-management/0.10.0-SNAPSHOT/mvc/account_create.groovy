import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("account_create__return201IfAccountCreated")
		request {
			method POST()
			url("/api/account-mgt/v1/accounts")
			headers {
				accept applicationJson()
				contentType applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
			body(
				firstName: value(producer("testFirstName"), consumer(anyNonBlankString())),
				lastName: value(producer("testLastName"), consumer(anyNonBlankString())),
				email: "not_existing_email@mail.com",
				password: value(anyAlphaNumeric())
			)
		}
		response {
			status CREATED()
			headers {
				contentType applicationJson()
			}
			body(
				id: value(producer("bede3ade-a66d-47d4-9853-34e74287a725"), consumer(anyUuid())),
				firstName: fromRequest().body("firstName"),
				lastName: fromRequest().body("lastName"),
				email: value(fromRequest().body("email")),
				enabled: false
			)
		}
	},
	Contract.make {
		name("account_create__return409IfConflict")
		request {
			method POST()
			url("/api/account-mgt/v1/accounts")
			headers {
				accept applicationJson()
				contentType applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
			body(
				firstName: value(producer("testFirstName"), consumer(anyNonBlankString())),
				lastName: value(producer("testLastName"), consumer(anyNonBlankString())),
				email: "existing_email@mail.com",
				password: value(anyAlphaNumeric())
			)
		}
		response {
			status CONFLICT()
		}
	}
]

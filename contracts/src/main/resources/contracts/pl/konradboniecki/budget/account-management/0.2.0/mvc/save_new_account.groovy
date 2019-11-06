import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		request {
			method POST()
			url("/api/account")
			headers {
				accept applicationJson()
				contentType applicationJson()
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
				id: value(producer(2L), consumer(anyPositiveInt())),
				familyId: null,
				firstName: fromRequest().body("firstName"),
				lastName: fromRequest().body("lastName"),
				email: value(fromRequest().body("email")),
				role: "USER",
				enabled: false,
				password: null,
				budgetGranted: value(consumer(true), producer(anyBoolean())),
				horseeGranted: value(consumer(true), producer(anyBoolean()))
			)
		}
	},
	Contract.make {
		request {
			method POST()
			url("/api/account")
			headers {
				accept applicationJson()
				contentType applicationJson()
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

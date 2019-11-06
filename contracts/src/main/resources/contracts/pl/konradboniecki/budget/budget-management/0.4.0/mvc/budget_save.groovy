package contracts.budget.save

import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("budget_save_shouldReturn201WithBody")
		request {
			method POST()
			url("/api/budgets")
			headers {
				accept applicationJson()
				contentType applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
			body(
				familyId: 6L,
				maxJars: 6L
			)
		}
		response {
			status CREATED()
			headers {
				contentType applicationJson()
			}
			body(
				id: value(producer(5L), consumer(anyPositiveInt())),
				familyId: value(producer(6L), consumer(fromRequest().body("familyId"))),
				maxJars: value(fromRequest().body("maxJars"))
			)
		}
	},
	Contract.make {
		name("budget_save_shouldReturn500IfFailed")
		request {
			method POST()
			url("/api/budgets")
			headers {
				accept applicationJson()
				contentType applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
			body(
				familyId: 8L,
				maxJars: 8L
			)
		}
		response {
			status INTERNAL_SERVER_ERROR()
			headers {
				contentType applicationJson()
			}
			body(
				"timestamp": value(regex("([0-9]{4})-(1[0-2]|0[1-9])-(3[01]|0[1-9]|[12][0-9])T(2[0-3]|[01][0-9]):([0-5][0-9]):([0-5][0-9])(\\.\\d{6}Z)")),
				"status": 500,
				"statusName": "INTERNAL_SERVER_ERROR",
				"message": value("Unexpected error occurred.")
			)
		}
	}
]

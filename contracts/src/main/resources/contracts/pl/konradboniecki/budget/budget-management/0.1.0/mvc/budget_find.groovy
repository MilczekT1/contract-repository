package contracts.budget.find

import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("budget_find_shouldReturn200WhenFound")
		request {
			method GET()
			url("/api/budgets/1") {
				queryParameters {
					parameter("idType", "family")
				}
			}
			headers {
				accept applicationJson()
			}
		}
		response {
			status OK()
			headers {
				contentType applicationJson()
			}
			body(
				id: value(producer(2L), consumer(anyPositiveInt())),
				familyId: fromRequest().path(2),
				maxJars: 6L
			)
		}
	},
	Contract.make {
		request {
			name("budget_find_shouldReturn404WhenNotFound")
			method GET()
			url("/api/budgets/100") {
				queryParameters {
					parameter("idType", "family")
				}
			}
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
				"timestamp": value(regex("2[0-9]{3}-(1[0-2]|0[1-9])-(0[1-9]|1[0-9]|2[0-8])T([0-1][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]\\.[0-9]{1,6}Z")),
				"status": 404,
				"statusName": "NOT_FOUND",
				"message": "Budget not found for family with id: 100"
			)
		}
	}
]

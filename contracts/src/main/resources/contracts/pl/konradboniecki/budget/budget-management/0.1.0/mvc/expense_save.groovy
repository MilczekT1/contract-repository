package contracts.jar.create

import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		request {
			name("expense_save_shouldReturn201WithExpenseInBodyWhenSuccess")
			method POST()
			url("/api/budgets/1/expenses")
			headers {
				accept applicationJson()
				contentType applicationJson()
			}
			body(
				budgetId: 1L,
				amount: value(producer(5L), consumer(anyPositiveInt())),
				comment: value(producer("comment"), consumer(anyNonBlankString()))
			)
		}
		response {
			status CREATED()
			headers {
				contentType applicationJson()
			}
			body(
				id: value(producer(1L), consumer(anyPositiveInt())),
				budgetId: fromRequest().body("budgetId"),
				amount: fromRequest().body("amount"),
				comment: fromRequest().body("comment"),
				created: regex("2[0-9]{3}-(1[0-2]|0[1-9])-(0[1-9]|1[0-9]|2[0-8])T([0-1][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]\\.[0-9]{1,6}Z")
			)
		}
	},
	Contract.make {
		request {
			name("expense_save_shouldReturn400IfBudgetIdInPathAndBodyDontMatch")
			method POST()
			url("/api/budgets/65/expenses")
			headers {
				accept applicationJson()
				contentType applicationJson()
			}
			body(
				budgetId: 2L,
				amount: anyPositiveInt(),
			)
		}
		response {
			status BAD_REQUEST()
			headers {
				contentType applicationJson()
			}
			body(
				"timestamp": value(regex("2[0-9]{3}-(1[0-2]|0[1-9])-(0[1-9]|1[0-9]|2[0-8])T([0-1][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]\\.[0-9]{1,6}Z")),
				"status": 400,
				"statusName": "BAD_REQUEST",
				"message": "Budget id in body and path don't match."
			)
		}
	}
]

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
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
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
				created: value(regex("([0-9]{4})-(1[0-2]|0[1-9])-(3[01]|0[1-9]|[12][0-9])T(2[0-3]|[01][0-9]):([0-5][0-9]):([0-5][0-9])(\\.\\d{6}Z)"))
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
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
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
				"timestamp": value(regex("([0-9]{4})-(1[0-2]|0[1-9])-(3[01]|0[1-9]|[12][0-9])T(2[0-3]|[01][0-9]):([0-5][0-9]):([0-5][0-9])(\\.\\d{6}Z)")),
				"status": 400,
				"statusName": "BAD_REQUEST",
				"message": "Budget id in body and path don't match."
			)
		}
	}
]

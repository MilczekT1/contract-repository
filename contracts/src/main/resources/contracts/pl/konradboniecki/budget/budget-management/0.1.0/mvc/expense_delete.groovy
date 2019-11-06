package contracts.jar.delete

import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("expense_delete_shouldReturn201")
		request {
			method DELETE()
			url(regex("/api/budgets/1/expenses/1"))
			headers {
				accept applicationJson()
			}
		}
		response {
			status NO_CONTENT()
		}
	},
	Contract.make {
		name("expense_delete_shouldReturn404WhenNotFoundInBudget")
		request {
			method DELETE()
			url("/api/budgets/1/expenses/2") {
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
				"message": "Expense with id: 2 not found in budget with id: 1."
			)
		}
	}
]

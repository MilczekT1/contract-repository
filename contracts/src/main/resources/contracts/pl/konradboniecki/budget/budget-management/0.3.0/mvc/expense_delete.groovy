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
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
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
				"message": "Expense with id: 2 not found in budget with id: 1."
			)
		}
	}
]

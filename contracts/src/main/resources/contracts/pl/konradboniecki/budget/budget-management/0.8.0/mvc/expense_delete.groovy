package contracts.jar.delete

import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("expense_delete_shouldReturn201")
		request {
			method DELETE()
			url(regex("/api/budget-mgt/v1/budgets/19e8147b-f6cb-46fa-b1d4-a0cb1ead4a08/expenses/445598c4-480a-452e-9493-8bc7ba709858"))
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
			url("/api/budget-mgt/v1/budgets/19e8147b-f6cb-46fa-b1d4-a0cb1ead4a08/expenses/570df03f-a98e-4752-bdab-3f7fa67e7945") {
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
				"message": "Expense with id: 570df03f-a98e-4752-bdab-3f7fa67e7945 not found in budget with id: 19e8147b-f6cb-46fa-b1d4-a0cb1ead4a08."
			)
		}
	}
]

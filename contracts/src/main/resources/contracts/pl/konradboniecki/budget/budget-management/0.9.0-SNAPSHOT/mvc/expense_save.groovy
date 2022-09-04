package contracts.jar.create

import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		request {
			name("expense_save_shouldReturn201WithExpenseInBodyWhenSuccess")
			method POST()
			url("/api/budget-mgt/v1/budgets/9ab79704-6682-4647-ade6-ac03aaaad427/expenses")
			headers {
				accept applicationJson()
				contentType applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
			body(
				budgetId: "9ab79704-6682-4647-ade6-ac03aaaad427",
				amount: value(producer(5L), consumer(anyNumber())),
				comment: value(producer("comment"), consumer(anyNonBlankString()))
			)
		}
		response {
			status CREATED()
			headers {
				contentType applicationJson()
			}
			body(
				id: value(anyUuid()),
				budgetId: fromRequest().body("budgetId"),
				amount: fromRequest().body("amount"),
				comment: fromRequest().body("comment"),
				created: value(regex("([0-9]{4})-(1[0-2]|0[1-9])-(3[01]|0[1-9]|[12][0-9])T(2[0-3]|[01][0-9]):([0-5][0-9]):([0-5][0-9])(\\.\\d{1,9}Z)"))
			)
		}
	},
	Contract.make {
		request {
			name("expense_save_shouldReturn400IfBudgetIdInPathAndBodyDontMatch")
			method POST()
			url("/api/budget-mgt/v1/budgets/e0860e06-9a46-4971-a447-07d46df471ae/expenses")
			headers {
				accept applicationJson()
				contentType applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
			body(
				budgetId: "230bc5b4-68c2-4a67-af09-3fdec0599de1",
				amount: anyNumber(),
			)
		}
		response {
			status BAD_REQUEST()
			headers {
				contentType applicationJson()
			}
			body(
				"timestamp": value(regex("([0-9]{4})-(1[0-2]|0[1-9])-(3[01]|0[1-9]|[12][0-9])T(2[0-3]|[01][0-9]):([0-5][0-9]):([0-5][0-9])(\\.\\d{1,9}Z)")),
				"status": 400,
				"statusName": "BAD_REQUEST",
				"message": "Budget id in body and path don't match."
			)
		}
	}
]

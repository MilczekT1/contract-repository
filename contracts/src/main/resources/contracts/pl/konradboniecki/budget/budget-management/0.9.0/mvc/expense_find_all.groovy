package contracts.expense.find

import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("expense_find_all_shouldReturn200With2ExpensesInBody")
		request {
			method GET()
			url(regex("/api/budget-mgt/v1/budgets/613c436d-ca18-4f31-9088-90efb19efd54/expenses"))
			headers {
				accept applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
		}
		response {
			status OK()
			headers {
				contentType applicationJson()
			}
			body '''\
				{
				  "items": [
				    {
				      "id": "52dc50fd-1dd1-4e62-bbab-2485f22f28ce",
				      "budgetId": "613c436d-ca18-4f31-9088-90efb19efd54",
				      "amount": 3,
				      "comment": "test_comments_1",
				      "created": "2019-06-16T10:22:54.246625Z"
				    },
				    {
				      "id": "896ffae8-0a10-46e1-933a-927a417cf447",
				      "budgetId": "613c436d-ca18-4f31-9088-90efb19efd54",
				      "amount": 4,
				      "comment": "test_comments_2",
				      "created": "2019-06-16T10:28:23.053553Z"
				    }
				  ],
				  "_meta": {
				    "page": 0,
				    "pageSize": 100,
				    "totalPages": 1,
				    "elements": 2,
				    "totalElements": 2
				  }
				}
			'''
		}
	},
	Contract.make {
		name("expense_find_all_shouldReturn200WithoutExpensesInBody")
		request {
			method GET()
			url(regex("/api/budget-mgt/v1/budgets/80adeba9-8ed6-4207-be1e-a1019439c0b5/expenses"))
			headers {
				accept applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
		}
		response {
			status OK()
			headers {
				contentType applicationJson()
			}
			body '''\
				{
				  "items": [],
				  "_meta": {
				    "page": 0,
				    "pageSize": 100,
				    "totalPages": 0,
				    "elements": 0,
				    "totalElements": 0
				  }
				}
			'''
		}
	}
]

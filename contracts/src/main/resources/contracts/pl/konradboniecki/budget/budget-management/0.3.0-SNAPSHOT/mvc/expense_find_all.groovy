package contracts.expense.find

import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("expense_find_all_shouldReturn200With2ExpensesInBody")
		request {
			method GET()
			url(regex("/api/budgets/1/expenses"))
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
				      "id": 1,
				      "budgetId": 1,
				      "amount": 3,
				      "comment": "test_comments_1",
				      "created": "2019-06-16T10:22:54.246625Z"
				    },
				    {
				      "id": 2,
				      "budgetId": 1,
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
			url(regex("/api/budgets/10/expenses"))
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
			//TODO: totalPages =1
			body '''\
				{
				  "items": [
				  ],
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

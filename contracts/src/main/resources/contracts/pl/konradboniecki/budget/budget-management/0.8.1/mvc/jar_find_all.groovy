import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("jar_find_all_shouldReturn200WithBodyWhen2JarsFoundInBudget")
		request {
			method GET()
			priority(1)
			url(regex("/api/budget-mgt/v1/budgets/bb973af2-1147-429b-9379-856a5ede2f60/jars"))
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
						"id": "afb2ab6f-7c0d-4ce7-8130-76efea5adc6b",
						"budgetId": "bb973af2-1147-429b-9379-856a5ede2f60",
						"jarName": "name1",
						"currentAmount": 0,
						"capacity": 3,
						"status": "NOT STARTED"
					},
					{
						"id": "b3e66a15-09e5-4a32-b9ec-d8c902bae0ea",
						"budgetId": "bb973af2-1147-429b-9379-856a5ede2f60",
						"jarName": "name2",
						"currentAmount": 0,
						"capacity": 3,
						"status": "NOT STARTED"
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
		name("jar_find_all_shouldReturn200WithBodyWhenNoJarFoundInBudget")
		request {
			method GET()
			priority(1)
			url(regex("/api/budget-mgt/v1/budgets/25372644-0c05-4ca7-abda-5ec08f0391b3/jars"))
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

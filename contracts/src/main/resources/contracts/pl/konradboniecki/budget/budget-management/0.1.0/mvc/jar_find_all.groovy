import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("jar_find_all_shouldReturn200WithBodyWhen2JarsFoundInBudget")
		request {
			method GET()
			priority(1)
			url(regex("/api/budgets/1/jars"))
			headers {
				accept applicationJson()
			}
		}
		response {
			status OK()
			headers {
				contentType applicationJson()
			}
			body '''\
				[
					{
						"id": 1,
						"budgetId": 1,
						"jarName": "name1",
						"currentAmount": 0,
						"capacity": 3,
						"status": "IN PROGRESS"
					},
					{
						"id": 2,
						"budgetId": 1,
						"jarName": "name2",
						"currentAmount": 0,
						"capacity": 3,
						"status": "IN PROGRESS"
					}
				]
			'''
		}
	},
	Contract.make {
		name("jar_find_all_shouldReturn200WithBodyWhenNoJarFoundInBudget")
		request {
			method GET()
			priority(1)
			url(regex("/api/budgets/2/jars"))
			headers {
				accept applicationJson()
			}
		}
		response {
			status OK()
			headers {
				contentType applicationJson()
			}
			body '''\
			[]
			'''
		}
	}
]

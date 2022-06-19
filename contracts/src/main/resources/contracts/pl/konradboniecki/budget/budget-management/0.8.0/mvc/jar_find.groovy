import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("jar_find_shouldReturn200WithBodyWhenFoundInBudget")
		request {
			method GET()
			priority(1)
			url(regex("/api/budget-mgt/v1/budgets/97f459b6-db3a-426a-9b3f-c40d589bc3a2/jars/8514d8b8-9c87-4909-be0c-bb03c78c0819"))
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
			body(
				id: value(fromRequest().path(6)),
				budgetId: value(fromRequest().path(4)),
				jarName: value(anyAlphaNumeric()),
				currentAmount: value(anyDouble()),
				capacity: value(anyDouble()),
				status: value("IN PROGRESS")
			)
		}
	},
	Contract.make {
		name("jar_find_shouldReturn404WhenNotFoundInBudget")
		request {
			method GET()
			priority(2)
			url("/api/budget-mgt/v1/budgets/97f459b6-db3a-426a-9b3f-c40d589bc3a2/jars/9f769ba3-8b72-4413-9709-f3c3394023eb") {
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
				"message": "Jar with id: 9f769ba3-8b72-4413-9709-f3c3394023eb not found in budget with id: 97f459b6-db3a-426a-9b3f-c40d589bc3a2"
			)
		}
	}
]

import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		request {
			name("jar_save_shouldReturn201WithBodyWhenSuccess")
			method POST()
			url("/api/budget-mgt/v1/budgets/39612072-c93d-4dc5-8eed-77b350c7533c/jars")
			headers {
				accept applicationJson()
				contentType applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
			body(
				budgetId: "39612072-c93d-4dc5-8eed-77b350c7533c",
				jarName: value(producer("name"), consumer(anyNonBlankString())),
				capacity: value(producer(5), consumer(anyPositiveInt())),
				currentAmount: 0
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
				jarName: fromRequest().body("jarName"),
				capacity: fromRequest().body("capacity"),
				currentAmount: 0,
				status: "NOT STARTED"
			)
		}
	},
	Contract.make {
		request {
			name("jar_save_shouldReturn400WhenBudgetIdIsDifferentInPathAndBody")
			method POST()
			url("/api/budget-mgt/v1/budgets/ff2416e7-938e-4cfc-8419-96f5010d3a01/jars")
			headers {
				accept applicationJson()
				contentType applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
			body(
				budgetId: value(anyUuid()),
				jarName: value(producer("name"), consumer(anyNonBlankString())),
				capacity: value(producer(5), consumer(anyPositiveInt())),
				"currentAmount": 0
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

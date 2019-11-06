import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		request {
			name("jar_save_shouldReturn201WithBodyWhenSuccess")
			method POST()
			url("/api/budgets/1/jars")
			headers {
				accept applicationJson()
				contentType applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
			body(
				budgetId: 1L,
				jarName: value(producer("name"), consumer(anyNonBlankString())),
				capacity: value(producer(5L), consumer(anyPositiveInt())),
			)
		}
		response {
			status CREATED()
			headers {
				contentType applicationJson()
			}
			body(
				id: value(producer(1L), consumer(anyPositiveInt())),
				budgetId: fromRequest().body("budgetId"),
				jarName: fromRequest().body("jarName"),
				capacity: fromRequest().body("capacity"),
				currentAmount: 0L,
				status: "IN PROGRESS"
			)
		}
	},
	Contract.make {
		request {
			name("jar_save_shouldReturn400WhenBudgetIdIsDifferentInPathAndBody")
			method POST()
			url("/api/budgets/1/jars")
			headers {
				accept applicationJson()
				contentType applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
			body(
				budgetId: 3L,
				jarName: value(producer("name"), consumer(anyNonBlankString())),
				capacity: value(producer(5L), consumer(anyPositiveInt())),
			)
		}
		response {
			status BAD_REQUEST()
			headers {
				contentType applicationJson()
			}
			body(
				"timestamp": value(regex("([0-9]{4})-(1[0-2]|0[1-9])-(3[01]|0[1-9]|[12][0-9])T(2[0-3]|[01][0-9]):([0-5][0-9]):([0-5][0-9])(\\.\\d{6}Z)")),
				"status": 400,
				"statusName": "BAD_REQUEST",
				"message": "Budget id in body and path don't match."
			)
		}
	}
]

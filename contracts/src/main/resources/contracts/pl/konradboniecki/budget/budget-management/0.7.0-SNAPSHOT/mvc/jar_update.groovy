import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		request {
			name("jar_update_shouldReturn200WhenJarUpdated")
			method PUT()
			url("/api/budget-mgt/v1/budgets/899a073e-12bf-4f27-85e4-3c004985e5b8/jars/325c033d-f17d-48d2-b75a-e14458200704")
			headers {
				accept applicationJson()
				contentType applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
			body(
				id: "325c033d-f17d-48d2-b75a-e14458200704",
				budgetId: "899a073e-12bf-4f27-85e4-3c004985e5b8",
				jarName: value(producer("modifiedName"), consumer(anyNonBlankString())),
				capacity: value(producer(5L), consumer(anyPositiveInt())),
				currentAmount: value(producer(4L), consumer(anyPositiveInt())),
			)
		}
		response {
			status OK()
			headers {
				contentType applicationJson()
			}
			body(
				id: fromRequest().body("id"),
				budgetId: fromRequest().body("budgetId"),
				jarName: fromRequest().body("jarName"),
				capacity: fromRequest().body("capacity"),
				currentAmount: fromRequest().body("currentAmount"),
				status: "IN PROGRESS"
			)
		}
	},
	Contract.make {
		request {
			name("jar_update_shouldReturn404WhenJarNotFoundDuringUpdate")
			method PUT()
			url("/api/budget-mgt/v1/budgets/899a073e-12bf-4f27-85e4-3c004985e5b8/jars/60b4ac5b-8b84-4eb5-a79a-b15af9d0761d")
			headers {
				accept applicationJson()
				contentType applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
			body(
				id: "60b4ac5b-8b84-4eb5-a79a-b15af9d0761d",
				budgetId: "899a073e-12bf-4f27-85e4-3c004985e5b8",
				jarName: value(producer("modifiedName"), consumer(anyNonBlankString())),
				capacity: value(producer(5L), consumer(anyPositiveInt())),
				currentAmount: value(producer(4L), consumer(anyPositiveInt())),
			)
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
				"message": "Jar with id: 60b4ac5b-8b84-4eb5-a79a-b15af9d0761d not found in budget with id: 899a073e-12bf-4f27-85e4-3c004985e5b8"
			)
		}
	}
]

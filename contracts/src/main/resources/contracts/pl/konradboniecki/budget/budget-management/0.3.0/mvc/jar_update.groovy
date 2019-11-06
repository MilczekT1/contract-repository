import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		request {
			name("jar_update_shouldReturn200WhenJarUpdated")
			method PUT()
			url("/api/budgets/4/jars/4")
			headers {
				accept applicationJson()
				contentType applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
			body(
				id: 4L,
				budgetId: 4L,
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
			url("/api/budgets/1/jars/5")
			headers {
				accept applicationJson()
				contentType applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
			body(
				id: 5L,
				budgetId: 1L,
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
				"message": "Jar with id: 5 not found in budget with id: 1"
			)
		}
	}
]

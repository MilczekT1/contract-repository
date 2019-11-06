import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("jar_find_shouldReturn200WithBodyWhenFoundInBudget")
		request {
			method GET()
			priority(1)
			url(regex("/api/budgets/1/jars/1"))
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
				id: value(fromRequest().path(4)),
				budgetId: value(fromRequest().path(2)),
				jarName: value(anyAlphaNumeric()),
				currentAmount: value(anyPositiveInt()),
				capacity: value(anyPositiveInt()),
				status: value("IN PROGRESS")
			)
		}
	},
	Contract.make {
		name("jar_find_shouldReturn404WhenNotFoundInBudget")
		request {
			method GET()
			priority(2)
			url("/api/budgets/1/jars/5") {
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
				"message": "Jar with id: 5 not found in budget with id: 1"
			)
		}
	}
]

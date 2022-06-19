import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("budget_find_shouldReturn200WhenFound")
		request {
			method GET()
			url("/api/budget-mgt/v1/budgets/1d6030f4-9051-4d1b-8b77-4fecd1ab9a52") {
				queryParameters {
					parameter("idType", "family")
				}
			}
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
				id: value(
					producer("cee8d4bd-e787-4912-91ed-57400cf90892"),
					consumer(anyUuid())
				),
				familyId: value(
					producer("1d6030f4-9051-4d1b-8b77-4fecd1ab9a52"),
					consumer("1d6030f4-9051-4d1b-8b77-4fecd1ab9a52")
				),
				maxJars: 6L
			)
		}
	},
	Contract.make {
		request {
			name("budget_find_shouldReturn404WhenNotFound")
			method GET()
			url("/api/budget-mgt/v1/budgets/b0492093-d920-492c-b5de-6e4046962410") {
				queryParameters {
					parameter("idType", "family")
				}
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
				"message": "Budget not found for family with id: b0492093-d920-492c-b5de-6e4046962410"
			)
		}
	}
]

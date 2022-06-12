import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("jar_delete_shouldReturn204WhenDeleted")
		request {
			method DELETE()
			url(regex("/api/budget-mgt/v1/budgets/38410e86-5782-4390-b026-184558177c5f/jars/666b975b-da45-4552-975b-26c559eb6b28"))
			headers {
				accept applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
		}
		response {
			status NO_CONTENT()
		}
	},
	Contract.make {
		name("jar_delete_shouldReturn404WhenNotFoundInBudget")
		request {
			method DELETE()
			url("/api/budget-mgt/v1/budgets/38410e86-5782-4390-b026-184558177c5f/jars/70fc6180-201e-4c7a-918e-095f8e9bfada") {
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
				"message": "Jar with id: 70fc6180-201e-4c7a-918e-095f8e9bfada not found in budget with id: 38410e86-5782-4390-b026-184558177c5f"
			)
		}
	}
]

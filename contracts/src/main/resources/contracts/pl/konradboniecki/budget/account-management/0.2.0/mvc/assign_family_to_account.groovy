import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("mvc_return 200 if family is assigned")
		request {
			method PUT()
			url(value(
				consumer("/api/account/1/family/1"),
				producer("/api/account/105/family/105"))
			)
			headers {
				accept applicationJson()
			}
		}
		response {
			status OK()
		}
	},
	Contract.make {
		name("mvc_return 404 if family is not found during assignment")
		request {
			method PUT()
			url("/api/account/1/family/5")
			url(value(
				consumer("/api/account/1/family/5"),
				producer("/api/account/105/family/106"))
			)
			headers {
				accept applicationJson()
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
				"message": value(
					consumer("Family with id: 5 not found."),
					producer("Family with id: 106 not found."))
			)
		}
	},
	Contract.make {
		name("mvc_return 404 if account is not found during family assignment")
		request {
			method PUT()
			url("/api/account/2/family/1")
			headers {
				accept applicationJson()
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
				"message": "Account with id: 2 not found"
			)
		}
	}
]

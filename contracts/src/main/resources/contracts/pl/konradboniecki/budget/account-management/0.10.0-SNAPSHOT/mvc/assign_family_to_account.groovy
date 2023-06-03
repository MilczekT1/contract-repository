import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("assign_family_to_account__return 200 if family is assigned")
		request {
			method PUT()
			url("/api/account-mgt/v1/accounts/4987a33c-66c5-47e9-86a1-d4da60cc6561/families/7dca5ea7-fa5c-4303-9569-c2f722a8fffa")
			headers {
				accept applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
		}
		response {
			status OK()
		}
	},
	Contract.make {
		name("assign_family_to_account__return 404 if family is not found during assignment")
		request {
			method PUT()
			url("/api/account-mgt/v1/accounts/4987a33c-66c5-47e9-86a1-d4da60cc6561/families/83e61d08-7b6d-4520-bf73-7d9822bb5eca")
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
				"timestamp": value(regex("([0-9]{4})-(1[0-2]|0[1-9])-(3[01]|0[1-9]|[12][0-9])T(2[0-3]|[01][0-9]):([0-5][0-9]):([0-5][0-9])(\\.\\d{1,9}Z)")),
				"status": 404,
				"statusName": "NOT_FOUND",
				"message": "Family with id: 83e61d08-7b6d-4520-bf73-7d9822bb5eca not found."
			)
		}
	},
	Contract.make {
		name("assign_family_to_account__return 404 if account is not found during family assignment")
		request {
			method PUT()
			url("/api/account-mgt/v1/accounts/fa63ec5b-38c7-4d11-befb-0227df4cad1b/families/f2dd6527-7d51-4f57-bc5d-f8cf8911dcf2")
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
				"timestamp": value(regex("([0-9]{4})-(1[0-2]|0[1-9])-(3[01]|0[1-9]|[12][0-9])T(2[0-3]|[01][0-9]):([0-5][0-9]):([0-5][0-9])(\\.\\d{1,9}Z)")),
				"status": 404,
				"statusName": "NOT_FOUND",
				"message": "Account with id: fa63ec5b-38c7-4d11-befb-0227df4cad1b not found"
			)
		}
	}
]

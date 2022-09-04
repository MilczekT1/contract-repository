import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("change_password_shouldReturn200IfPasswordChanged")
		request {
			method PUT()
			url("/api/account-mgt/v1/accounts/10d2f132-dc5a-4cfb-b82c-82f2eee80b4e/password")
			body(
				"accountId": "10d2f132-dc5a-4cfb-b82c-82f2eee80b4e",
				"newPassword": value(anyUuid())
			)
			headers {
				contentType applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
		}
		response {
			status OK()
		}
	},
	Contract.make {
		name("change_password_shouldReturn404WhenAccountNotFoundDuringPasswordChange")
		request {
			method PUT()
			url("/api/account-mgt/v1/accounts/dbe34030-183e-4118-86db-36eb15fa60b2/password")
			body(
				"accountId": "dbe34030-183e-4118-86db-36eb15fa60b2",
				"newPassword": value(anyUuid())
			)
			headers {
				contentType applicationJson()
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
				"message": "Account with id: dbe34030-183e-4118-86db-36eb15fa60b2 not found."
			)
		}
	}
]

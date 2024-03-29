import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("shouldReturn200WhenInvitationFoundById")
		request {
			method GET()
			url("/api/family-mgt/v1/invitations/0728df5b-7f7f-42f9-8eae-251e86d8360a")
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
				id: fromRequest().path(4),
				familyId: anyUuid(),
				email: anyEmail(),
				invitationCode: anyUuid(),
				created: value(regex("([0-9]{4})-(1[0-2]|0[1-9])-(3[01]|0[1-9]|[12][0-9])T(2[0-3]|[01][0-9]):([0-5][0-9]):([0-5][0-9])(\\.\\d{1,9}Z)")),
				registered: regex("(true|false)")
			)
		}
	},
	Contract.make {
		name("shouldReturn404WhenInvitationNotFoundById")
		request {
			method GET()
			url("/api/family-mgt/v1/invitations/801bcae9-348a-4cd3-9793-7e6234461d5f")
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
				"message": "invitation with id: 801bcae9-348a-4cd3-9793-7e6234461d5f not found."
			)
		}
	}
]

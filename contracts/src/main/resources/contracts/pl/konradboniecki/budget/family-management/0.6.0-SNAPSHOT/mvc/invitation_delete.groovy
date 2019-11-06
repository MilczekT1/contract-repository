import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("shouldReturn204WhenInvitationDeleted")
		request {
			method DELETE()
			url("/api/family-mgt/v1/invitations/a27e1edd-0a55-4531-9926-e74b95926174")
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
		name("shouldReturn404WhenInvitationNotFoundDuringDeletion")
		request {
			method DELETE()
			url("/api/family-mgt/v1/invitations/0631f6c6-eb39-41dd-9895-da1a9258d3e4")
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
				"message": "Invitation with id: 0631f6c6-eb39-41dd-9895-da1a9258d3e4 not found."
			)
		}
	}
]

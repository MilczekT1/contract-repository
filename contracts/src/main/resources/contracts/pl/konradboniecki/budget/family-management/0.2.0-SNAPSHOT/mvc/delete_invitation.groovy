import org.springframework.cloud.contract.spec.Contract
[
	Contract.make {
		name("shouldReturn204WhenFamilyDeleted")
		request {
			method DELETE()
			url(regex("/api/family-invitations/1"))
			headers {
				accept applicationJson()
			}
		}
		response {
			status NO_CONTENT()
		}
	},
	Contract.make {
		name("shouldReturn404WhenFamilyNotFoundDuringDeletion")
		request {
			method DELETE()
			url("/api/family-invitations/5") {
			}
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
				"message": "Invitation with id: 5 not found."
			)
		}
	}
]

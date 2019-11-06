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
				"timestamp": regex("2[0-9]{3}-(1[0-2]|0[1-9])-(0[1-9]|1[0-9]|2[0-8])T([0-1][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]\\.[0-9]{1,6}Z"),
				"status": 404,
				"statusName": "NOT_FOUND",
				"message": "Invitation with id: 5 not found."
			)
		}
	}
]

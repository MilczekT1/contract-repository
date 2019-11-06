import org.springframework.cloud.contract.spec.Contract
[
	Contract.make {
		name("shouldReturn200WhenInvitationFoundByFamilyIdAndEmail")
		request {
			method GET()
			url("/api/family-invitations/find-one") {
				queryParameters {
					parameter("email", "email@with-invitations.com")
					parameter("familyId", 1L)
					parameter("strict", "true")
				}
			}
			headers {
				accept applicationJson()
			}
		}
		response {
			status OK()
			headers {
				contentType applicationJson()
			}
			body(
				id: anyPositiveInt(),
				familyId: fromRequest().query("familyId"),
				email: fromRequest().query("email"),
				invitationCode: anyUuid(),
				applyTime: regex("2[0-9]{3}-(1[0-2]|0[1-9])-(0[1-9]|1[0-9]|2[0-8])T([0-1][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]\\.[0-9]{1,6}Z"),
				registeredStatus: regex("(true|false)")
			)
		}
	},
	Contract.make {
		name("shouldReturn404WhenInvitationsNotFoundByFamilyIdAndEmail")
		request {
			method GET()
			url("/api/family-invitations/find-one") {
				queryParameters {
					parameter("email", "email@without-invitations.com")
					parameter("familyId", 5L)
					parameter("strict", "true")
				}
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
				"message": "invitation with email: email@without-invitations.com and familyId: 5 not found."
			)
		}
	},
	Contract.make {
		name("shouldReturn200WhenInvitationFoundById")
		request {
			method GET()
			url("/api/family-invitations/find-one") {
				queryParameters {
					parameter("id", 1L)
				}
			}
			headers {
				accept applicationJson()
			}
		}
		response {
			status OK()
			headers {
				contentType applicationJson()
			}
			body(
				id: fromRequest().query("id"),
				familyId: anyPositiveInt(),
				email: anyEmail(),
				invitationCode: anyUuid(),
				applyTime: regex("2[0-9]{3}-(1[0-2]|0[1-9])-(0[1-9]|1[0-9]|2[0-8])T([0-1][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]\\.[0-9]{1,6}Z"),
				registeredStatus: regex("(true|false)")
			)
		}
	},
	Contract.make {
		name("shouldReturn404WhenInvitationNotFoundId")
		request {
			method GET()
			url("/api/family-invitations/find-one") {
				queryParameters {
					parameter("id", 5L)
				}
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
				"message": "invitation with id: 5 not found."
			)
		}
	}
]

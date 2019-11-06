import org.springframework.cloud.contract.spec.Contract
[
	Contract.make {
		name("shouldReturn204WithCreatedInvitation")
		request {
			method POST()
			url("/api/family-invitations")
			headers {
				accept applicationJson()
				contentType applicationJson()
			}
			body(
				familyId: value(producer(5L), anyPositiveInt()),
				email: value(producer("test@mail2.com"), anyEmail()),
				invitationCode: value(producer("ebdd12b3-aedc-4b32-9518-cc71263c0775"), consumer(anyUuid())),
				registeredStatus: value(producer(false), consumer(anyBoolean()))
			)
		}
		response {
			status CREATED()
			headers {
				contentType applicationJson()
			}
			body(
				id: anyPositiveInt(),
				familyId: fromRequest().body("familyId"),
				email: fromRequest().body("email"),
				invitationCode: fromRequest().body("invitationCode"),
				registeredStatus: fromRequest().body("registeredStatus"),
				applyTime: value(regex("([0-9]{4})-(1[0-2]|0[1-9])-(3[01]|0[1-9]|[12][0-9])T(2[0-3]|[01][0-9]):([0-5][0-9]):([0-5][0-9])(\\.\\d{6}Z)"))
			)
		}
	}
]

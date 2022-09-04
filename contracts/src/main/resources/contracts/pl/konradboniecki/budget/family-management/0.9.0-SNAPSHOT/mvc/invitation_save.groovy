import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("shouldReturn204WithCreatedInvitation")
		request {
			method POST()
			url("/api/family-mgt/v1/invitations")
			headers {
				accept applicationJson()
				contentType applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
			body(
				familyId: "f75df896-c050-4b55-9950-5ce262925572",
				email: value(producer("test@mail2.com"), anyEmail()),
				invitationCode: value(producer("ebdd12b3-aedc-4b32-9518-cc71263c0775"), consumer(anyUuid())),
				registered: value(producer(false), consumer(anyBoolean()))
			)
		}
		response {
			status CREATED()
			headers {
				contentType applicationJson()
			}
			body(
				id: value(anyUuid()),
				familyId: fromRequest().body("familyId"),
				email: fromRequest().body("email"),
				invitationCode: fromRequest().body("invitationCode"),
				registered: fromRequest().body("registered"),
				created: value(regex(isoDateTime()))
			)
		}
	}
]

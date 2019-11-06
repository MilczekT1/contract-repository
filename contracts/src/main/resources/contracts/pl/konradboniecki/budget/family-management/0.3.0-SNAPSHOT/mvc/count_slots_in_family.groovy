import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("shouldReturn_200_WhenSlots_WereCounted")
		request {
			method GET()
			url $(
				producer("/api/family/1/slots"),
				consumer("/api/family/1/slots")
			)
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
				familyId: fromRequest().path(2),
				freeSlots: 4,
				maxSlots: 5
			)
		}
	},
	Contract.make {
		name("shouldReturn_404_WhenSlots_WereCountedForNotExistingFamily")
		request {
			method GET()
			url $(
				producer("/api/family/5/slots"),
				consumer("/api/family/5/slots")
			)
			headers {
				accept applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
		}
		response {
			status NOT_FOUND()
		}
	}
]

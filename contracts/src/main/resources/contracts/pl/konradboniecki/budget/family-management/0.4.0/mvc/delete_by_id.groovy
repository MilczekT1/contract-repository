import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("shouldReturn204WhenFamilyDeletedById")
		request {
			method DELETE()
			url $(
				producer("/api/family/100"),
				consumer(regex("/api/family/100"))
			)
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
		name("shouldReturn404WhenFamilyNotFoundByIdDuringDeletion")
		request {
			method DELETE()
			url $(
				producer("/api/family/5"),
				consumer(regex("/api/family/5"))
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

import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("shouldReturn204WhenFamilyDeletedById")
		request {
			method DELETE()
			url("/api/family-mgt/v1/families/df511b31-0316-476e-8eff-f031692ac670")
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
			url("/api/family-mgt/v1/families/a31fed2a-8da7-46ea-8256-3b5e746d6fe5")
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

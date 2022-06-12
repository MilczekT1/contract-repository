import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("shouldReturn200WithBodyWhenFamilyFoundById")
		request {
			method GET()
			url("/api/family-mgt/v1/families/40320278-4656-4772-bd3a-68fd98ca5921")
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
				ownerId: anyUuid(),
				budgetId: anyUuid(),
				title: anyNonBlankString()
			)
		}
	},
	Contract.make {
		name("shouldReturn404WhenFamilyNotFoundById")
		request {
			method GET()
			url("/api/family-mgt/v1/families/a31fed2a-8da7-46ea-8256-3b5e746d6fe5") {
			}
			headers {
				accept applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
		}
		response {
			status NOT_FOUND()
		}
	},
	Contract.make {
		name("shouldReturn200WithBodyWhenFamilyFoundByOwnerId")
		request {
			method GET()
			url("/api/family-mgt/v1/families/owners/82e84da2-83db-47d9-b8f1-df44f2971acb")
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
				id: anyUuid(),
				ownerId: fromRequest().path(5),
				budgetId: anyUuid(),
				title: anyNonBlankString()
			)
		}
	},
	Contract.make {
		name("shouldReturn404WhenFamilyNotFoundByOwnerId")
		request {
			method GET()
			url("/api/family-mgt/v1/families/owners/336dd389-2a7b-4360-8c13-607c8d126c4f")
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

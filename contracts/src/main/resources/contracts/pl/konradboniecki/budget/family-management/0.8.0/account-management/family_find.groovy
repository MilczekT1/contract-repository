import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("shouldReturn404WhenFamilyNotFoundById")
		request {
			method GET()
			url("/api/family-mgt/v1/families/ddad74b9-8fb3-4195-a999-07c01aaee371")
			headers {
				accept(applicationJson())
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
		}
		response {
			status NOT_FOUND()
		}
	},
	Contract.make {
		name("shouldReturn200WithBodyWhenFamilyFoundById")
		request {
			method GET()
			url("/api/family-mgt/v1/families/6e3c8e50-099a-4a44-9f63-0a6704937649")
			headers {
				accept(applicationJson())
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
				ownerId: "06fc87d5-657f-4299-a93f-c3fa7a0ecd47",
				budgetId: anyUuid(),
				title: anyNonBlankString()
			)
		}
	}
]

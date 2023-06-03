import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("shouldReturn404WhenFamilyNotFoundDuringModification")
		request {
			method PUT()
			url("/api/family-mgt/v1/families/eb44bc19-1b29-444e-bcd2-d9ef9a449bf0")
			headers {
				accept applicationJson()
				contentType applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
			body(
				id: "eb44bc19-1b29-444e-bcd2-d9ef9a449bf0",
				ownerId: value(anyUuid()),
				budgetId: value(anyUuid()),
				title: value(producer("testTitle"), consumer(anyAlphaNumeric()))
			)
		}
		response {
			status NOT_FOUND()
		}
	},
	Contract.make {
		name("shouldReturn200WithBodyWhenFamilyModified")
		request {
			method PUT()
			url("/api/family-mgt/v1/families/1dac6f28-9a85-407c-8e07-8371f9c2e5d9")
			headers {
				accept applicationJson()
				contentType applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
			body(
				id: "1dac6f28-9a85-407c-8e07-8371f9c2e5d9",
				ownerId: value(producer("6c55e0e1-c847-4ef2-be26-08ad11d7927a"), consumer(anyUuid())),
				budgetId: value(producer("f771e0c9-aea6-4ca3-b580-e81e15ad22a4"), consumer(anyUuid())),
				title: value(producer("testTitle"), consumer(anyAlphaNumeric()))
			)
		}
		response {
			status OK()
			headers {
				contentType applicationJson()
			}
			body(
				id: fromRequest().body("id"),
				ownerId: fromRequest().body("ownerId"),
				budgetId: fromRequest().body("budgetId"),
				title: fromRequest().body("title"),
			)
		}
	}
]

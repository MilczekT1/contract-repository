import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("successfulFamilyCreation")
		request {
			method POST()
			url("/api/family-mgt/v1/families")
			headers {
				accept applicationJson()
				contentType applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
			body(
				ownerId: "37369461-0c5f-4240-8ff1-be20fda9c5df",
				budgetId: value(producer("09031a3c-2109-46d9-b966-1a51d97ede2e"), consumer(anyUuid())),
				title: value(producer("testTitle"), consumer(anyAlphaNumeric()))
			)
		}
		response {
			status CREATED()
			headers {
				contentType applicationJson()
			}
			body(
				id: value(anyUuid()),
				ownerId: fromRequest().body("ownerId"),
				budgetId: fromRequest().body("budgetId"),
				title: fromRequest().body("title")
			)
		}
	},
	Contract.make {
		name("conflictDuringFamilyCreation")
		request {
			method POST()
			url("/api/family-mgt/v1/families")
			headers {
				accept applicationJson()
				contentType applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
			body(
				ownerId: "2015e088-f035-47be-b5cd-ae74d22c728d",
				budgetId: anyUuid(),
				title: value(producer("testTitle"), consumer(anyAlphaNumeric()))
			)
		}
		response {
			status CONFLICT()
		}
	}
]

import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("shouldReturn200WithBodyWhenFamilyFoundById")
		request {
			method GET()
			url("/api/family/1") {
				queryParameters {
					parameter("idType", "id")
				}
			}
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
				id: fromRequest().path(2),
				ownerId: anyPositiveInt(),
				budgetId: anyPositiveInt(),
				title: anyNonBlankString(),
				maxMembers: anyPositiveInt()
			)
		}
	},
	Contract.make {
		name("shouldReturn404WhenFamilyNotFoundByIdWithParam")
		request {
			method GET()
			url("/api/family/5") {
				queryParameters {
					parameter("idType", "id")
				}
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
			url("/api/family/1") {
				queryParameters {
					parameter("idType", "ownerId")
				}
			}
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
				id: anyPositiveInt(),
				ownerId: fromRequest().path(2),
				budgetId: anyPositiveInt(),
				title: anyNonBlankString(),
				maxMembers: anyPositiveInt()
			)
		}
	},
	Contract.make {
		name("shouldReturn404WhenFamilyNotFoundByOwnerId")
		request {
			method GET()
			url("/api/family/5") {
				queryParameters {
					parameter("idType", "ownerId")
				}
			}
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

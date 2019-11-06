import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("shouldReturn404WhenFamilyNotFoundById")
		request {
			method GET()
			url $(
				producer("/api/family/5"),
				consumer("/api/family/5")
			)
			headers {
				accept(applicationJson())
			}
		}
		response {
			status NOT_FOUND()
		}
	},
	Contract.make {
		name("shouldReturn200WithBodyWhenFamilyFoundByIdWithoutParam")
		request {
			method GET()
			url $(
				producer("/api/family/1"),
				consumer("/api/family/1")
			)
			headers {
				accept(applicationJson())
			}
		}
		response {
			status OK()
			headers {
				contentType applicationJson()
			}
			body(
				id: fromRequest().path(2),
				ownerId: 2,
				budgetId: anyPositiveInt(),
				title: anyNonBlankString(),
				maxMembers: anyPositiveInt()
			)
		}
	}
]

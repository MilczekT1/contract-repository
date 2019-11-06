import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("shouldReturn404WhenFamilyNotFoundDuringModification")
		request {
			method PUT()
			url("/api/family")
			headers {
				accept applicationJson()
				contentType applicationJson()
			}
			body(
				id: 5,
				ownerId: anyPositiveInt(),
				budgetId: value(producer(5), consumer(anyPositiveInt())),
				title: value(producer("testTitle"), consumer(anyAlphaNumeric())),
				maxMembers: value(producer(5), consumer(anyPositiveInt()))
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
			url("/api/family")
			headers {
				accept applicationJson()
				contentType applicationJson()
			}
			body(
				id: value(producer(100L), consumer(1L)),
				ownerId: value(producer(100), consumer(anyPositiveInt())),
				budgetId: value(producer(100), consumer(anyPositiveInt())),
				title: value(producer("testTitle"), consumer(anyAlphaNumeric())),
				maxMembers: value(producer(5), consumer(anyPositiveInt()))
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
				maxMembers: fromRequest().body("maxMembers")
			)
		}
	}
]

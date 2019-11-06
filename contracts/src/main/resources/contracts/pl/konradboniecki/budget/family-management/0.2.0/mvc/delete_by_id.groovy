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
			}
		}
		response {
			status NOT_FOUND()
		}
	}
]

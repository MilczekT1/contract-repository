import org.springframework.cloud.contract.spec.Contract
[
    Contract.make {
        name("successfulFamilyCreation")
        request {
            method POST()
            url("/api/family")
            headers {
                accept applicationJson()
                contentType applicationJson()
            }
            body(
                ownerId: 100,
                budgetId: value(producer(100), consumer(anyPositiveInt())),
                title: value(producer("testTitle"), consumer(anyAlphaNumeric())),
                maxMembers: value(producer(5), consumer(anyPositiveInt()))
            )
        }
        response {
            status CREATED()
            headers {
                contentType applicationJson()
            }
            body(
                id: value(producer(100), consumer(anyPositiveInt())),
                ownerId: fromRequest().body("ownerId"),
                budgetId: fromRequest().body("budgetId"),
                title: fromRequest().body("title"),
                maxMembers: fromRequest().body("maxMembers")
            )
        }
    },
    Contract.make {
        name("conflictDuringFamilyCreation")
        request {
            method POST()
            url("/api/family")
            headers {
                accept applicationJson()
                contentType applicationJson()
            }
            body(
                ownerId: 101,
                budgetId: value(producer(100), consumer(anyPositiveInt())),
                title: value(producer("testTitle"), consumer(anyAlphaNumeric())),
                maxMembers: 5
            )
        }
        response {
            status CONFLICT()
        }
    }
]

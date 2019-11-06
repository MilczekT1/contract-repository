import org.springframework.cloud.contract.spec.Contract

Contract.make {
	description "When account found, then service should send new password activation link to user."
	request {
		url "/api/mail/reset-password"
		method POST()
		headers {
			accept applicationJson()
			contentType applicationJson()
		}
		body '''\
            {
                "Account": {
                    "id": 2,
                    "firstName": "kon",
                    "lastName": "bon",
                    "email": "test@mail.com"
                },
                "ResetCode": "29431ce1-8282-4489-8dd9-50f91e4c5653"
            }
        '''
	}
	response {
		status OK()
	}
}

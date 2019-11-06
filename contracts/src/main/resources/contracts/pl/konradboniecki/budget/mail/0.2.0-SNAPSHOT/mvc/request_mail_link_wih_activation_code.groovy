import org.springframework.cloud.contract.spec.Contract
Contract.make {
	description "Send mail with activation link."
	request {
		url "/api/mail/activate-account"
		method POST()
		headers {
			accept applicationJson()
			contentType applicationJson()
		}
		body '''\
            {
                "Account": {
                    "id": 2,
                    "firstName": "testFirstName",
                    "lastName": "testLastName",
                    "email": "test@mail.com"
                },
                "ActivationCode": "29431ce1-8282-4489-8dd9-50f91e4c5653"
            }
        '''
	}
	response {
		status OK()
	}
}

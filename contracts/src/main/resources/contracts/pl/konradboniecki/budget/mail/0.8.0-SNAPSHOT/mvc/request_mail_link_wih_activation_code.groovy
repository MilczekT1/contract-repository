import org.springframework.cloud.contract.spec.Contract

Contract.make {
	description "Send mail with activation link."
	name("shouldReturnOkWhenUserActivationEmailIsSent")
	request {
		url "/api/mail/v1/account-activations"
		method POST()
		headers {
			accept applicationJson()
			contentType applicationJson()
			header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
		}
		body '''\
            {
                "account": {
                    "id": "bdde2539-37fd-4e06-897d-2c145ca4afba",
                    "firstName": "testFirstName",
                    "lastName": "testLastName",
                    "email": "test@mail.com"
                },
                "activationCode": "29431ce1-8282-4489-8dd9-50f91e4c5653"
            }
        '''
	}
	response {
		status NO_CONTENT()
	}
}

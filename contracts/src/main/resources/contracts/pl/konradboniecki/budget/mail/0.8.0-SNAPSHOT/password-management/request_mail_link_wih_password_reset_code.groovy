import org.springframework.cloud.contract.spec.Contract

Contract.make {
	description "When account found, then service should send new password activation link to user."
	name("shouldReturnOkWhenResetPasswordEmailIsSent")
	request {
		url "/api/mail/v1/password-reset"
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
                    "firstName": "kon",
                    "lastName": "bon",
                    "email": "test@mail.com"
                },
                "resetCode": "29431ce1-8282-4489-8dd9-50f91e4c5653"
            }
        '''
	}
	response {
		status NO_CONTENT()
	}
}

import org.springframework.cloud.contract.spec.Contract

Contract.make {
	description "Send mail with invitation."
	name("shouldReturnOkWhenInvitationToNewUserIsSent")
	request {
		url "/api/mail/v1/family-invitations"
		method POST()
		headers {
			accept applicationJson()
			contentType applicationJson()
			header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
		}
		body '''\
            {
				"guest": true,
                "email": "test@mail.com",
                "inviter": {
                    "id": "5780007d-dcb2-42ab-9319-2695168336c0",
                    "firstName":"testFirstName2",
                    "lastName":"testLastName2",
                    "email":"email@email2.com"
                },
                "family": {
                    "id":"8b4c0235-f29a-4cf7-bdb3-505af01f4c7c",
                    "title":"testFamilyTitle"
                }
            }
        '''
	}
	response {
		status NO_CONTENT()
	}
}

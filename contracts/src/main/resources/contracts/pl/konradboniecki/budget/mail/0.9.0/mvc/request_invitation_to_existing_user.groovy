import org.springframework.cloud.contract.spec.Contract

Contract.make {
	description "Send mail with invitation."
	name("shouldReturnOkWhenInvitationToExistingUserIsSent")
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
                "guest": false,
                "invitee": {
                    "id": "63766b43-8eac-4719-b2e3-8165d1d3d077",
                    "firstName":"testFirstName1",
                    "lastName":"testLastName1",
                    "email":"email@email1.com"
                },
                "inviter": {
                    "id": "fc15fa5a-daf5-476d-aa22-404e7709d116",
                    "firstName":"testFirstName2",
                    "lastName":"testLastName2",
                    "email":"email@email2.com"
                },
                "family": {
                    "id":"eb75d4c2-534a-4c3e-84de-b4bec2d4bb36",
                    "title":"testFamilyTitle"
                },
                "invitationCode":"8f37ab38-971a-471e-9fac-d8e63e47ce34"
            }
        '''
	}
	response {
		status NO_CONTENT()
	}
}

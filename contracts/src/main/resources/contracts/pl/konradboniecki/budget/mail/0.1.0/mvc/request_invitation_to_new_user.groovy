import org.springframework.cloud.contract.spec.Contract
Contract.make {
	description "Send mail with invitation."
	request {
		url "/api/mail/invite-user/new"
		method POST()
		headers {
			accept applicationJson()
			contentType applicationJson()
		}
		body '''\
            {
                "Inviter": {
                    "id":1,
                    "firstName":"testFirstName2",
                    "lastName":"testLastName2",
                    "email":"email@email2.com"
                },
                "Family": {
                    "id":3,
                    "title":"testFamilyTitle"
                },
                "NewMemberEmail":"test@mail.com"
            }
        '''
	}
	response {
		status OK()
	}
}

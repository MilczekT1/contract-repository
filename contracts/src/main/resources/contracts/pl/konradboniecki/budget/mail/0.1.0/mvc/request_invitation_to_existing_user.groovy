import org.springframework.cloud.contract.spec.Contract
Contract.make {
    description "Send mail with invitation."
    request {
        url "/api/mail/invite-user/existing"
        method POST()
        headers {
            accept applicationJson()
            contentType applicationJson()
        }
        body '''\
            {
                "Account": {
                    "id":2,
                    "firstName":"testFirstName1",
                    "lastName":"testLastName1",
                    "email":"email@email1.com"
                },
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
                "InvitationCode":"8f37ab38-971a-471e-9fac-d8e63e47ce34"
            }
        '''
    }
    response {
        status OK()
    }
}

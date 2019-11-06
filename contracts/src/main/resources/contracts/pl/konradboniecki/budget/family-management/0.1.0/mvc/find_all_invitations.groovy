import org.springframework.cloud.contract.spec.Contract
[
	Contract.make {
		name("shouldReturn200With2InvitationsByFamilyId")
		request {
			method GET()
			url("/api/family-invitations/find-all") {
				queryParameters {
					parameter("familyId", 1L)
				}
			}
			headers {
				accept applicationJson()
			}
		}
		response {
			status OK()
			headers {
				contentType applicationJson()
			}
			body '''\
				[
					{
						"id": 6,
						"familyId": 1,
						"email": "mail_1@mail.com",
						"invitationCode": "34b7a194-b0d3-47f7-8aef-1d64caefcdf4",
						"applyTime": "2019-06-16T10:22:54.246625Z",
						"registeredStatus": true
					},
					{
						"id": 7,
						"familyId": 1,
						"email": "mail_2@mail.com",
						"invitationCode": "c04a8005-cb67-46de-a4dc-e4f84d26faf3",
						"applyTime": "2019-06-16T10:22:54.246625Z",
						"registeredStatus": false
					}
				]
			'''
		}
	},
	Contract.make {
		name("shouldReturn200With0InvitationsByFamilyId")
		request {
			method GET()
			url("/api/family-invitations/find-all") {
				queryParameters {
					parameter("familyId", 5L)
				}
			}
			headers {
				accept applicationJson()
			}
		}
		response {
			status OK()
			headers {
				contentType applicationJson()
			}
			body '''\
			[]
			'''
		}
	},
	Contract.make {
		name("shouldReturn200With2InvitationsByEmail")
		request {
			method GET()
			url("/api/family-invitations/find-all") {
				queryParameters {
					parameter("email", "email@with-invitations.com")
				}
			}
			headers {
				accept applicationJson()
			}
		}
		response {
			status OK()
			headers {
				contentType applicationJson()
			}
			body '''\
				[
					{
						"id": 6,
						"familyId": 6,
						"email": "email@with-invitations.com",
						"invitationCode": "34b7a194-b0d3-47f7-8aef-1d64caefcdf4",
						"applyTime": "2019-06-16T10:22:54.246625Z",
						"registeredStatus": true
					},
					{
						"id": 7,
						"familyId": null,
						"email": "email@with-invitations.com",
						"invitationCode": "c04a8005-cb67-46de-a4dc-e4f84d26faf3",
						"applyTime": "2019-06-16T10:22:54.246625Z",
						"registeredStatus": true
					}
				]
			'''
		}
	},
	Contract.make {
		name("shouldReturn200With0InvitationsByEmail")
		request {
			method GET()
			url("/api/family-invitations/find-all") {
				queryParameters {
					parameter("email", "email@without-invitations.com")
				}
			}
			headers {
				accept applicationJson()
			}
		}
		response {
			status OK()
			headers {
				contentType applicationJson()
			}
			body '''\
			[]
			'''
		}
	}
]

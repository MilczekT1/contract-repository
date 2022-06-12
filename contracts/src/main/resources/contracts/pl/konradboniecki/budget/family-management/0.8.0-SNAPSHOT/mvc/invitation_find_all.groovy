import org.springframework.cloud.contract.spec.Contract

[
	Contract.make {
		name("shouldReturn200With2InvitationsByFamilyId")
		request {
			method GET()
			url("/api/family-mgt/v1/invitations") {
				queryParameters {
					parameter("familyId", "3ee03003-b049-47fe-9269-e64eaba640e7")
				}
			}
			headers {
				accept applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
		}
		response {
			status OK()
			headers {
				contentType applicationJson()
			}
			body '''\
				{
				  "items": [
				    {
					  "id": "ff14b483-f28a-4b6a-a3ba-0d77de4b1682",
                      "familyId": "3ee03003-b049-47fe-9269-e64eaba640e7",
                      "email": "mail_1@mail.com",
                      "invitationCode": "34b7a194-b0d3-47f7-8aef-1d64caefcdf4",
                      "created": "2019-06-16T10:22:54.246625Z",
                      "registered": true
				    },
				    {
                      "id": "1474dffc-360a-4c33-a72d-c58fe72be1f2",
                      "familyId": "3ee03003-b049-47fe-9269-e64eaba640e7",
                      "email": "mail_2@mail.com",
                      "invitationCode": "c04a8005-cb67-46de-a4dc-e4f84d26faf3",
                      "created": "2019-06-16T10:22:54.246625Z",
                      "registered": false
				    }
				  ],
				  "_meta": {
				    "page": 0,
				    "pageSize": 100,
				    "totalPages": 1,
				    "elements": 2,
				    "totalElements": 2
				  }
				}
			'''
		}
	},
	Contract.make {
		name("shouldReturn200With0InvitationsByFamilyId")
		request {
			method GET()
			url("/api/family-mgt/v1/invitations") {
				queryParameters {
					parameter("familyId", "58701904-4407-4b26-ae77-745c61a49384")
				}
			}
			headers {
				accept applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
		}
		response {
			status OK()
			headers {
				contentType applicationJson()
			}
			body '''\
				{
				  "items": [],
				  "_meta": {
				    "page": 0,
				    "pageSize": 100,
				    "totalPages": 0,
				    "elements": 0,
				    "totalElements": 0
				  }
				}
			'''
		}
	},
	Contract.make {
		name("shouldReturn200With2InvitationsByEmail")
		request {
			method GET()
			url("/api/family-mgt/v1/invitations") {
				queryParameters {
					parameter("email", "email@with-invitations.com")
				}
			}
			headers {
				accept applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
		}
		response {
			status OK()
			headers {
				contentType applicationJson()
			}
			body '''\
				{
				  "items": [
				    {
					  "id": "90727450-8e56-4380-90c3-56fc56f4035d",
					  "familyId": "2db9b8ca-2cc1-4129-b402-cfe75ca08547",
					  "email": "email@with-invitations.com",
					  "invitationCode": "34b7a194-b0d3-47f7-8aef-1d64caefcdf4",
					  "created": "2019-06-16T10:22:54.246625Z",
					  "registered": true
				    },
				    {
					  "id": "7c28c191-821b-4977-996d-010787a203ee",
					  "familyId": null,
					  "email": "email@with-invitations.com",
					  "invitationCode": "c04a8005-cb67-46de-a4dc-e4f84d26faf3",
					  "created": "2019-06-16T10:22:54.246625Z",
					  "registered": true
				    }
				  ],
				  "_meta": {
				    "page": 0,
				    "pageSize": 100,
				    "totalPages": 1,
				    "elements": 2,
				    "totalElements": 2
				  }
				}
			'''
		}
	},
	Contract.make {
		name("shouldReturn200With0InvitationsByEmail")
		request {
			method GET()
			url("/api/family-mgt/v1/invitations") {
				queryParameters {
					parameter("email", "email@without-invitations.com")
				}
			}
			headers {
				accept applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
		}
		response {
			status OK()
			headers {
				contentType applicationJson()
			}
			body '''\
				{
				  "items": [],
				  "_meta": {
				    "page": 0,
				    "pageSize": 100,
				    "totalPages": 0,
				    "elements": 0,
				    "totalElements": 0
				  }
				}
			'''
		}
	},
	Contract.make {
		name("shouldReturn200WhenInvitationFoundByFamilyIdAndEmail")
		priority(1)
		request {
			method GET()
			url("/api/family-mgt/v1/invitations") {
				queryParameters {
					parameter("email", "email@with-invitations.com")
					parameter("familyId", "091a6799-bce9-444d-982d-8724d4d31588")
				}
			}
			headers {
				accept applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
		}
		response {
			status OK()
			headers {
				contentType applicationJson()
			}
			body '''\
				{
				  "items": [
				    {
					  "id": "90727450-8e56-4380-90c3-56fc56f4035d",
					  "familyId": "091a6799-bce9-444d-982d-8724d4d31588",
					  "email": "email@with-invitations.com",
					  "invitationCode": "34b7a194-b0d3-47f7-8aef-1d64caefcdf4",
					  "created": "2019-06-16T10:22:54.246625Z",
					  "registered": true
				    }
				  ],
				  "_meta": {
				    "page": 0,
				    "pageSize": 100,
				    "totalPages": 1,
				    "elements": 1,
				    "totalElements": 1
				  }
				}
			'''
		}
	},
	Contract.make {
		name("shouldReturn200With0InvitationsNotFoundByFamilyIdAndEmail")
		request {
			method GET()
			url("/api/family-mgt/v1/invitations") {
				queryParameters {
					parameter("email", "email@without-invitations.com")
					parameter("familyId", "89f066a0-23a4-4e2a-aff5-7d7f920afa48")
				}
			}
			headers {
				accept applicationJson()
				header 'Authorization': 'Basic dGVzdFVzZXJOYW1lOnRlc3RVc2VyUGFzc3dvcmQ='
			}
		}
		response {
			status OK()
			headers {
				contentType applicationJson()
			}
			body '''\
				{
				  "items": [],
				  "_meta": {
				    "page": 0,
				    "pageSize": 100,
				    "totalPages": 0,
				    "elements": 0,
				    "totalElements": 0
				  }
				}
			'''
		}
	}
]

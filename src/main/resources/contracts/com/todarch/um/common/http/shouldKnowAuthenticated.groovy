import org.springframework.cloud.contract.spec.Contract

[
  Contract.make {
    name("should check if user is authenticated")
    request {
      method GET()
      url '/api/authenticated'
      headers {
        contentType applicationJsonUtf8()
        header 'Authorization': execute('authToken()')
      }
    }
    response {
      status NO_CONTENT()
    }
  },
  Contract.make {
    name("should check if user is not authenticated")
    request {
      method GET()
      url '/api/authenticated'
      headers {
        contentType applicationJsonUtf8()
        header(authorization(), "Bearer invalidjwt")
      }
    }
    response {
      status FORBIDDEN()
    }
  }

]

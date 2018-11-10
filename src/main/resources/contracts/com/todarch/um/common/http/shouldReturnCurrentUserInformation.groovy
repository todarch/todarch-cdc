import org.springframework.cloud.contract.spec.Contract

Contract.make {
  name("should return current user information")
  request {
    method GET()
    url '/api/account'
    headers {
      contentType applicationJsonUtf8()
      header 'Authorization': execute('authToken()')
    }
  }
  response {
    status OK()
    headers {
      contentType applicationJsonUtf8()
    }
    body(
      email: $(c("test@user.com"), p(anyEmail()))
    )
  }
}

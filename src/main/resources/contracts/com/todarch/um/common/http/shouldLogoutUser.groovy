import org.springframework.cloud.contract.spec.Contract

Contract.make {
  name("should logout a user")
  request {
    method POST()
    url '/api/logout'
    headers {
      contentType applicationJsonUtf8()
      header 'Authorization': execute('authToken()')
    }
  }
  response {
    status NO_CONTENT()
  }
}

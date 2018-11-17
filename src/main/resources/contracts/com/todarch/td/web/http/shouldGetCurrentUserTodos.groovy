import org.springframework.cloud.contract.spec.Contract

Contract.make {
  name("should get current user todos")
  request {
    method GET()
    url '/api/todos'
    headers {
      contentType applicationJsonUtf8()
      header 'Authorization': execute('getJwt()')
    }
  }
  response {
    status OK()
    //TODO:selimssevgi: validate the array of todos
  }
}

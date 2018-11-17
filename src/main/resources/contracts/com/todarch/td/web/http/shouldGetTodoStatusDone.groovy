import org.springframework.cloud.contract.spec.Contract

Contract.make {
  request {
    method PUT()
    url $(c(regex('^/api/todos/[0-9]{1,9}/done$')), p('/api/todos/63/done'))
    headers {
      contentType applicationJsonUtf8()
      header 'Authorization': execute('getJwt()')
    }
  }
  response {
    status NO_CONTENT()
  }
}

import org.springframework.cloud.contract.spec.Contract

Contract.make {
  name("should update users todo fully")
  request {
    method PUT()
    url $(c(regex('^/api/todos/[0-9]{1,9}$')), p('/api/todos/63'))
    headers {
      contentType applicationJsonUtf8()
      header 'Authorization': execute('getJwt()')
    }
    body([
      title: "Updated Todo Item",
      description: "Updated Todo Item Description",
      priority: 7,
      timeNeededInMin: 80,
      tags: ["oldTag", "newTag"],

    ])
    //TODO:selimssevgi: use body matchers
  }
  response {
    status NO_CONTENT()
  }
}

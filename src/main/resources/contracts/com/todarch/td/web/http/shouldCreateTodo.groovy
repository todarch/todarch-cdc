import org.springframework.cloud.contract.spec.Contract

Contract.make {
  request {
    method POST()
    url '/api/todos'
    headers {
      contentType applicationJsonUtf8()
      header 'Authorization': execute('getJwt()')
    }
    body(
      // consumer can provide any value matching the regex
      // on producer side verification side use given values for producer
      title: $(consumer(any()), producer("New Todo Item")),
      description: $(consumer(any()), producer("New Todo Item Description")),
      priority: $(consumer(regex('[1-9]|10')), producer(5)),
      timeNeededInMin: $(consumer(anyPositiveInt()), producer(60)),
      //TODO:selimssevgi: include tags
    )
  }
  response {
    status CREATED()
    headers {
      contentType applicationJsonUtf8()
    }
    body(
      // return 123 to consumer, and any number in producer verification test
      id: $(consumer('123'), producer(anyNumber()))
    )
  }
}

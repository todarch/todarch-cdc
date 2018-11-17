import org.springframework.cloud.contract.spec.Contract

[
  Contract.make {
    name("should return todo details")
    request {
      method GET()
      url '/api/todos/63'
      headers {
        contentType applicationJsonUtf8()
        header 'Authorization': execute('getJwt()')
      }
    }
    response {
      status OK()
      headers {
        contentType applicationJsonUtf8()
      }
      body(
        id: $(c(63), p(anyInteger())),
        title: $(c("New Todo Title"), p(anyNonBlankString())),
        description: $(c("New Todo Desc"), p(anyNonBlankString())),
        priority: $(c(7), p(regex('[1-9]|10'))),
        status: $(c("INITIAL"), p(anyOf("INITIAL", "DONE"))),
        timeNeededInMin: $(c(60), p(anyInteger())),
        createdAtEpoch: $(c(1541043609554), p(anyInteger())),
        doneAtEpoch: $(c(1541043609894), p(optional(1541043609894)))
        //TODO:selimssevgi: include tags
      )
    }
  },
  Contract.make {
    name("should return 404 when todo does not exist")
    request {
      method GET()
      url '/api/todos/99'
      headers {
        contentType applicationJsonUtf8()
        header 'Authorization': execute('getJwt()')
      }
    }
    response {
      status NOT_FOUND()
    }
  }
]


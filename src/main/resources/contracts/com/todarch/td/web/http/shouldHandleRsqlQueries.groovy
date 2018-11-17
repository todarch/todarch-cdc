import org.springframework.cloud.contract.spec.Contract

[
  Contract.make {
    name("should handle valid rsql queries")
    request {
      method GET()
      urlPath ('/api/todos/') {
        queryParameters {
          parameter "q" : "validrsql"
        }
      }
      headers {
        contentType applicationJsonUtf8()
        header 'Authorization': execute('getJwt()')
      }
    }
    response {
      status OK()
    }
  },
  Contract.make {
    name("should return badrequest for invalid rsql queries")
    request {
      method GET()
      urlPath ('/api/todos/') {
        queryParameters {
          parameter "q" : "invalidrsql"
        }
      }
      headers {
        contentType applicationJsonUtf8()
        header 'Authorization': execute('getJwt()')
      }
    }
    response {
      status BAD_REQUEST()
    }
  }

]

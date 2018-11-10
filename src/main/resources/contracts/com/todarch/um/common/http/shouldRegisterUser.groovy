import org.springframework.cloud.contract.spec.Contract

[
  Contract.make {
    name("should register a user")
    request {
      method POST()
      url '/non-secured/register'
      headers {
        contentType applicationJsonUtf8()
      }
      body(
        email: $(c(email()), p("test@user.com")),
        password: $(consumer(any()), p("12345678")),
      )
    }
    response {
      status CREATED()
    }
  },
  Contract.make {
    name("should not register same email")
    request {
      method POST()
      url '/non-secured/register'
      headers {
        contentType applicationJsonUtf8()
      }
      body(
        email: $(c(email()), p("registered@user.com")),
        password: $(consumer(any()), p("12345678")),
      )
    }
    response {
      status EXPECTATION_FAILED()
      headers {
        contentType applicationJsonUtf8()
      }
      body(
        errorCode: "USER1",
        errorMessage: $(c("Email address already in use."), p(anyNonBlankString()))
      )
    }
  }
]

import org.springframework.cloud.contract.spec.Contract

[
  Contract.make {
    name("should authenticate a user")
    request {
      method POST()
      url '/non-secured/authenticate'
      headers {
        contentType applicationJsonUtf8()
      }
      body(
        email: $(c(email()), p("test@user.com")),
        password: $(consumer(any()), p("12345678")),
      )
    }
    response {
      status OK()
      headers {
        contentType applicationJsonUtf8()
        header(authorization(), "Bearer validjwt")
      }
      body(
        token: "validjwt"
      )
    }
  },
  Contract.make {
    name("should not authenticate incorrect credentials")
    request {
      method POST()
      url '/non-secured/authenticate'
      headers {
        contentType applicationJsonUtf8()
      }
      body(
        email: $(c(email()), p("nonexistent@user.com")),
        password: $(consumer(any()), p("wrongpassword")),
      )
    }
    response {
      status EXPECTATION_FAILED()
      headers {
        contentType applicationJsonUtf8()
      }
      body(
        errorCode: "USER3",
        errorMessage: $(c("Incorrect credentials."), p(anyNonBlankString()))
      )
    }
  },
  Contract.make {
    name("should not authenticate inactive account")
    request {
      method POST()
      url '/non-secured/authenticate'
      headers {
        contentType applicationJsonUtf8()
      }
      body(
        email: $(c(email()), p("inactive@user.com")),
        password: $(consumer(any()), p("12345678")),
      )
    }
    response {
      status EXPECTATION_FAILED()
      headers {
        contentType applicationJsonUtf8()
      }
      body(
        errorCode: "USER4",
        errorMessage: $(c("Account is not active."), p(anyNonBlankString()))
      )
    }
  }
]

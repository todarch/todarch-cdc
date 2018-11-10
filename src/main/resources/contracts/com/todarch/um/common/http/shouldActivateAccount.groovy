import org.springframework.cloud.contract.spec.Contract

[
  Contract.make {
    name("should activate an account")
    request {
      method POST()
      urlPath ('/non-secured/activate-account') {
        queryParameters {
          parameter "code" : any()
        }
      }
      headers {
        contentType applicationJsonUtf8()
      }
    }
    response {
      status NO_CONTENT()
    }
  },
  Contract.make {
    name("should handle invalid activation code")
    request {
      method POST()
      urlPath ('/non-secured/activate-account') {
        queryParameters {
          parameter "code" : "invalid-activation-code"
        }
      }
      headers {
        contentType applicationJsonUtf8()
      }
    }
    response {
      status EXPECTATION_FAILED()
      headers {
        contentType applicationJsonUtf8()
      }
      body(
        errorCode: "USER2",
        errorMessage: $(c("Invalid activation code."), p(anyNonBlankString()))
      )
    }
  },
]

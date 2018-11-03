package contracts.com.todarch.um.cm.messaging

import org.springframework.cloud.contract.spec.Contract

Contract.make {
  label "send_activation_email"
  input {
    triggeredBy "userRegistered()"
  }
  outputMessage {
    sentTo "emails" // destination
    body(
      to: "test@user.com",
      emailType: "ACTIVATION_EMAIL",
      parameters: [
              activation_url: $(c("https://todarch.com/xyz"), p(anyUrl()))
      ]
    )
    headers {
      messagingContentType applicationJson()
    }
  }
}

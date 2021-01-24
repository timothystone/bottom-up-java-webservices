package com.anothercaffeinatedday.ws.soap;

import com.anothercaffeinatedday.ws.soap.dto.PaymentProcessorRequest;
import com.anothercaffeinatedday.ws.soap.dto.PaymentProcessorResponse;

public class PaymentProcessorImpl implements PaymentProcessor {

  public PaymentProcessorResponse processPayment(PaymentProcessorRequest paymentProcessorRequest) {
    PaymentProcessorResponse paymentProcessorResponse = new PaymentProcessorResponse();
    String creditCardNumber = paymentProcessorRequest.getCreditCardInfo().getCardNumber();
    // Business Logic or a call to a Business Logic Class Goes Here.
    if (creditCardNumber == null || creditCardNumber.length() == 0) {
      throw new RuntimeException("Invalid Card Number");
    }

    paymentProcessorResponse.setResult(true);
    return paymentProcessorResponse;
  }

}

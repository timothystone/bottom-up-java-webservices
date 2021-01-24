package com.anothercaffeinatedday.ws.soap;

import com.anothercaffeinatedday.ws.soap.dto.PaymentProcessorRequest;
import com.anothercaffeinatedday.ws.soap.dto.PaymentProcessorResponse;
import com.anothercaffeinatedday.ws.soap.exceptions.ServiceException;

public class PaymentProcessorImpl implements PaymentProcessor {

  public PaymentProcessorResponse processPayment(PaymentProcessorRequest paymentProcessorRequest) throws
      ServiceException {
    PaymentProcessorResponse paymentProcessorResponse = new PaymentProcessorResponse();
    String creditCardNumber = paymentProcessorRequest.getCreditCardInfo().getCardNumber();
    // Business Logic or a call to a Business Logic Class Goes Here.
    if (creditCardNumber == null || creditCardNumber.length() == 0) {
      throw new ServiceException("Invalid Credit Card Number");
    }

    paymentProcessorResponse.setResult(true);
    return paymentProcessorResponse;
  }

}

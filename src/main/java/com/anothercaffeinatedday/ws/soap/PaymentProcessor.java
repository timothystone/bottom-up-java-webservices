package com.anothercaffeinatedday.ws.soap;

import com.anothercaffeinatedday.ws.soap.dto.PaymentProcessorRequest;
import com.anothercaffeinatedday.ws.soap.dto.PaymentProcessorResponse;

public interface PaymentProcessor {

	public PaymentProcessorResponse processPayment(PaymentProcessorRequest paymentProcessorRequest);
}

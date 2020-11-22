package com.anothercaffeinatedday.ws.soap;

import com.anothercaffeinatedday.ws.soap.dto.PaymentProcessorRequest;
import com.anothercaffeinatedday.ws.soap.dto.PaymentProcessorResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(name = "PaymentProcessor")
public interface PaymentProcessor {

    @WebMethod
    @WebResult(name = "response")
    PaymentProcessorResponse processPayment(@WebParam(name = "paymentProcessorRequest") PaymentProcessorRequest paymentProcessorRequest);
}

package com.anothercaffeinatedday.ws.soap.config;

import com.anothercaffeinatedday.ws.soap.PaymentProcessorImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class WebServiceConfig {

    @Autowired
    private Bus bus;

    @Bean
    public Endpoint endpoint() {
        Endpoint endpoint = new EndpointImpl(bus, new PaymentProcessorImpl());
        endpoint.publish("/paymentProcessor");
        return endpoint;
    }
}

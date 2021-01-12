package com.anothercaffeinatedday.ws.soap.config;

import com.anothercaffeinatedday.ws.soap.PaymentProcessorImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebServiceConfig {

    @Autowired
    private Bus bus;

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, new PaymentProcessorImpl());
        endpoint.publish("/paymentProcessor");

        Map<String, Object> properties = new HashMap<>();

        WSS4JInInterceptor request = new WSS4JInInterceptor(properties);
        endpoint.getInInterceptors().add(request);

        return endpoint;
    }
}

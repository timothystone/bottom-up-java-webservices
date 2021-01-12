package com.anothercaffeinatedday.ws.soap.config;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UTPasswordCallback implements CallbackHandler {

    private Map<String,String> passwords = new HashMap<>();

    public UTPasswordCallback() {
        passwords.put("tstone", "tstone");
        passwords.put("bella", "bella");
    }
    
    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

    }
}

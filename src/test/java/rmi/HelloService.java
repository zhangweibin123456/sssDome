package rmi;

import org.springframework.stereotype.Component;

@Component
public interface HelloService {   
    public String doHello(String name);   
} 

package rmi;

public  class HelloServiceImpl implements HelloService{   
  
    public String doHello(String name) {   
        return "Hello , " + name;   
    }   
  
}

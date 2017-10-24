package thrift;

public class HelloWorldImpl implements HelloWorldService {

	public HelloWorldImpl() {
	}

	@Override
	public String sayHello(String username) {
		return "Hi," + username + " welcome to thrift world";
	}

}

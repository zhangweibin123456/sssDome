package tcp;

public class EchoService {

	public String test(String input) {
		if ("FAIL".equals(input)) {
			throw new RuntimeException("Failure Demonstration");
		}
		return "echo:" + input;
	}

}

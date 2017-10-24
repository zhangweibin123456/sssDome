package ecodeAndDecodeTest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class EcodeTest {

	public static void main(String[] args) throws UnsupportedEncodingException {

		String fileName="asa sds.txt";
		System.out.println("初始文件名 = "+fileName);
		fileName=URLEncoder.encode(fileName, "utf-8");
		System.out.println("encode 之后 = "+fileName);
		fileName=URLDecoder.decode(fileName, "utf-8");
		fileName=URLDecoder.decode(fileName, "utf-8");
		System.out.println("decode 之后 = "+fileName);

	}

}

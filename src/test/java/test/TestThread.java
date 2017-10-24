package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
public class TestThread {

	@Test
	public void test() throws IOException {
		String path="ceshi.txt";
		BufferedReader bufferedReader = null;
		InputStreamReader inputStreamReader =null;
		InputStream inputStream=null;
		inputStream=new ClassPathResource(path).getInputStream();
		Assert.notNull(inputStream,	"inputStream is required; it must not be null");
		if(inputStream!=null){
			inputStreamReader=new InputStreamReader(inputStream);
		}
		Assert.notNull(inputStreamReader,	"inputStreamReader is required; it must not be null");
		if(inputStreamReader!=null){
			bufferedReader=new BufferedReader(inputStreamReader);
		}
		String text = FileCopyUtils.copyToString(bufferedReader);
		
		if(bufferedReader !=null){
			bufferedReader.close();
		}
		
		if(inputStreamReader !=null){
			inputStreamReader.close();
		}
		if(inputStream !=null){
			inputStream.close();
		}
		System.out.println(text);
		
	}
	
	

}

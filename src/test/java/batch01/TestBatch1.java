package batch01;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:batch01/batch01.xml")
public class TestBatch1 {

	@Autowired
	private Job xmlFileReadAndWriterJob;

	@Autowired
	private SimpleJobLauncher jobLauncher;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void test01() {

		
	    JobExecution result;
		
			try {
				result = jobLauncher.run(xmlFileReadAndWriterJob, new JobParametersBuilder()
				        .addString("inputFilePath", "D:\\BatchFile\\input.xml")
				        .addString("outputFilePath", "D:\\BatchFile\\output.xml")
				        .toJobParameters());
				
				 System.out.println(result.toString());
				 
			} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
					| JobParametersInvalidException e) {
				
				e.printStackTrace();
			}
		
	}

}

package batch;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-batch-myfirstbatch.xml")
public class TestBatch {

	@Autowired
	private Job ioSampleJob;

	@Autowired
	private SimpleJobLauncher jobLauncher;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void test01() {

		JobParametersBuilder jobPara = new JobParametersBuilder(); // 设置文件路径参数
		jobPara.addString("abcd", "世界这么大");
		JobExecution result = null;
		try {
			result = jobLauncher.run(ioSampleJob, jobPara.toJobParameters());
		} catch (JobExecutionAlreadyRunningException | org.springframework.batch.core.repository.JobRestartException
				| JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
			logger.debug("result = ",e);
		}

		ExitStatus es = result.getExitStatus();
		if (es.getExitCode().equals(ExitStatus.COMPLETED.getExitCode())) // 任务正常完成
		{
			logger.info("任务正常完成");
		} else {
			logger.info("任务失败");
		}
	}

}

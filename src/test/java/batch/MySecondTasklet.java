package batch;


import java.util.Map;

import org.springframework.batch.core.StepContribution;  
import org.springframework.batch.core.scope.context.ChunkContext;  
import org.springframework.batch.core.step.tasklet.Tasklet;  
import org.springframework.batch.repeat.RepeatStatus;  
  
 
public class MySecondTasklet implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
		
		System.out.println(sc.toString());
		
		Map<String, Object>  map= cc.getStepContext().getJobParameters();
		String aa= map.get("abcd").toString();
		System.out.println("abcd = "+aa);
		return RepeatStatus.FINISHED;
	}  
  
   
}  


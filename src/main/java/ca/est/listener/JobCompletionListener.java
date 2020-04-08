package ca.est.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

/**
 * 
 * @author estevam
 *
 */
public class JobCompletionListener extends JobExecutionListenerSupport {

	private static final Logger log = LogManager.getLogger(JobCompletionListener.class);
	
	@Override
	public void afterJob(JobExecution je) {

		if (je.getStatus() == BatchStatus.COMPLETED) {

			log.info("{} batch job completed at {} to {} job_id:{}", je.getJobConfigurationName(), 
					je.getCreateTime(), je.getEndTime(),je.getId());
		}
	}
}
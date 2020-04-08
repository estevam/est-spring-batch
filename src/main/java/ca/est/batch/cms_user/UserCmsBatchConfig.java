package ca.est.batch.cms_user;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ca.est.entity.CmsUser;

/**
 * 
 * @author estevam
 */
@Configuration
@EnableBatchProcessing
public class UserCmsBatchConfig {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private JobExecutionListener listener;
	
	@Autowired
	private CmsUserReader cmsUserReader;

	@Bean
	public Job processJob() {
		return jobBuilderFactory.get("processJob").incrementer(new RunIdIncrementer()).listener(listener)
				.flow(orderStep1()).end().build();
	}

	@Bean
	public Step orderStep1() {
		return stepBuilderFactory.get("orderStep1").<CmsUser, CmsUser>chunk(1).reader(cmsUserReader)
				.processor(new CmsUserProcessor()).writer(new CmsUserWriter()).build();
	}
}

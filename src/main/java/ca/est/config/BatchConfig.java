package ca.est.config;

import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ca.est.listener.JobCompletionListener;
/**
 * 
 * @author estevam
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig extends DefaultBatchConfigurer {

    @Override
    protected JobRepository createJobRepository() throws Exception {
        MapJobRepositoryFactoryBean factoryBean = new MapJobRepositoryFactoryBean();
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }
   
	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionListener();
	}
}

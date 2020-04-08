package ca.est;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import ca.est.entity.CmsUser;
import ca.est.repository.CmsUserRepository;

@SpringBootApplication
@ComponentScan("ca.est")
public class Application implements CommandLineRunner {

	private static final Logger log = LogManager.getLogger(Application.class);

	@Autowired
	private CmsUserRepository cmsUserRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		List<CmsUser> cmsUserList = cmsUserRepository.findAll();
		log.info("{}", cmsUserList.toString());
	}

}

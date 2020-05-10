package jp.co.metlife.api.instalive;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import jp.co.metlife.api.instalive.controller.InstaLiveController;
import jp.co.metlife.api.instalive.model.WebExDataModel;
import jp.co.metlife.api.instalive.repo.WebExRepo;

@SpringBootApplication
public class InstaLiveApplication {

	private Log log = LogFactory.getLog(InstaLiveApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(InstaLiveApplication.class, args);
	}
	
	@Bean
	  public CommandLineRunner demo(WebExRepo repository) {
	    return (args) -> {
	      // save a WebExDataModel
	      repository.save(new WebExDataModel("yamada", "tanaka","2134","webex.com","fsdf/sdf","45dsf"));

	      // fetch all customers
	      log.info("WebExDataModel found with findAll():");
	      log.info("-------------------------------");
	      for (WebExDataModel webex : repository.findAll()) {
	        log.info(webex.getAgentCode());
	      }
	      log.info("");

	    };
	  }

}

package tour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages="com.travelzilla.controller,com.travelzilla.repositories")
@EntityScan("com.travelzilla.model")
@EnableJpaRepositories("com.travelzilla.repositories")


public class TourAndTravelFinalApplicationTests {

//	@Test
//	void contextLoads() {
//	}
	public static void main(String[] args) {
		SpringApplication.run(TourAndTravelFinalApplication.class, args);
	}

}

package mx.aplazo.simpleinteresttest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SimpleInterestTestApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	public void applicationContextTest() {
		SimpleInterestTestApplication.main(new String[] {});
	}

}

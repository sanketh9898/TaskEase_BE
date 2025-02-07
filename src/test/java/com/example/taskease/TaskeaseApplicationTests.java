package com.example.taskease;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // This is crucial!
class TaskeaseApplicationTests {

	@Test
	void contextLoads() {
	}
}
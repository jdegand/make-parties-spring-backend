package com.example.makeparties;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.makeparties.controller.EventController;
import com.example.makeparties.controller.RsvpController;

@SpringBootTest
class MakePartiesSpringBackendApplicationTests {

	@Autowired
	private EventController eventController;

	@Autowired
	private RsvpController rsvpController;

	@Test
	void contextLoads() {
		Assertions.assertThat(eventController).isNotNull();
		Assertions.assertThat(rsvpController).isNotNull();
	}

}

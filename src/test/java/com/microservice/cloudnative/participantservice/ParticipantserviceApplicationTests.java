package com.microservice.cloudnative.participantservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.microservice.cloudnative.participantservice.ParticipantserviceApplication.Participant;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ParticipantserviceApplicationTests {
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testGetParticipants() {
		ResponseEntity<Participant[]> participants = restTemplate.getForEntity("http://localhost:8282/participants/", Participant[].class);
		for (Participant p : participants.getBody()) {
			System.out.println(p);
		}
	}

}

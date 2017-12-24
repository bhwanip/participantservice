package com.microservice.cloudnative.participantservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class ParticipantserviceApplication implements CommandLineRunner {


	private final List<Participant> paticipants = new ArrayList<>();
	
	public static void main(String[] args) {
		SpringApplication.run(ParticipantserviceApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		paticipants.add(new Participant("Sahil", "Arora", "Medium", "KA", new HashSet<Integer>(Arrays.asList(100))));
		paticipants.add(new Participant("Nitin", "Jain", "Large", "MH", new HashSet<Integer>(Arrays.asList(100,200))));
	}
	
	
	@RequestMapping(value="/race/{raceId}", method = RequestMethod.GET)
	public List<Participant> getParticipantsForRace(@PathVariable("raceId") String raceId){
		return paticipants.stream().filter(p -> p.getRaces().contains(Integer.valueOf(raceId))).collect(Collectors.toList());
	}
	
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Participant> getParticipants(){
		return paticipants;
	}
	
	public static class Participant {
		
		public Participant () {}
		
		public Participant(String firstNAme, String lastName, String shirtSize,
				String homeState, Set<Integer> races) {
			super();
			this.firstNAme = firstNAme;
			this.lastName = lastName;
			this.shirtSize = shirtSize;
			this.homeState = homeState;
			this.races = races;
		}
		
		private String firstNAme;
		private String lastName;
		private String shirtSize;
		private String homeState;
		private Set<Integer> races;
		
		public String getFirstNAme() {
			return firstNAme;
		}
		public String getLastName() {
			return lastName;
		}
		public String getShirtSize() {
			return shirtSize;
		}
		public String getHomeState() {
			return homeState;
		}
		public Set<Integer> getRaces() {
			return races;
		}

		@Override
		public String toString() {
			return "Participant [firstNAme=" + firstNAme + ", lastName="
					+ lastName + ", shirtSize=" + shirtSize + ", homeState="
					+ homeState + ", races=" + races + "]";
		}
		
	}
}

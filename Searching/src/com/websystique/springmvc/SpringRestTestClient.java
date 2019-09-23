package com.websystique.springmvc;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.websystique.springmvc.model.CardioChallenge;


public class SpringRestTestClient {

	public static final String REST_SERVICE_URI = "http://localhost:8080/Searching-0.0.1-SNAPSHOT";
	
	/* GET */
	@SuppressWarnings("unchecked")
	private static void listAllCardios(){
		System.out.println("Testing listAllCardio API-----------");
		
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> CardiosMap = restTemplate.getForObject(REST_SERVICE_URI+"/cardio/", List.class);
		
		if(CardiosMap!=null){
			for(LinkedHashMap<String, Object> map : CardiosMap){
	            System.out.println("Cardio : id="+map.get("id")+", Name="+map.get("name")+", Level="+map.get("level"));;
	        }
		}else{
			System.out.println("No Cardio exist----------");
		}
	}
	
	/* GET */
	private static void getCardio(){
		System.out.println("Testing getCardio API----------");
		RestTemplate restTemplate = new RestTemplate();
        CardioChallenge Cardio = restTemplate.getForObject(REST_SERVICE_URI+"/cardio/1", CardioChallenge.class);
        System.out.println(Cardio);
	}
	
	/* POST */
    private static void createCardio() {
		System.out.println("Testing create Cardio API----------");
    	RestTemplate restTemplate = new RestTemplate();
        CardioChallenge Cardio = new CardioChallenge(0,"Walking","intermidiate");
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/cardio/", Cardio, CardioChallenge.class);
        System.out.println("Location : "+uri.toASCIIString());
    }

    /* PUT */
    private static void updateCardio() {
		System.out.println("Testing update Cardio API----------");
        RestTemplate restTemplate = new RestTemplate();
        CardioChallenge Cardio  = new CardioChallenge(1,"Running","advance");
        restTemplate.put(REST_SERVICE_URI+"/cardio/1", Cardio);
        System.out.println(Cardio);
    }

    /* DELETE */
    private static void deleteCardio() {
		System.out.println("Testing delete Cardio API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/cardio/3");
    }


    /* DELETE */
    private static void deleteAllCardios() {
		System.out.println("Testing all delete Cardios API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/cardio/");
    }

    /* GET */
	@SuppressWarnings("unchecked")
	private static void listAllCardiosByLevel(){
		System.out.println("Testing listAllCardiosByLevel API-----------");
		
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> CardiosMap = restTemplate.getForObject(REST_SERVICE_URI+"/cardio/", List.class);
		
		if(CardiosMap!=null){
			for(LinkedHashMap<String, Object> map : CardiosMap){
	            System.out.println("Cardio : id="+map.get("id")+", Name="+map.get("name")+", Level="+map.get("level"));;
	        }
		}else{
			System.out.println("No Cardio exist----------");
		}
	}
	
	
    
    public static void main(String args[]){
		//listAllCardios();
		getCardio();
		//createCardio();
		//listAllCardios();
		//updateCardio();
		//listAllCardios();
		//deleteCardio();
		//listAllCardios();
		//deleteAllCardios();
		//listAllCardios();
		

		listAllCardiosByLevel();
    }
}
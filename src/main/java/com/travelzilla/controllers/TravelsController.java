package com.travelzilla.controllers;

import java.util.List;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travelzilla.exceptions.BusException;
import com.travelzilla.exceptions.SessionException;
import com.travelzilla.exceptions.TravelsException;
import com.travelzilla.models.Bus;
import com.travelzilla.models.Session;
import com.travelzilla.models.Travels;
import com.travelzilla.models.TravelsDTO;
import com.travelzilla.models.UserType;
import com.travelzilla.services.SessionServices;
import com.travelzilla.services.TravelsService;

@RestController
@RequestMapping("/Travels")
public class TravelsController {
	@Autowired
	private TravelsService cont;

	@Autowired
	SessionServices service;
	
	
//	Register New traveler along with new bus If travels want.
	@PostMapping("/Register")
	public ResponseEntity<Travels> registerNewTravelsHandler( @RequestBody Travels travel ) throws TravelsException, BusException, SessionException{

//			Main Function calling
			Travels travels1=cont.registerNewTravels(travel);
			return new ResponseEntity<Travels>(travels1, HttpStatus.CREATED);
			
		

	}
	
//	Add new Bus With existing Travels.
	@PostMapping("/AddNewBus/{Travel_id}")
	public ResponseEntity<Travels> registerNewBusInTravelsHandler( @PathVariable("Travel_id") Integer tid,
			@RequestBody Bus bus ,@RequestParam("sessionKey") String key) throws TravelsException, SessionException{
		Session session= service.getASessionByKey(key);
//		authentication
		if(session.getUserType()==UserType.ADMIN ||(session.getUserType()==UserType.TRAVELS && session.getUserId()==tid)) {
			
//			Main Function calling
			Travels travels1=cont.registerNewBusInTravels(tid, bus);
			return new ResponseEntity<Travels>(travels1, HttpStatus.OK);
			
		}else {
			throw new SessionException("Please Enter Correct Key. Or You are not Authorized...!");
		}
		
		
	}
	
//	Register old bus with other travels.
	@PutMapping("/AddOldBus/{Travel_id}/{Bus_Id}")
	public ResponseEntity<Travels> registerOldBusInTravelsHandler(@PathVariable("Travel_id") Integer tid,@PathVariable("Bus_Id") Integer bid ,@RequestParam("sessionKey") String key) throws TravelsException, BusException, SessionException{
		Session session= service.getASessionByKey(key);
//		authentication
		if(session.getUserType()==UserType.ADMIN ||(session.getUserType()==UserType.TRAVELS && session.getUserId()==tid) ) {
			
//			Main Function calling
			Travels travels1=cont.registerOldBusInTravels(tid, bid);
			return new ResponseEntity<Travels>(travels1, HttpStatus.OK);
			
		}else {
			throw new SessionException("Please Enter Correct Key. Or You are not Authorized...!");
		}
		
		
	}
	
//  Search travels with their IDs.
	@GetMapping("/GetById/{Travel_id}")
	public ResponseEntity<Travels> getTravelsByIdHandler(@PathVariable("Travel_id") Integer travelsID ,@RequestParam("sessionKey") String key) throws TravelsException, SessionException{
		Session session= service.getASessionByKey(key);
//		authentication
		if(session.getUserType()==UserType.ADMIN ||(session.getUserType()==UserType.TRAVELS && session.getUserId()==travelsID)) {
			
//			Main Function calling
			Travels travels1= cont.getTravelsById(travelsID);
			return new ResponseEntity<Travels>(travels1, HttpStatus.CREATED);
			
		}else {
			throw new SessionException("Please Enter Correct Key. Or You are not Authorized...!");
		}
		
		
	}
	
//	Get all Travels from database.
	@GetMapping("/GetAll")
	public ResponseEntity<List<Travels>> getAllTravelsHandler(@RequestParam("sessionKey") String key) throws TravelsException, SessionException{
		Session session= service.getASessionByKey(key);
//		authentication
		if(session.getUserType()==UserType.ADMIN) {
			
//			Main Function calling
			List<Travels> travels1= cont.getAllTravelsDetails();
			return new ResponseEntity<List<Travels>>(travels1, HttpStatus.CREATED);
			
		}else {
			throw new SessionException("Please Enter Correct Key..!");
		}
		
		
	}

}

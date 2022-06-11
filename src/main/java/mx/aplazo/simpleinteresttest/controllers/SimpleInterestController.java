package mx.aplazo.simpleinteresttest.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mx.aplazo.simpleinteresttest.request.SimpleInterestRequest;
import mx.aplazo.simpleinteresttest.response.SimpleInterestResponse;
import mx.aplazo.simpleinteresttest.services.ISimpleInterestService;

@RestController
public class SimpleInterestController {
	
	@Autowired
	private ISimpleInterestService simpleInterestServie;
	
	@PostMapping("/simple-interest")
	public ResponseEntity<List<SimpleInterestResponse>> simpleInterest(@Valid @RequestBody SimpleInterestRequest sir) {
		List<SimpleInterestResponse> simpleInterestResponse = simpleInterestServie.calculateSimpleInterest(sir);
		
		return new ResponseEntity<>(simpleInterestResponse, HttpStatus.OK);
	}

}

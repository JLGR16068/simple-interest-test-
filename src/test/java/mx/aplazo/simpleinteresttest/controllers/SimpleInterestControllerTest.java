package mx.aplazo.simpleinteresttest.controllers;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.aplazo.simpleinteresttest.request.SimpleInterestRequest;
import mx.aplazo.simpleinteresttest.response.SimpleInterestResponse;
import mx.aplazo.simpleinteresttest.services.ISimpleInterestService;

@WebMvcTest(SimpleInterestController.class)
class SimpleInterestControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private ISimpleInterestService simpleInterestServie;
	
	private List<SimpleInterestResponse> mockResponse;	
	private String requestJson;
	private String responseJson;
	private SimpleInterestRequest sir; 
	
	 @BeforeEach 
	 public void init() throws JsonProcessingException {
		mockResponse = new ArrayList<>();
		mockResponse.add(new SimpleInterestResponse(1, 460.0, Date.from(LocalDate.now().plusWeeks(1)
                .atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())));
		mockResponse.add(new SimpleInterestResponse(2, 460.0, Date.from(LocalDate.now().plusWeeks(2)
                .atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())));
		mockResponse.add(new SimpleInterestResponse(3, 460.0, Date.from(LocalDate.now().plusWeeks(3)
                .atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())));
		
		sir = new SimpleInterestRequest(1200.00, 3, 0.05);
		requestJson = objectMapper.writeValueAsString(sir);
		responseJson = objectMapper.writeValueAsString(mockResponse); 
	}
	
	@Test
	void simpleInterest() throws Exception {
		when(simpleInterestServie.calculateSimpleInterest(any()
					)).thenReturn(mockResponse);
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/simple-interest")
				.accept(MediaType.APPLICATION_JSON)
				.content(requestJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		
		MvcResult result = mockMvc.perform(request).andReturn();
		
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		JSONAssert.assertEquals(responseJson, result.getResponse()
				.getContentAsString(), false);
	}
}

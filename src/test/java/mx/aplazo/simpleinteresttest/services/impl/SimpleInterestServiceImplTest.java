package mx.aplazo.simpleinteresttest.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.aplazo.simpleinteresttest.domain.Loan;
import mx.aplazo.simpleinteresttest.domain.Payment;
import mx.aplazo.simpleinteresttest.mapper.SimpleInterestMapper;
import mx.aplazo.simpleinteresttest.repositories.ILoanRepository;
import mx.aplazo.simpleinteresttest.repositories.IPaymentRepository;
import mx.aplazo.simpleinteresttest.request.SimpleInterestRequest;
import mx.aplazo.simpleinteresttest.response.SimpleInterestResponse;

@ExtendWith(MockitoExtension.class)
class SimpleInterestServiceImplTest {
	
	@InjectMocks
	private SimpleInterestServiceImpl simpleInterestService;
	
	@Mock
	private SimpleInterestMapper mapper;
	
	@Mock
	private ILoanRepository loanRepository;
	
	@Mock
	private IPaymentRepository paymentRepository;
	
	private SimpleInterestRequest sir;
	private Loan loan;
	private Loan loanSaved;
	private List<Payment> payments;
	private List<SimpleInterestResponse> response;
	
	@BeforeEach
	public void init() {
		sir = new SimpleInterestRequest(1200.00, 3, 0.05);
		loan = new Loan(null, 1200.0, 3, 0.05, null);
		loanSaved = new Loan(1L, 1200.0, 3, 0.05, null);;
		
		new Payment(1L, 1, 460.0, Date.from(LocalDate.now().plusWeeks(2)
                .atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), loanSaved);
		
		payments = new ArrayList<>();
		payments.add(new Payment(1L, 1, 460.0, Date.from(LocalDate.now().plusWeeks(1)
                .atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), loanSaved));
		payments.add(new Payment(2L, 2, 460.0, Date.from(LocalDate.now().plusWeeks(2)
                .atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), loanSaved));
		payments.add(new Payment(3L, 3, 460.0, Date.from(LocalDate.now().plusWeeks(3)
                .atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), loanSaved));
		
		response = new ArrayList<>();
		response.add(new SimpleInterestResponse(1, 460.0, Date.from(LocalDate.now().plusWeeks(1)
                .atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())));
		response.add(new SimpleInterestResponse(2, 460.0, Date.from(LocalDate.now().plusWeeks(2)
                .atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())));
		response.add(new SimpleInterestResponse(3, 460.0, Date.from(LocalDate.now().plusWeeks(3)
                .atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())));
	}
	
	@Test
	public void calculateSimpleInterest() {
		when(mapper.simpleInteresRequestToLoan(any())).thenReturn(loan);
		when(loanRepository.save(any())).thenReturn(loan);
		when(paymentRepository.saveAll(any())).thenReturn(payments);
		when(mapper.paymentsToSimpleInterestResponses(any())).thenReturn(response);
		
		List<SimpleInterestResponse> actualResponse = simpleInterestService.calculateSimpleInterest(sir);
		
		verify(mapper).simpleInteresRequestToLoan(any());

		assertEquals(180, simpleInterestService.makeSimpleInterestCalculation(
							loan.getAmount(), 
							loan.getRate(), 
							loan.getTerms()));
		assertEquals(response, actualResponse);
	}
	
	
	
	
}

package mx.aplazo.simpleinteresttest.services.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.aplazo.simpleinteresttest.domain.Loan;
import mx.aplazo.simpleinteresttest.domain.Payment;
import mx.aplazo.simpleinteresttest.mapper.SimpleInterestMapper;
import mx.aplazo.simpleinteresttest.repositories.ILoanRepository;
import mx.aplazo.simpleinteresttest.repositories.IPaymentRepository;
import mx.aplazo.simpleinteresttest.request.SimpleInterestRequest;
import mx.aplazo.simpleinteresttest.response.SimpleInterestResponse;
import mx.aplazo.simpleinteresttest.services.ISimpleInterestService;

@Service
public class SimpleInterestServiceImpl implements ISimpleInterestService {
	
	@Autowired
	private SimpleInterestMapper mapper;
	
	@Autowired
	private ILoanRepository loanRepository;
	
	@Autowired
	private IPaymentRepository paymentRepository;

	@Override
	public List<SimpleInterestResponse> calculateSimpleInterest(SimpleInterestRequest sir) {
		Loan loan = mapper.simpleInteresRequestToLoan(sir);
		loan = loanRepository.save(loan);
		
		
		Double simpleInterest = makeSimpleInterestCalculation(loan.getAmount(), loan.getRate(), loan.getTerms());
		Double amount = loan.getAmount() + simpleInterest;
		Double amountPerWeek = amount / loan.getTerms();
		List<Payment> paymentList = new ArrayList<>();
		
		for(int i = 1; i <= loan.getTerms(); i++) {
			Date date = Date.from(LocalDate.now().plusWeeks(i)
	                .atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
			paymentList.add(new Payment(null, i, amountPerWeek, date, loan));
		}
		
		paymentList = paymentRepository.saveAll(paymentList);
		
		
		return mapper.paymentsToSimpleInterestResponses(paymentList);
	}

	public Double makeSimpleInterestCalculation(Double principal, Double rate, Integer time) {
		return principal * rate * time;
	}
}

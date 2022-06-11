package mx.aplazo.simpleinteresttest.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import mx.aplazo.simpleinteresttest.domain.Loan;
import mx.aplazo.simpleinteresttest.domain.Payment;
import mx.aplazo.simpleinteresttest.request.SimpleInterestRequest;
import mx.aplazo.simpleinteresttest.response.SimpleInterestResponse;

@Mapper(componentModel = "spring")
public interface SimpleInterestMapper {
	
	Loan simpleInteresRequestToLoan(SimpleInterestRequest sir);
	
	SimpleInterestRequest loanToSimpleInterestRequest(Loan loan);
	
	List<SimpleInterestResponse> paymentsToSimpleInterestResponses(List<Payment> payments);
	
}

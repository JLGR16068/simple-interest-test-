package mx.aplazo.simpleinteresttest.services;

import java.util.List;

import mx.aplazo.simpleinteresttest.request.SimpleInterestRequest;
import mx.aplazo.simpleinteresttest.response.SimpleInterestResponse;

public interface ISimpleInterestService {

	List<SimpleInterestResponse> calculateSimpleInterest(SimpleInterestRequest sir);
}

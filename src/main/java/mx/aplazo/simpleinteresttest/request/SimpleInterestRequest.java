package mx.aplazo.simpleinteresttest.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleInterestRequest {

	@NotNull(message = "Amount is required")
	private Double amount;
	
	@NotNull(message = "Terms is required")
	private Integer terms;
	
	@NotNull(message = "Rate is required")
	private Double rate;
}

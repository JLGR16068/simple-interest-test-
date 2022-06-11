package mx.aplazo.simpleinteresttest.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleInterestResponse {
	
	@JsonProperty("payment_number")
	private Integer paymentNumer;
	
	private Double amount;
	
	@JsonProperty("payment_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date paymentDate;

}

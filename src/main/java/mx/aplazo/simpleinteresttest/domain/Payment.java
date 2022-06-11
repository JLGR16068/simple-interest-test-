package mx.aplazo.simpleinteresttest.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "payments")
@Data
@AllArgsConstructor
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer paymentNumer;	
	private Double amount;
	private Date paymentDate;
	
	@ManyToOne 
    @JoinColumn(name="loan_id", nullable=false) 
    private Loan loan; 
}

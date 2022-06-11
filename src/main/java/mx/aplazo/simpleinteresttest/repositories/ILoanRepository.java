package mx.aplazo.simpleinteresttest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.aplazo.simpleinteresttest.domain.Loan;

@Repository
public interface ILoanRepository extends JpaRepository<Loan, Long>{

}

package mx.aplazo.simpleinteresttest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.aplazo.simpleinteresttest.domain.Payment;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Long> {

}

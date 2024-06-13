package zut.wi.streamingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zut.wi.streamingservice.model.Payment;

import java.util.UUID;

@Repository
public interface PaymentsRepository extends JpaRepository<Payment, UUID> {

}

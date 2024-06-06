package zut.wi.streamingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zut.wi.streamingservice.model.Payment;

import java.util.UUID;

public interface PaymentsRepository extends JpaRepository<Payment, UUID> {

}

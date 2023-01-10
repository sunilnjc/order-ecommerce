package com.order.ecommerce.service.payments;

import com.order.ecommerce.entity.Payment;
import com.order.ecommerce.enums.PaymentStatus;
import com.order.ecommerce.repository.IPaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {
    private final IPaymentRepository paymentRepository;
    @Transactional
    public Payment buildAndSavePayment(double amount, String paymentMode) {
        Payment payment = new Payment(
                UUID.randomUUID().toString(),
                amount,
                paymentMode,
                UUID.randomUUID().toString(),
                PaymentStatus.PROCESSING.name(),
                LocalDate.now(),
                null
        );
        log.info("Saving payment details for payment id = {}", payment.getPaymentId());
        return paymentRepository.save(payment);
    }
}

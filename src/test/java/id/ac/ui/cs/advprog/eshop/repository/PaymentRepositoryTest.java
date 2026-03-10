package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    Payment payment1;
    Payment payment2;

    @BeforeEach
    void setUp() {
        // Ingat pelajaran berharga tadi, jangan sampe lupa di-new! 😂
        paymentRepository = new PaymentRepository();

        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");
        payment1 = new Payment("payment-1", "VOUCHER", paymentData1);

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("address", "Jalan Margonda Raya");
        paymentData2.put("deliveryFee", "10000");
        payment2 = new Payment("payment-2", "CASH_ON_DELIVERY", paymentData2);
    }

    @Test
    void testSaveCreate() {
        Payment result = paymentRepository.save(payment1);

        Payment findResult = paymentRepository.findById(payment1.getId());
        assertEquals(payment1.getId(), result.getId());
        assertEquals(payment1.getId(), findResult.getId());
        assertEquals(payment1.getMethod(), findResult.getMethod());
        assertEquals(payment1.getStatus(), findResult.getStatus());
    }

    @Test
    void testSaveUpdate() {
        paymentRepository.save(payment1);
        
        // Simulasi update status
        payment1.setStatus("REJECTED");
        Payment result = paymentRepository.save(payment1);

        Payment findResult = paymentRepository.findById(payment1.getId());
        assertEquals(payment1.getId(), result.getId());
        assertEquals("REJECTED", findResult.getStatus());
    }

    @Test
    void testFindByIdIfFound() {
        paymentRepository.save(payment1);
        Payment findResult = paymentRepository.findById(payment1.getId());
        assertNotNull(findResult);
        assertEquals(payment1.getId(), findResult.getId());
    }

    @Test
    void testFindByIdIfNotFound() {
        paymentRepository.save(payment1);
        Payment findResult = paymentRepository.findById("payment-ngasal");
        assertNull(findResult);
    }

    @Test
    void testFindAll() {
        paymentRepository.save(payment1);
        paymentRepository.save(payment2);

        List<Payment> paymentList = paymentRepository.findAll();
        assertEquals(2, paymentList.size());
    }
}
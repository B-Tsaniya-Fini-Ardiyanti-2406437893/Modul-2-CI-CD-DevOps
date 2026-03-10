package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    Payment payment1;
    Payment payment2;
    private Order order; 

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        this.order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");

        paymentRepository = new PaymentRepository();

        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");
        payment1 = new Payment("payment-1", order, "VOUCHER", paymentData1);

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("address", "Jalan Margonda Raya");
        paymentData2.put("deliveryFee", "10000");
        payment2 = new Payment("payment-2", order, "CASH_ON_DELIVERY", paymentData2);
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
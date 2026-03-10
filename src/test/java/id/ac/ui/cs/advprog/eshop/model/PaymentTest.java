package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

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
    }

    @Test
    void testCreatePaymentVoucherSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678"); 

        Payment payment = new Payment("payment-1", order, "VOUCHER", paymentData);

        assertEquals("VOUCHER", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentVoucherRejectedNot16Chars() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234"); 

        Payment payment = new Payment("payment-2", order, "VOUCHER", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentVoucherRejectedNotStartWithEshop() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "KSHOP1234ABC5678"); 

        Payment payment = new Payment("payment-3", order, "VOUCHER", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentVoucherRejectedNot8Digits() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOPABCDEFGHJKL"); 

        Payment payment = new Payment("payment-4", order, "VOUCHER", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentMethodNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("payment-5", order, "", new HashMap<>());
        });
    }

    @Test
    void testCreatePaymentCODSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jalan Margonda Raya, Depok");
        paymentData.put("deliveryFee", "10000");

        Payment payment = new Payment("payment-cod-1", order, "CASH_ON_DELIVERY", paymentData);

        assertEquals("CASH_ON_DELIVERY", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentCODRejectedEmptyAddress() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "");
        paymentData.put("deliveryFee", "10000");

        Payment payment = new Payment("payment-cod-2", order, "CASH_ON_DELIVERY", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentCODRejectedNullAddress() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", null);
        paymentData.put("deliveryFee", "10000");

        Payment payment = new Payment("payment-cod-3", order, "CASH_ON_DELIVERY", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentCODRejectedEmptyDeliveryFee() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jalan Margonda Raya, Depok");
        paymentData.put("deliveryFee", "");

        Payment payment = new Payment("payment-cod-4", order, "CASH_ON_DELIVERY", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentCODRejectedNullDeliveryFee() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jalan Margonda Raya, Depok");
        paymentData.put("deliveryFee", null);

        Payment payment = new Payment("payment-cod-5", order, "CASH_ON_DELIVERY", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }
}
package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    void testCreatePaymentVoucherSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678"); 

        Payment payment = new Payment("payment-1", "VOUCHER", paymentData);

        assertEquals("VOUCHER", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentVoucherRejectedNot16Chars() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234"); 

        Payment payment = new Payment("payment-2", "VOUCHER", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentVoucherRejectedNotStartWithEshop() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "KSHOP1234ABC5678"); 

        Payment payment = new Payment("payment-3", "VOUCHER", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentVoucherRejectedNot8Digits() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOPABCDEFGHJKL"); 

        Payment payment = new Payment("payment-4", "VOUCHER", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentMethodNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("payment-5", "", new HashMap<>());
        });
    }

    @Test
    void testCreatePaymentCODSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jalan Margonda Raya, Depok");
        paymentData.put("deliveryFee", "10000");

        Payment payment = new Payment("payment-cod-1", "CASH_ON_DELIVERY", paymentData);

        assertEquals("CASH_ON_DELIVERY", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentCODRejectedEmptyAddress() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "");
        paymentData.put("deliveryFee", "10000");

        Payment payment = new Payment("payment-cod-2", "CASH_ON_DELIVERY", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentCODRejectedNullAddress() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", null);
        paymentData.put("deliveryFee", "10000");

        Payment payment = new Payment("payment-cod-3", "CASH_ON_DELIVERY", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentCODRejectedEmptyDeliveryFee() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jalan Margonda Raya, Depok");
        paymentData.put("deliveryFee", "");

        Payment payment = new Payment("payment-cod-4", "CASH_ON_DELIVERY", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentCODRejectedNullDeliveryFee() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jalan Margonda Raya, Depok");
        paymentData.put("deliveryFee", null);

        Payment payment = new Payment("payment-cod-5", "CASH_ON_DELIVERY", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }
}
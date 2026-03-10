package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Order order;
    private Map<String, String> paymentData;

    public Payment(String id, Order order, String method, Map<String, String> paymentData) {
        if (method == null || method.isEmpty()) {
            throw new IllegalArgumentException("Payment method cannot be empty");
        }
        if (paymentData == null || paymentData.isEmpty()) {
            throw new IllegalArgumentException("Payment data cannot be empty");
        }
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        this.id = id;
        this.order = order;
        this.method = method;
        this.paymentData = paymentData;

        if ("VOUCHER".equals(method)) {
            this.status = validateVoucher(paymentData.get("voucherCode")) ? "SUCCESS" : "REJECTED";
        } else if ("CASH_ON_DELIVERY".equals(method)) {
            this.status = validateCOD(paymentData.get("address"), paymentData.get("deliveryFee")) ? "SUCCESS" : "REJECTED";
        } else {
            this.status = "REJECTED";
        }
    }

    private boolean validateVoucher(String voucherCode) {
        if (voucherCode == null || voucherCode.length() != 16 || !voucherCode.startsWith("ESHOP")) {
            return false;
        }
        
        int numCount = 0;
        for (char c : voucherCode.toCharArray()) {
            if (Character.isDigit(c)) {
                numCount++;
            }
        }
        return numCount == 8;
    }

    private boolean validateCOD(String address, String deliveryFee) {
        if (address == null || address.trim().isEmpty()) {
            return false;
        }
        if (deliveryFee == null || deliveryFee.trim().isEmpty()) {
            return false;
        }
        return true;
    }
}
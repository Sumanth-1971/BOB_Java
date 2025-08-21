package com.bob.db.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class RazorpayDTO {

    private Long id;
    private String orderId;
    private Integer amount;
    private String currency;
    private String status;
    private String receipt;
    private Map<String, Object> notes;
    private String paymentId;
    private Integer capturedAmount;
    private LocalDateTime createdAt;
    private String candidateId;
    private String positionId;

    public RazorpayDTO() {
    }

    public RazorpayDTO(Long id, String orderId, Integer amount, String currency, String status, String receipt,
                       Map<String, Object> notes, String paymentId, String signature, Integer capturedAmount,
                       LocalDateTime createdAt, LocalDateTime updatedAt, String candidateId, String positionId) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
        this.status = status;
        this.receipt = receipt;
        this.notes = notes;
        this.paymentId = paymentId;
        this.capturedAmount = capturedAmount;
        this.createdAt = createdAt;
        this.candidateId = candidateId;
        this.positionId = positionId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public Integer getAmount() { return amount; }
    public void setAmount(Integer amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getReceipt() { return receipt; }
    public void setReceipt(String receipt) { this.receipt = receipt; }

    public Map<String, Object> getNotes() { return notes; }
    public void setNotes(Map<String, Object> notes) { this.notes = notes; }

    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }

    public Integer getCapturedAmount() { return capturedAmount; }
    public void setCapturedAmount(Integer capturedAmount) { this.capturedAmount = capturedAmount; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getCandidateId() { return candidateId; }
    public void setCandidateId(String candidateId) { this.candidateId = candidateId; }

    public String getPositionId() { return positionId; }
    public void setPositionId(String positionId) { this.positionId = positionId; }
}

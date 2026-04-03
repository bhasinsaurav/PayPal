package com.paypal.transaction_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name =  "transaction")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Sender_name", nullable = false)
    private String senderName;

    @Column(name = "Receiver_Name", nullable = false)
    private String receiverName;

    @Column(nullable = false)
    @Positive(message = "Amount should be positive")
    private Double amount;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private String status;

    @PrePersist
    public void PrePersist(){
        if(timestamp == null){
            timestamp =  LocalDateTime.now();
        }

        if(status == null){
            status = "PENDING";
        }
    }

    @Override
    public String toString(){
        return "Transaction {" +
                "id=" + id +
                ", senderName='" + senderName + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                ", status='" + status + '\'' +
                '}';
    }
}

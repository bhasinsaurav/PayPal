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

    @Column(name = "Sender_Id", nullable = false)
    private Long senderId;

    @Column(name = "Receiver_Id", nullable = false)
    private Long receiverId;

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
                ", senderName='" + senderId + '\'' +
                ", receiverName='" + receiverId + '\'' +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                ", status='" + status + '\'' +
                '}';
    }
}

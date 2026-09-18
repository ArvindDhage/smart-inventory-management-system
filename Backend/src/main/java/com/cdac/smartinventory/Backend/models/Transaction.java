package com.cdac.smartinventory.Backend.models;

import com.cdac.smartinventory.Backend.enums.TransactionStatus;
import com.cdac.smartinventory.Backend.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction")
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Integer totalProducts;

    private BigDecimal totalPrice;



    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;  // purchase ,sale ,return

    @Enumerated(EnumType.STRING)
    private TransactionStatus status; //pending , completed ,processing

    private String description;
    private String note;

    private final LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updateAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "transaction_product",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> product;

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", totalProducts=" + totalProducts +
                ", totalPrice=" + totalPrice +
                ", transactionType=" + transactionType +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", note='" + note + '\'' +
                ", createdAt=" + createdAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
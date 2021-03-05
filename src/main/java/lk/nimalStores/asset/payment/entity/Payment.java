package lk.nimalStores.asset.payment.entity;


import com.fasterxml.jackson.annotation.JsonFilter;

import lk.nimalStores.asset.common_asset.model.enums.LiveDead;
import lk.nimalStores.asset.invoice.entity.Enum.PaymentMethod;
import lk.nimalStores.asset.purchase_order.entity.PurchaseOrder;
import lk.nimalStores.util.audit.AuditEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("Payment")
public class Payment extends AuditEntity {

    private String bankName;

    private String remarks;

    @Column(nullable = false, unique = true)
    private String code;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private LiveDead liveDead;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.EAGER)
    private PurchaseOrder purchaseOrder;
}

package lk.nimalStores.asset.goodReceivedNote.entity;


import com.fasterxml.jackson.annotation.JsonFilter;
import lk.nimalStores.asset.PurchaseOrder.entity.PurchaseOrder;
import lk.nimalStores.asset.goodReceivedNote.entity.Enum.GoodReceivedNoteState;
import lk.nimalStores.asset.ledger.entity.Ledger;
import lk.nimalStores.util.audit.AuditEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter( "GoodReceivedNote" )
public class GoodReceivedNote extends AuditEntity {

    private String remarks;

    @Column( precision = 10, scale = 2 )
    private BigDecimal totalAmount;

    @Enumerated( EnumType.STRING )
    private GoodReceivedNoteState goodReceivedNoteState;

    @ManyToOne
    private PurchaseOrder purchaseOrder;

    @OneToMany( mappedBy = "goodReceivedNote", cascade = CascadeType.PERSIST)
    private List< Ledger > ledgers;


}

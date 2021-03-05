package lk.nimalStores.asset.ledger.entity;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lk.nimalStores.asset.common_asset.model.enums.LiveDead;
import lk.nimalStores.asset.good_received_note.entity.GoodReceivedNote;
import lk.nimalStores.asset.invoice_ledger.entity.InvoiceLedger;
import lk.nimalStores.asset.item.entity.Item;
import lk.nimalStores.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter( "Ledger" )
public class Ledger extends AuditEntity {

    @NotEmpty
    private String quantity;

    @Column( nullable = false, precision = 10, scale = 2 )
    private BigDecimal sellPrice;

    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private LocalDate manufactureDate;

    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private LocalDate expiredDate;

    @Enumerated( EnumType.STRING)
    private LiveDead liveDead;

    @ManyToOne(fetch=FetchType.EAGER)
    private Item item;

    @ManyToOne
    @JsonIgnore
    private GoodReceivedNote goodReceivedNote;


    @OneToMany(mappedBy = "ledger")
    private List<InvoiceLedger> invoiceLedgers;


}

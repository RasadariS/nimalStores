package lk.nimal_stores.asset.ledger.entity;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lk.nimal_stores.asset.common_asset.model.enums.LiveDead;
import lk.nimal_stores.asset.good_received_note.entity.GoodReceivedNote;
import lk.nimal_stores.asset.invoice_ledger.entity.InvoiceLedger;
import lk.nimal_stores.asset.item.entity.Item;
import lk.nimal_stores.util.audit.AuditEntity;
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
    private List< InvoiceLedger > invoiceLedgers;


}

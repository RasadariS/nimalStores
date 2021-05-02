package lk.nimal_stores.asset.brand.entity;


import com.fasterxml.jackson.annotation.JsonFilter;

import lk.nimal_stores.asset.category.entity.Category;
import lk.nimal_stores.asset.item.entity.Item;
import lk.nimal_stores.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("Brand")
public class Brand extends AuditEntity {
    @NotNull
    @Size(min = 1, message = "This name length should be more than one character")
    @Column(unique = true)
    private String name;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "brand")
    private List<Item> items;



}

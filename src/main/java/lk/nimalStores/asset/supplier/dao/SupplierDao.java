package lk.nimalStores.asset.supplier.dao;

import lk.nimalStores.asset.supplier.entity.Supplier;


import lk.nimalStores.asset.supplier_item.entity.enums.ItemSupplierStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierDao extends JpaRepository<Supplier, Integer> {
    Supplier findFirstByOrderByIdDesc();

    Supplier findByIdAndItemSupplierStatus(Integer supplierId, ItemSupplierStatus itemSupplierStatus);
}

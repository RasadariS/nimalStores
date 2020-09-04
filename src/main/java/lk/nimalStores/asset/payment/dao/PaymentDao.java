package lk.nimalStores.asset.payment.dao;

import lk.nimalStores.asset.PurchaseOrder.entity.PurchaseOrder;
import lk.nimalStores.asset.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentDao extends JpaRepository< Payment,Integer> {
    List< Payment> findByPurchaseOrder(PurchaseOrder purchaseOrder);

    Payment findFirstByOrderByIdDesc();
}

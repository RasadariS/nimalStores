package lk.nimalStores.asset.goodReceivedNote.dao;

import lk.nimalStores.asset.PurchaseOrder.entity.PurchaseOrder;
import lk.nimalStores.asset.goodReceivedNote.entity.GoodReceivedNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodReceivedNoteDao extends JpaRepository< GoodReceivedNote, Integer> {
    GoodReceivedNote findByPurchaseOrder(PurchaseOrder purchaseOrder);
}

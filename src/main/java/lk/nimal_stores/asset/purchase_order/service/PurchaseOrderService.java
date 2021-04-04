package lk.nimal_stores.asset.purchase_order.service;


import lk.nimal_stores.asset.common_asset.model.enums.LiveDead;
import lk.nimal_stores.asset.purchase_order.dao.PurchaseOrderDao;
import lk.nimal_stores.asset.purchase_order.entity.PurchaseOrder;
import lk.nimal_stores.asset.purchase_order.entity.enums.PurchaseOrderStatus;
import lk.nimal_stores.asset.supplier.entity.Supplier;
import lk.nimal_stores.util.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@CacheConfig( cacheNames = "purchaseOrder" )
public class PurchaseOrderService implements AbstractService< PurchaseOrder, Integer > {
    private final PurchaseOrderDao purchaseOrderDao;

    @Autowired
    public PurchaseOrderService(PurchaseOrderDao purchaseOrderDao) {
        this.purchaseOrderDao = purchaseOrderDao;
    }

    public List< PurchaseOrder > findAll() {
        return purchaseOrderDao.findAll().stream()
            .filter(x -> LiveDead.ACTIVE.equals(x.getLiveDead()))
            .collect(Collectors.toList());
    }

    public PurchaseOrder findById(Integer id) {
        return purchaseOrderDao.getOne(id);
    }

    public PurchaseOrder persist(PurchaseOrder purchaseOrder) {
        if(purchaseOrder.getId()==null){
            purchaseOrder.setLiveDead(LiveDead.ACTIVE);}
        return purchaseOrderDao.save(purchaseOrder);
    }

    public boolean delete(Integer id) {
        PurchaseOrder purchaseOrder =  purchaseOrderDao.getOne(id);
        purchaseOrder.setLiveDead(LiveDead.STOP);
        purchaseOrderDao.save(purchaseOrder);
        return false;
    }

    public List< PurchaseOrder > search(PurchaseOrder purchaseOrder) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example< PurchaseOrder > purchaseRequestExample = Example.of(purchaseOrder, matcher);
        return purchaseOrderDao.findAll(purchaseRequestExample);
    }

    public List< PurchaseOrder > findByPurchaseOrderStatus(PurchaseOrderStatus purchaseOrderStatus) {
        List<PurchaseOrder> purchaseOrderLIst = purchaseOrderDao.findByPurchaseOrderStatus(purchaseOrderStatus);
        System.out.println("dsdds   "+purchaseOrderLIst.size());
        return purchaseOrderLIst;
    }


    public PurchaseOrder lastPurchaseOrder() {
        return purchaseOrderDao.findFirstByOrderByIdDesc();
    }

    public List< PurchaseOrder > findByPurchaseOrderStatusAndSupplier(PurchaseOrderStatus purchaseOrderStatus,
                                                                      Supplier supplier) {
        return purchaseOrderDao.findByPurchaseOrderStatusAndSupplier(purchaseOrderStatus, supplier);
    }

    public List< PurchaseOrder> findByCreatedAtIsBetween(LocalDateTime form, LocalDateTime to) {
    return purchaseOrderDao.findByCreatedAtIsBetween(form, to);
    }
}

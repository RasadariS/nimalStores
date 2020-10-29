package lk.nimalStores.asset.employee.entity.Enum;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Designation {
    //All station can check
    OWNER("Owner"),
    CASHIER("Cashier"),
    ACCOUNTANT("Accountant"),
    SK ("Stock Keeper)");

    private final String designation;
}


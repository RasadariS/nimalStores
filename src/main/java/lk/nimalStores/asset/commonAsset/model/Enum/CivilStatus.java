package lk.nimalStores.asset.commonAsset.model.Enum;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CivilStatus {

    MARRIED("Married"),
    UNMARRIED("UnMarried"),
    SINGLE("Single");

    private final String civilStatus;


}

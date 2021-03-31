package lk.nimal_stores.asset.discount_ratio.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiscountRatioStatus {
  ACTIVE("Active"),
  INACTIVE("Inactive"),
  TEPHOLD("Temporally Hold"),
  EX("Expired");

  private final String discountRatioStatus;
}

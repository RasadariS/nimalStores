package lk.nimal_stores.asset.item.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MainCategory {

    BAKERY("Bakery"),
    SNAKS_AND_CONFECTIONERY_ITEMS("Snaks & Confectionery Items"),
    RICE_PULSES("Rice Pulses"),
    SEA_FOOD("Sea Food"),
    MEATS("Meats"),
    TEA_AND_COFFEE("Tea & Coffee"),
    VEGETABLES_FRUITS("Vegetables & Fruits"),
    DAIRY_PRODUCTS("Dairy Products"),
    BABY_PRODUCTS("Baby Products"),
    PERSONAL_CARE("Personal Care"),
    HOUSEHOLD("Household"),
    COOKING_ESSENTIALS("Cooking Essentials"),
    BEVERAGES("Beverages"),
    DESSERT_INGREDIENTS("Dessert Ingredients"),
    SEEDS_AND_SPICES("Seeds and Spices"),
    PARTY_FAVORS("Party Favors"),
    STATIONARY("Stationary"),
    PET_PRODUCTS("Pet Products");


    private final String mainCategory;
}

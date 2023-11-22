package be.cm.security.user;

import be.cm.security.Feature;
import static be.cm.security.Feature.*;

import java.util.List;

public enum UserRole {
    ADMIN(List.of(
            ADD_ITEM,
            UPDATE_ITEM,
            VIEW_ALL_CUSTOMERS,
            VIEW_A_CUSTOMER,
            VIEW_ITEM_SHIPPING_TODAY,
            GET_ITEM_OVERVIEW

    )),
    CUSTOMER(List.of(
            ORDER_ITEM,
            VIEW_ORDER_REPORTS,
            REORDER_ORDER
    ));

    private final List<Feature> featureList;

    UserRole(List<Feature> featureList) {
        this.featureList = featureList;
    }

    public boolean containsFeature(Feature feature) {
        return featureList.contains(feature);
    }
}

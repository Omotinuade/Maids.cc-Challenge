package com.salesmgt.salesmgtsystem.enums;

import com.salesmgt.salesmgtsystem.exceptions.SalesMgtException;
import org.springframework.http.HttpStatus;

public enum Category {
    BEVERAGE,DAIRY, BAKERY, DRINKS, NONFOOD_AND_PHARMACY, MEAT_AND_FISH, VEGETABLES,ELECTRONICS,
    CLOTHING, BOOKS, FRUITS;
    public static Category fromString(String value) throws SalesMgtException {
        for (Category category : Category.values()) {
            if (category.name().equalsIgnoreCase(value)) {
                return category;
            }
        }
        throw new SalesMgtException(HttpStatus.BAD_REQUEST,"Invalid category value: " + value);
    }
}

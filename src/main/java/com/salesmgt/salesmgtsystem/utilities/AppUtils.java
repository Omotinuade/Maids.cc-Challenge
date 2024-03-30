package com.salesmgt.salesmgtsystem.utilities;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class AppUtils {

    public static final String ACCESS_TOKEN_FIELD_VALUE = "access_token";
    public static final String JWT_PREFIX = "Bearer ";
    public static final String CREATED_SUCCESS = "%s successfully created";
    public static final String RETRIEVED_SUCCESS = "%s retrieved successfully";
    public static final String UPDATED_SUCCESS = "%s updated successfully";
    public static final String DELETED_SUCCESS = "%s deleted successfully";
    public static final String CLIENT = "Client";
    public static final String PRODUCT= "Product";
    public static final String SALE= "Sales";
    public static Pageable buildPageRequest(int page, int items) {
        if (page <= 1) page = 1;
        if (items <= 0) items = 10;
        page -= 1;
        return PageRequest.of(page, items);
    }

}
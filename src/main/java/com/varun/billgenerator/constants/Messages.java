package com.varun.billgenerator.constants;

import lombok.Getter;

@Getter
public enum Messages {

    SUCCESS_MESSAGE("Success"),
    NO_PRODUCT("No Product Found"),
    INTERNAL_ERROR("Internal Server Error"),
    NO_CONTENT("No Content Found");

    private final String value;

    Messages(String value){
        this.value=value;
    }
}

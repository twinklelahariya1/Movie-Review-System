package com.coding.moviereviewservice.enums;

import java.util.Locale;

public enum Role {

    VIEWER,
    CRITIC,
    EXPERT,
    ADMIN;

    @Override
    public String toString() {
        return this.name().toLowerCase(Locale.ROOT);
    }
}

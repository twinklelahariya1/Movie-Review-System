package com.coding.moviereviewservice.enums;

import java.util.Locale;

public enum Genre {
    COMEDY,
    ROMANTIC,
    ROM_COM,
    HORROR,
    THRILLER,
    SUSPENSE,
    AMINE;

    @Override
    public String toString() {
        return super.toString().toLowerCase(Locale.ROOT);
    }
}

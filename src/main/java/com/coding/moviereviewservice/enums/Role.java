package com.coding.moviereviewservice.enums;

import java.util.Locale;

public enum Role {

    VIEWER(1),
    CRITIC(2),
    EXPERT(3),
    ADMIN(4);

    public final int weightage;

    public int compute(int rating) {
        return rating * this.weightage;
    }

    Role(int weightage) {
        this.weightage = weightage;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase(Locale.ROOT);
    }
}

package com.coding.moviereviewservice.enums;

import com.coding.moviereviewservice.service.impl.ReviewServiceImpl;

import java.util.Locale;
import java.util.function.BiFunction;

public enum Role {

    VIEWER(ReviewServiceImpl::computeViewerRating),
    CRITIC(ReviewServiceImpl::computeCriticRating),
    EXPERT(ReviewServiceImpl::computeExpertRating),
    ADMIN(ReviewServiceImpl::computeAdminRating);

    public final BiFunction<Role, Integer, Integer> rating;

    Role(BiFunction<Role, Integer, Integer> rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase(Locale.ROOT);
    }
}

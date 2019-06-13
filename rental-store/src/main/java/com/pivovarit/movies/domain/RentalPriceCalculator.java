package com.pivovarit.movies.domain;

import java.util.Map;
import java.util.Optional;

class RentalPriceCalculator {

    private final Map<String, Integer> prices;

    public RentalPriceCalculator(Map<String, Integer> prices) {
        this.prices = prices;
    }

    Optional<Integer> priceFor(String type) {
        return Optional.ofNullable(prices.get(type));
    }
}

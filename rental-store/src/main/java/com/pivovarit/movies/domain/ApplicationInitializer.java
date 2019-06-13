package com.pivovarit.movies.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class ApplicationInitializer implements CommandLineRunner {

    @Autowired
    private PricesConfiguration pricesConfiguration;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(pricesConfiguration.getPrices());
    }
}

package org.qchimp;

import java.util.UUID;

public class Rapport {
    public static long getInterestWeight(Registry registry, Customer customer, UUID aSeriesID) {
        ASeries aSeries = registry.getSeries().getASeries(aSeriesID);
        return aSeries.getTypes().stream().filter(type -> customer.getSeriesTypes().contains(type)).count();
    }

    public static long getTotalInterestWeight(Registry registry, UUID aSeriesID) {
        ASeries aSeries = registry.getSeries().getASeries(aSeriesID);
        long sum = 0;
        for (Customer customer : registry.getCustomers().allCustomers()) {
            sum += aSeries.getTypes().stream().filter(type -> customer.getSeriesTypes().contains(type)).count();
        }
        return sum;
    }
}

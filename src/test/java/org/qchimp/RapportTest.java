package org.qchimp;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class RapportTest {

    private Registry registry;

    @Test
    void shouldGiveTheInterestWeight() {
        //given

        // customer
        String name = "user1";
        LocalDate date = LocalDate.now();
        Customer customer = new Customer(name, date);
        customer.addSeriesType(SeriesType.DRAMA);

        // series
        Set<SeriesType> seriesTypes = new HashSet<>();
        seriesTypes.add(SeriesType.DRAMA);
        seriesTypes.add(SeriesType.KRIM);
        seriesTypes.add(SeriesType.TALKSHOW);

        // registry
        registry = new Registry();
        registry.addSeries("series1", seriesTypes);
        ASeries aSeries = registry.getSeries().getSeries().get(0);

        // when
        long interestWeight = Rapport.getInterestWeight(registry, customer, aSeries.getId());

        // then
        assertEquals(interestWeight, 1);
    }
    @Test
    void shouldGiveTheTotalInterestWeight() {
        //given

        // customer
        String user1 = "user1";
        LocalDate date1 = LocalDate.now();

        String user2 = "user1";
        LocalDate date2 = LocalDate.now();

        // series
        Set<SeriesType> seriesTypes = new HashSet<>();
        seriesTypes.add(SeriesType.DRAMA);
        seriesTypes.add(SeriesType.KRIM);
        seriesTypes.add(SeriesType.TALKSHOW);

        // registry
        registry = new Registry();

        registry.addCustomer(user1, date1);
        registry.addCustomer(user2, date2);
        registry.getCustomers().allCustomers().get(0).addSeriesType(SeriesType.DRAMA);
        registry.getCustomers().allCustomers().get(1).addSeriesType(SeriesType.KRIM);

        registry.addSeries("series1", seriesTypes);
        ASeries aSeries = registry.getSeries().getSeries().get(0);

        // when
        long interestWeight = Rapport.getTotalInterestWeight(registry, aSeries.getId());

        // then
        assertEquals(2,interestWeight);
    }

}

package org.qchimp;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Registry {

    private final Series series;
    private final Customers customers;

    public Registry() {
        series = new Series();
        customers = new Customers();
    }

    public void addSeries(String title, Set<SeriesType> types) throws IllegalArgumentException {
        series.addSeries(new ASeries(title, types));
    }

    public Series getSeries() {
        return series;
    }


    public void addCustomer(String name, LocalDate date) {
        customers.addCustomer(new Customer(name, date));
    }

    public Customers getCustomers() {
        return customers;
    }

    public List<Customer> getCustomersInterestedInASeries(UUID aSeriesID) {
        ASeries aSeries = series.getASeries(aSeriesID);
        return customers.getCustomersInterestedIn(aSeries.getTypes());
    }

    public Set<ASeries> getSeriesOfType(SeriesType type) {
        return series.ofType(type);
    }

    public List<ASeries> getSeriesOfTypeSorted(SeriesType type) {
        return getSeriesOfType(type).stream().sorted(Comparator.comparing(ASeries::getTitle)).collect(Collectors.toList());
    }

    public Set<Customer> getCustomersBetweenDates(LocalDate from, LocalDate to) {
        return customers.getCustomerBetweenDates(from,to);
    }
}

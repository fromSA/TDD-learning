package org.qchimp;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Customers {
    private final List<Customer> customers;

    public Customers() {
        customers = new ArrayList<>();
    }

    public boolean contains(String name, LocalDate date) {
        return customers.stream().anyMatch(customer -> customer.getName().equals(name) && customer.getDate().equals(date));
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer getCustomer(String name, LocalDate date) {
        if (contains(name, date)) {
            List<Customer> matchingCustomers = customers.stream().filter(customer ->
                    customer.getName().equals(name) && customer.getDate().equals(date)).collect(Collectors.toList());
            if(matchingCustomers.size()>1){
                throw new IllegalStateException("There are more than one customer with the matching name and added date");
            }
            else return matchingCustomers.get(0);
        }
        else throw new IllegalArgumentException("There is no customer with the matching name and added date.");
    }

    public Customer getCustomerByID(UUID id) {
        if(customers.stream().anyMatch(customer -> customer.getId().equals(id))){
            List<Customer> matchingCustomers = customers.stream().filter(customer -> customer.getId().equals(id)).collect(Collectors.toList());
            if(matchingCustomers.size()>1){
                throw new IllegalStateException("There are more than one customer with the matching id.");
            }
            else return matchingCustomers.get(0);
        }
        else throw new IllegalArgumentException("There are no customer with the matching id.");
    }

    public List<Customer> getCustomersInterestedIn(Set<SeriesType> seriesTypes) {
        return customers.stream().filter(customer -> customer.getSeriesTypes().containsAll(seriesTypes)).collect(Collectors.toList());
    }

    public Set<Customer> getCustomerBetweenDates(LocalDate from, LocalDate to) {
        return customers.stream().filter(customer -> customer.getDate().isAfter(from)&&customer.getDate().isBefore(to)).collect(Collectors.toSet());
    }

    public List<Customer> allCustomers() {
        return customers;
    }
}

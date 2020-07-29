package org.qchimp;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Customer {

    private final UUID id;
    private final String name;
    private final LocalDate date;
    private final Set<SeriesType> seriesType;

    public Customer(String name, LocalDate date) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.date = date;
        this.seriesType = new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id) &&
                name.equals(customer.name) &&
                date.equals(customer.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", series=" + seriesType +
                '}';
    }

    public void addSeriesType(SeriesType type) {
        seriesType.add(type);
    }

    public Set<SeriesType> getSeriesTypes() {
        return seriesType;
    }

    public void removeSeriesType(SeriesType type) {
        seriesType.remove(type);
    }
}

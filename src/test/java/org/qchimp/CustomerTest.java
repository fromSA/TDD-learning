package org.qchimp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

public class CustomerTest {

    Customer customer;
    String name;
    LocalDate date;

    @BeforeEach
    void setUp() {

    }


    @Test
    void testEquals() {
        name = "someUser";
        date = LocalDate.now();
        customer = new Customer(name, date);

        String name2 = "name";
        LocalDate date2 = LocalDate.now();
        Customer customer2 = new Customer(name2, date2);

        assertNotEquals(customer, customer2);

    }

    @Test
    void testHashCode() {
        name = "someUser";
        date = LocalDate.now();
        customer = new Customer(name, date);

        String name2 = "name";
        LocalDate date2 = LocalDate.now();
        Customer customer2 = new Customer(name2, date2);

        assertNotEquals(customer.hashCode(), customer2.hashCode());
    }

    @Test
    void getId() {
        name = "someUser";
        date = LocalDate.now();
        customer = new Customer(name, date);

        String name2 = "name";
        LocalDate date2 = LocalDate.now();
        Customer customer2 = new Customer(name2, date2);

        assertNotEquals(customer.getId(), customer2.getId());
    }

    @Test
    void getName() {
        name = "someUser";
        date = LocalDate.now();
        customer = new Customer(name, date);
        assertEquals(name, customer.getName());
    }

    @Test
    void getDate() {
        name = "someUser";
        date = LocalDate.now();
        customer = new Customer(name, date);
        assertEquals(date, customer.getDate());
    }

    @Test
    void shouldAddASeriesTypeToCustomerPreferences() {
        // given
        name = "user1";
        date = LocalDate.now();
        customer = new Customer(name, date);

        //when
        customer.addSeriesType(SeriesType.DRAMA);

        //then
        Set<SeriesType> seriesTypes = customer.getSeriesTypes();
        assertTrue(seriesTypes.contains(SeriesType.DRAMA));
    }

    @Test
    void shouldRemoveASeriesTypeFromCustomerPreferences() {
        // given
        name = "user1";
        date = LocalDate.now();
        customer = new Customer(name, date);

        //when
        customer.addSeriesType(SeriesType.DRAMA);
        Set<SeriesType> seriesTypes = customer.getSeriesTypes();
        assumeTrue(seriesTypes.contains(SeriesType.DRAMA));
        customer.removeSeriesType(SeriesType.DRAMA);

        //then
        assertFalse(seriesTypes.contains(SeriesType.DRAMA));
    }
}

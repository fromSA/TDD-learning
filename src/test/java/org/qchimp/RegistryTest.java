package org.qchimp;

import com.google.common.collect.Ordering;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class RegistryTest {

    private Registry registry;

    @BeforeEach
    void SetUp() {
        registry = new Registry();
    }

    @Test
    void shouldRegisterOneValidSeries() {
        // given
        Set<SeriesType> types = new HashSet<>();

        // when
        types.add(SeriesType.DRAMA);
        String title = "someSeries";
        registry.addSeries(title, types);

        // then
        assertTrue(registry.getSeries().containsSeries(title, types));
    }

    @Test
    void shouldThrowExceptionForIllegalASeries() {

        // given
        Set<SeriesType> types = new HashSet<>();

        // when
        String title = "someSeries";

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            registry.addSeries(title, types);
        });
    }

    @Test
    void shouldRegisterACustomer() {
        // given
        String name = "someUser";
        LocalDate date = LocalDate.now();

        // when
        registry.addCustomer(name, date);

        // then
        assertTrue(registry.getCustomers().contains(name, date));
    }

    @Test
    void shouldReturnACustomerByID() {


        // given
        String name = "firstCustomer";
        LocalDate date = LocalDate.now();

        registry.addCustomer(name, date);
        registry.addCustomer("sendCustomer", LocalDate.now());
        registry.addCustomer("thirdCustomer", LocalDate.now());
        registry.addCustomer("fourthCustomer", LocalDate.now());

        Customer firstCustomer = registry.getCustomers().getCustomer(name, date);

        // when
        Customer customer = registry.getCustomers().getCustomerByID(firstCustomer.getId());

        // then
        assertEquals(customer, firstCustomer);
    }

    @Test
    void shouldGetCustomersInterestedInASeries() {
        // given

        // a series
        Set<SeriesType> types = new HashSet<>();
        types.add(SeriesType.DRAMA);
        String name = "name";
        registry.addSeries(name,types);
        ASeries aSeries = registry.getSeries().getSeries().get(0);

        // customers
        String customer1 = "firstCustomer";
        LocalDate date = LocalDate.now();
        registry.addCustomer(customer1, date);
        registry.addCustomer("sendCustomer", LocalDate.now());
        registry.addCustomer("thirdCustomer", LocalDate.now());
        registry.addCustomer("fourthCustomer", LocalDate.now());

        Customer testCustomer = registry.getCustomers().getCustomer(customer1, date);
        testCustomer.addSeriesType(SeriesType.DRAMA);

        // when
        List<Customer> customersWithSeries = registry.getCustomersInterestedInASeries(aSeries.getId());

        // then
        assertAll(() -> assertTrue(customersWithSeries.contains(testCustomer)),
                () -> assertEquals(1, customersWithSeries.size()));

    }

    @Test
    void shouldGetSeriesOfAType() {
        // given
        Set<SeriesType> types = new HashSet<>();
        types.add(SeriesType.DRAMA);
        registry.addSeries("Hello", types);

        Set<SeriesType> types1 = new HashSet<>();
        types1.add(SeriesType.HUMOR);
        registry.addSeries("series1", types1);
        Set<SeriesType> types2 = new HashSet<>();
        types2.add(SeriesType.KRIM);
        registry.addSeries("series2", types2);
        Set<SeriesType> types3 = new HashSet<>();
        types3.add(SeriesType.DRAMA);
        registry.addSeries("series3", types3);

        // when

        Set<ASeries> seriesOfType = registry.getSeriesOfType(SeriesType.DRAMA);

        //
        assertAll(() -> assertEquals(2, seriesOfType.size()),
                () -> assertTrue(seriesOfType.stream().anyMatch(aSeries -> aSeries.getTitle().equals("Hello")))
        );
    }

    @Test
    void shouldGetSeriesOfTypeSortedAlphabetically() {
        // given
        Set<SeriesType> types = new HashSet<>();
        types.add(SeriesType.DRAMA);
        registry.addSeries("A", types);

        Set<SeriesType> types1 = new HashSet<>();
        types1.add(SeriesType.HUMOR);
        registry.addSeries("B", types1);
        Set<SeriesType> types2 = new HashSet<>();
        types2.add(SeriesType.KRIM);
        registry.addSeries("C", types2);
        Set<SeriesType> types3 = new HashSet<>();
        types3.add(SeriesType.DRAMA);
        registry.addSeries("D", types3);
        Set<SeriesType> types4 = new HashSet<>();
        types4.add(SeriesType.DRAMA);
        registry.addSeries("H", types4);

        // when

        List<ASeries> seriesOfType = registry.getSeriesOfTypeSorted(SeriesType.DRAMA);

        //
        assertAll(() -> assertEquals(3, seriesOfType.size()),
                () -> assertTrue(Ordering.from(Comparator.comparing(ASeries::getTitle)).isOrdered(seriesOfType))
        );
    }

    @Test
    void shouldGetSeriesOfTypeUnSortedAlphabetically() {
        // given
        Set<SeriesType> types = new HashSet<>();
        types.add(SeriesType.DRAMA);
        registry.addSeries("A", types);

        Set<SeriesType> types1 = new HashSet<>();
        types1.add(SeriesType.HUMOR);
        registry.addSeries("B", types1);
        Set<SeriesType> types2 = new HashSet<>();
        types2.add(SeriesType.KRIM);
        registry.addSeries("C", types2);
        Set<SeriesType> types3 = new HashSet<>();
        types3.add(SeriesType.DRAMA);
        registry.addSeries("D", types3);
        Set<SeriesType> types4 = new HashSet<>();
        types4.add(SeriesType.DRAMA);
        registry.addSeries("H", types4);

        // when

        Set<ASeries> seriesOfType = registry.getSeriesOfType(SeriesType.DRAMA);

        List<ASeries> seriesOfTypeList = new ArrayList<>();
        seriesOfTypeList.addAll(seriesOfType);
        //
        assertAll(() -> assertEquals(3, seriesOfType.size()),
                () -> assertFalse(Ordering.from(Comparator.comparing(ASeries::getTitle)).isOrdered(seriesOfTypeList))
        );
    }

    @Test
    void shouldReturnCustomersRegisteredBetweenDates() {
        // given
        LocalDate date;
        for (int i = 0; i < 10; i++) {
            date = LocalDate.of(2020, i + 1, 1);
            registry.addCustomer("user" + i, date);
        }

        // when
        LocalDate from = LocalDate.of(2020, 1, 1);
        LocalDate to = LocalDate.of(2020, 5, 1);
        Set<Customer> customersBetweenDates = registry.getCustomersBetweenDates(from, to);

        System.out.println(LocalDate.of(2020,3,1).isBefore(from));
        System.out.println(Arrays.deepToString(customersBetweenDates.toArray()));
        // then
        assertAll(() -> assertEquals(3, customersBetweenDates.size()),
                () -> assertTrue(customersBetweenDates.stream().noneMatch(customer -> customer.getDate().isBefore(from) && customer.getDate().isAfter(to)))

        );


    }
}

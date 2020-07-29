package org.qchimp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ASeriesTest {

    Set<SeriesType> types;
    String name;
    ASeries testSeries;

    @BeforeEach
    void setUp() {
        types = new HashSet<>();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTypes() {
        types.add(SeriesType.DRAMA);
        name = "someSeries";
        testSeries = new ASeries(name,types);
        assertEquals(types, testSeries.getTypes());
    }

    @Test
    void addTypes() {
        types.add(SeriesType.DRAMA);
        name = "someSeries";
        testSeries = new ASeries(name, types);
        SeriesType testType = SeriesType.DOKUMENTAR;
        testSeries.addType(testType);
        assertEquals(true, testSeries.getTypes().contains(testType));
    }

    @Test
    void removeType() {
        types.add(SeriesType.DRAMA);
        name = "someSeries";
        testSeries = new ASeries(name, types);
        SeriesType testType = SeriesType.DOKUMENTAR;
        testSeries.removeType(testType);
        assertEquals(false, testSeries.getTypes().contains(testType));
    }

    @Test
    void getTitle() {
        types.add(SeriesType.DRAMA);
        name = "someSeries";
        testSeries = new ASeries(name, types);
        assertEquals(name, testSeries.getTitle());
    }


    @Test
    void getId() {
        types.add(SeriesType.DRAMA);
        name = "someSeries";
        testSeries = new ASeries(name, types);
        ASeries testSeries2 = new ASeries(name, types);

        assertNotEquals(testSeries2.getId(), testSeries.getId());

    }

    @Test
    void testEquals() {
        types.add(SeriesType.DRAMA);
        name = "someSeries";
        testSeries = new ASeries(name, types);
        ASeries testSeries2 = new ASeries(name, types);

        assertNotEquals(testSeries, testSeries2);
    }

    @Test
    void testHashCode() {
        types.add(SeriesType.DRAMA);
        name = "someSeries";
        testSeries = new ASeries(name, types);
        ASeries testSeries2 = new ASeries(name, types);

        assertNotEquals(testSeries.hashCode(), testSeries2.hashCode());
    }
}
package org.qchimp;

import java.util.*;
import java.util.stream.Collectors;


public class Series {

    public Series() {
        this.series = new ArrayList<>();
    }

    private final List<ASeries> series;

    public boolean containsSeries(String title, Set<SeriesType> types) {
        return series.stream().anyMatch(aSeries -> aSeries.getTitle().equals(title) && aSeries.getTypes().equals(types));

    }

    public void addSeries(ASeries aSeries) {
        series.add(aSeries);
    }

    public Set<ASeries> ofType(SeriesType type) {
        return series.stream().filter(aSeries -> aSeries.getTypes().contains(type)).collect(Collectors.toSet());
    }

    public List<ASeries> getSeries() {
        return series;
    }

    public ASeries getASeries(UUID ID) {
        List<ASeries> collectionOfSeriesMatchingID = series.stream().filter(aSeries -> aSeries.getId().equals(ID)).collect(Collectors.toList());
        if (collectionOfSeriesMatchingID.size()>1){
            throw new IllegalStateException("There are more than one series with the same ID");
        }else return collectionOfSeriesMatchingID.get(0);
    }
}

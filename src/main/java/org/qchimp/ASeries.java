package org.qchimp;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class ASeries {
    private final Set<SeriesType> types;
    private final UUID id;
    private final String title;

    public ASeries(String title,Set<SeriesType> types) {
        if (types == null || title == null || types.size() < 1 || title.length() == 0) throw
                new IllegalArgumentException("A Series must have at least have one type and a title with at least one character.");
        this.types = types;
        this.title = title;
        this.id = UUID.randomUUID();
    }

    public Set<SeriesType> getTypes() {
        return this.types;
    }

    public void addType(SeriesType type) {
        types.add(type);
    }

    public void removeType(SeriesType type) {
        types.remove(type);
    }

    public String getTitle() {
        return title;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ASeries series = (ASeries) o;
        return types.equals(series.types) &&
                id.equals(series.id) &&
                title.equals(series.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

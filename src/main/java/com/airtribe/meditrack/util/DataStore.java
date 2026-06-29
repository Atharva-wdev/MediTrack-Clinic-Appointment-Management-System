package com.airtribe.meditrack.util;

import java.util.ArrayList;
import java.util.List;

public class DataStore<T> {

    private final List<T> records = new ArrayList<>();

    public void add(T record) {
        records.add(record);
    }

    public void remove(T record) {
        records.remove(record);
    }

    public List<T> getAll() {
        return records;
    }

    public boolean isEmpty() {
        return records.isEmpty();
    }

    public int size() {
        return records.size();
    }
}
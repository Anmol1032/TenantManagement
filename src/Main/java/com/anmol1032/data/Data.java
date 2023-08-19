package com.anmol1032.data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

public class Data implements Serializable {
    @Serial
    private static final long serialVersionUID = 1293489384L;

    public ArrayList<HouseData> houses;

    public Data() {
        houses = new ArrayList<>();
        Date date = new Date();
    }

    public void add(HouseData houseData) {
        AtomicReference<Boolean> found = new AtomicReference<>(false);
        houses.forEach(houseData2 -> {
            if (houseData2.name.equals(houseData.name))
                found.set(true);
        });

        if (!found.get()) {
            houses.add(houseData);
        }
    }

    public HouseData find(String name) {
        AtomicReference<HouseData> houseDataAtomicReference = new AtomicReference<>();

        houses.forEach(houseData -> {
            if (name.equals(houseData.name)) houseDataAtomicReference.set(houseData);
        });

        return houseDataAtomicReference.get();
    }

    public void update() {
        houses.forEach(houseData -> houseData.update(System.currentTimeMillis()));
    }

    @Override
    public String toString() {
        return "Data{\n" + houses +
                '}';
    }
}

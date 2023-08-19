package com.anmol1032.data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class HouseData implements Serializable {
    @Serial
    private static final long serialVersionUID = 12934879893L;
    public ArrayList<Tenant> tenants;
    String name;

    public HouseData(String name) {
        this.tenants = new ArrayList<>();
        this.name = name;
    }

    public void add(Tenant tenant) {
        AtomicReference<Boolean> found = new AtomicReference<>(false);
        tenants.forEach(tenant1 -> {
            if (tenant1.name.equals(tenant.name))
                found.set(true);
        });

        if (!found.get()) {
            tenants.add(tenant);
        }
    }

    public Tenant find(String name) {
        AtomicReference<Tenant> tenantAtomicReference = new AtomicReference<>();

        tenants.forEach(houseData -> {
            if (name.equals(houseData.name)) tenantAtomicReference.set(houseData);
        });

        return tenantAtomicReference.get();
    }

    public void update(long t) {
        tenants.forEach(tenant -> tenant.update(t));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\tHouseData: name=" + name + ", " +
                "tenants=\n" + tenants;
    }
}

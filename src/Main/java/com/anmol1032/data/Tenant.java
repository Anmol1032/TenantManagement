package com.anmol1032.data;

import java.io.Serial;
import java.io.Serializable;

public class Tenant implements Serializable {
    @Serial
    private static final long serialVersionUID = 129347889384L;
    long money;
    long moneyPerMonth;
    String name;
    long lastChange;

    public Tenant(String name, long moneyPerMonth) {
        lastChange = System.currentTimeMillis();
        money = 0;
        this.moneyPerMonth = moneyPerMonth;
        this.name = name;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMpM() {
        return moneyPerMonth;
    }

    public void payed(long money) {
        this.money -= money;
    }

    public void extraToPay(long money) {
        this.money += money;
    }

    public void update(long t) {
        long month = 0X9A7EC800L; //30.5*24*60*60*1000 = 2635200000L = 0x9a7ec800L
        if (t - lastChange > month) {
            lastChange += month;
            money += moneyPerMonth;
            update(t);
        }


    }

    @Override
    public String toString() {
        return "\t\tTenant{" +
                "money=" + money +
                ", moneyPerMonth=" + moneyPerMonth +
                ", name='" + name + '\'' +
                ", lastChange=" + lastChange +
                '}' + '\n';
    }
}

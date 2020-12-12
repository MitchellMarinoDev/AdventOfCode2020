package com.marinodev;

// using a class in order to map to 2 values
// not using generics to avoid boxing and unboxing
public class BagAmountPair {
    public String bag;
    public int amount;

    public BagAmountPair(String bag, int amount) {
        this.bag = bag;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BagAmountPair{" +
                "bag='" + bag + '\'' +
                ", amount=" + amount +
                '}';
    }
}

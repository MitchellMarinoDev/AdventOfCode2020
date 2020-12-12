package com.marinodev;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Bag {
    public String name;

    public List<Bag>     parents;
    public List<Bag>     children;
    public List<Integer> numOfChildrenCanHold; // number of each type of bag it can hold

    public Bag(String name) {
        this.name = name;
        parents  = new ArrayList<>();
        children = new ArrayList<>();
        numOfChildrenCanHold = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Bag{" +
                "name=\"" + name + '\"' +
                ", Number of Children=" + children.size() +
                ", Number of Parents=" + parents.size() +
                '}';
    }

    // hash to remove dupes
    private int deepGetNumParents(HashSet<Integer> hashCodes) {
        // use hash codes to make sure a parent isn't checked twice
        hashCodes.add(this.hashCode());
        if (parents.size() == 0) {
            return 0;
        }
        int num = 0;
        for (Bag parent : parents) {
            // if the hash is not in the set, add it, then count it's parents
            if (!hashCodes.contains(parent.hashCode())) {
                num++;
                num += parent.deepGetNumParents(hashCodes);
            }
        }
        return num;
    }

    public int deepGetNumParents() {
        HashSet<Integer> hashCodes = new HashSet<>();
        return deepGetNumParents(hashCodes);
    }

    // Don't Hash. Take all Children
    public int deepGetNumChildren() {
        if (children.size() == 0) {
            return 0;
        }
        int num = 0;
        for (int i = 0; i < children.size(); i++) {
            num += numOfChildrenCanHold.get(i);
            num += children.get(i).deepGetNumChildren() * numOfChildrenCanHold.get(i);
        }
        return num;
    }

}

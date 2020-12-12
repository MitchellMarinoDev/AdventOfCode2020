package com.marinodev;

import java.util.List;

public class Node {
    public int count;
    public String name;

    public Node parent;
    public List<Node> children;

    public Node() {}

    public Node(String name, int count, Node parent) {
        this.count = count;
        this.name = name;
        this.parent = parent;
    }
}

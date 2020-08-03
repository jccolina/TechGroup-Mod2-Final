package datastructures.selfbalancedbst;

import datastructures.arraylist.MyArrayList;
import datastructures.linkedlist.MyLinkedList;

public class MyColorNode {
    public enum Color{
        RED,
        BLACK
    }
    private double value;
    private MyLinkedList elements;
    private Color color;
    private MyColorNode left;
    private MyColorNode right;
    private MyColorNode parent;

    public MyColorNode(double value, Object element) {
        this.value = value;
        this.color = Color.RED;
        this.elements = new MyLinkedList();
        this.elements.add(element);
    }

    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public MyColorNode getLeft() {
        return left;
    }

    public void setLeft(MyColorNode left) {
        this.left = left;
    }

    public MyColorNode getRight() {
        return right;
    }

    public void setRight(MyColorNode right) {
        this.right = right;
    }

    public MyColorNode getParent() {
        return parent;
    }

    public void setParent(MyColorNode parent) {
        this.parent = parent;
    }

    public void add(MyLinkedList elements){
        this.elements.addAll(elements);
    }

    public MyLinkedList getElements(){
        return this.elements;
    }
}

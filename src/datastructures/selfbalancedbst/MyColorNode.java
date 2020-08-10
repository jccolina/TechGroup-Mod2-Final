package datastructures.selfbalancedbst;

import datastructures.linkedlist.MyLinkedList;

public class MyColorNode<T> {
    public enum Color{
        RED,
        BLACK
    }
    private double value;
    private MyLinkedList<T> elements;
    private Color color;
    private MyColorNode<T> left;
    private MyColorNode<T> right;
    private MyColorNode<T> parent;

    public MyColorNode(double value, T element) {
        this.value = value;
        this.color = Color.RED;
        this.elements = new MyLinkedList<>();
        this.elements.add(element);
    }

    public MyColorNode(double value) {
        this.value = value;
        this.color = Color.RED;
        this.elements = new MyLinkedList<>();
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

    public MyColorNode<T> getLeft() {
        return left;
    }

    public void setLeft(MyColorNode<T> left) {
        this.left = left;
    }

    public MyColorNode<T> getRight() {
        return right;
    }

    public void setRight(MyColorNode<T> right) {
        this.right = right;
    }

    public MyColorNode<T> getParent() {
        return parent;
    }

    public void setParent(MyColorNode<T> parent) {
        this.parent = parent;
    }

    public void add(MyLinkedList<T> elements){
        this.elements.addAll(elements);
    }

    public MyLinkedList<T> getElements(){
        return this.elements;
    }
}

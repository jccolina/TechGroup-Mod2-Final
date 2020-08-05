package datastructures.selfbalancedbst;

import datastructures.linkedlist.MyLinkedList;
import datastructures.selfbalancedbst.MyColorNode.Color;

public class MyRedBlackBST<T> {

    public static final MyColorNode NULLT = new MyColorNode<>(-1);
    private MyColorNode<T> root;
    private int size;

    public int getSize() {
        return size;
    }

    public boolean add(double value, T element) {
        MyColorNode<T> newNode = new MyColorNode<>(value, element);
        newNode.setLeft(NULLT);
        newNode.setRight(NULLT);
        boolean result;
        if (root == null) {
            root = newNode;
            root.setParent(NULLT);
            root.getParent().setColor(Color.BLACK);
            this.size++;
            result = true;
        } else {
            result = add(root, newNode);
            if (result) {
                this.size++;
            }
        }
        if (result) {
            this.fixTree(newNode);
        }
        return result;
    }

    private void fixTree(MyColorNode node) {
        while (node.getParent().getColor().equals(Color.RED)) {
            if (node.getParent().getParent().getRight() == node.getParent()) {
                MyColorNode uncle = node.getParent().getParent().getLeft();
                if (uncle.getColor().equals(Color.RED)) {
                    node.getParent().setColor(Color.BLACK);
                    uncle.setColor(Color.BLACK);
                    node.getParent().getParent().setColor(Color.RED);
                    node = node.getParent().getParent();
                    continue;
                }
                if (node.getParent().getLeft() == node) {
                    node = node.getParent();
                    rightRotate(node);
                }
                node.getParent().setColor(Color.BLACK);
                node.getParent().getParent().setColor(Color.RED);
                leftRotate(node.getParent().getParent());
            } else {
                MyColorNode uncle = node.getParent().getParent().getRight();
                if (uncle.getColor().equals(Color.RED)) {
                    node.getParent().setColor(Color.BLACK);
                    uncle.setColor(Color.BLACK);
                    node.getParent().getParent().setColor(Color.RED);
                    node = node.getParent().getParent();
                    continue;
                }
                if (node.getParent().getLeft() == node) {
                    node = node.getParent();
                    rightRotate(node);
                }
                node.getParent().setColor(Color.BLACK);
                node.getParent().getParent().setColor(Color.RED);
                rightRotate(node.getParent().getParent());
            }
        }
        root.setColor(Color.BLACK);
    }

    public MyColorNode<T> rightRotate(MyColorNode<T> node) {
        MyColorNode parent = node.getParent();
        MyColorNode aux = node.getLeft();
        aux.setParent(parent);
        node.setLeft(aux.getRight());
        aux.setRight(node);
        node.setParent(aux);
        node.getLeft().setParent(node);
        if (parent != NULLT) {
            if (parent.getRight() == node) {
                parent.setRight(aux);
            } else {
                parent.setLeft(aux);
            }
        } else {
            root = aux;
        }
        return aux;
    }

    protected MyColorNode<T> leftRotate(MyColorNode<T> node) {
        MyColorNode<T> parent = node.getParent();
        MyColorNode<T> aux = node.getRight();
        aux.setParent(parent);
        node.setRight(aux.getLeft());
        aux.setLeft(node);
        node.setParent(aux);
        node.getRight().setParent(node);
        if (parent != NULLT) {
            if (parent.getRight() == node) {
                parent.setRight(aux);
            } else {
                parent.setLeft(aux);
            }
        } else {
            root = aux;
        }
        return aux;
    }

    private boolean add(MyColorNode<T> cursor, MyColorNode<T> newNode) {
        boolean result = false;
        if (newNode.getValue() > cursor.getValue()) {
            if (cursor.getRight() == NULLT) {
                newNode.setParent(cursor);
                cursor.setRight(newNode);
                result = true;
            } else {
                return add(cursor.getRight(), newNode);
            }
        } else if (newNode.getValue() < cursor.getValue()) {
            if (cursor.getLeft() == NULLT) {
                newNode.setParent(cursor);
                cursor.setLeft(newNode);
                result = true;
            } else {
                return add(cursor.getLeft(), newNode);
            }
        } else {
            cursor.add(newNode.getElements());
        }
        return result;
    }

    public MyColorNode<T> getRoot() {
        return this.root;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");

        visit(root, builder);

        builder.append("]");
        return builder.toString();
    }

    private void visit(MyColorNode<T> cursor, StringBuilder builder) {
        if (cursor == NULLT) return;

        visit(cursor.getLeft(), builder);
        builder.append(cursor.getValue());
        visit(cursor.getRight(), builder);
    }

    public void clear(){
        this.root = null;
        this.size = 0;
    }

    public MyLinkedList<T> getValuesLessThan(double maxValue){
        MyColorNode<T> currentNode = getRoot();
        MyLinkedList<T> elementsFound =  new MyLinkedList<>();
        while(currentNode != NULLT){
            if(currentNode.getValue() <= maxValue){
                elementsFound.addAll(currentNode.getElements());
            }
            currentNode = currentNode.getLeft();
        }
        return elementsFound;
    }

    public MyLinkedList<T> getValuesGreaterThan(double minValue){
        MyColorNode<T> currentNode = getRoot();
        MyLinkedList<T> elementsFound =  new MyLinkedList<>();
        while(currentNode != NULLT){
            if(currentNode.getValue() >= minValue){
                elementsFound.addAll(currentNode.getElements());
            }
            currentNode = currentNode.getRight();
        }
        return elementsFound;
    }

    public MyLinkedList<T> getValuesLeftOpenInterval(double maxValue, double minValue){
        MyLinkedList<T> foundElements =  new MyLinkedList<>();
        getValuesLeftOpenInterval(maxValue, minValue, getRoot(), foundElements);
        return foundElements;
    }

    private void getValuesLeftOpenInterval(double maxValue, double minValue, MyColorNode<T> currentNode, MyLinkedList<T> foundElements){
        if(currentNode == NULLT) return;
        if(currentNode.getValue() <= maxValue){
            getValuesLeftOpenInterval(maxValue, minValue, currentNode.getRight(), foundElements);
        } else if(currentNode.getValue() > minValue){
            getValuesLeftOpenInterval(maxValue, minValue, currentNode.getLeft(), foundElements);
        } else {
            foundElements.addAll(currentNode.getElements());
            getValuesLeftOpenInterval(maxValue, minValue, currentNode.getRight(), foundElements);
            getValuesLeftOpenInterval(maxValue, minValue, currentNode.getLeft(), foundElements);
        }
    }

    public MyLinkedList<T> getValuesOpenInterval(double minValue, double maxValue){
        MyLinkedList<T> foundElements = new MyLinkedList<>();
        getValuesOpenInterval(minValue, maxValue, getRoot(), foundElements);
        return foundElements;
    }

    private void getValuesOpenInterval(double minValue, double maxValue, MyColorNode<T> currentNode, MyLinkedList<T> foundElements){
        if(currentNode == MyRedBlackBST.NULLT) return;
        if(currentNode.getValue() <= minValue){
            getValuesOpenInterval(minValue, maxValue, currentNode.getRight(), foundElements);
        } else if(currentNode.getValue() >= maxValue){
            getValuesOpenInterval(minValue, maxValue, currentNode.getLeft(), foundElements);
        } else {
            foundElements.addAll(currentNode.getElements());
            getValuesOpenInterval(minValue, maxValue, currentNode.getRight(), foundElements);
            getValuesOpenInterval(minValue, maxValue, currentNode.getLeft(), foundElements);
        }
    }

    public MyLinkedList<T> getHigherValuesOver(double minValue) {
        MyColorNode<T> currentNode = getRoot();
        MyLinkedList<T> foundElements = new MyLinkedList<>();
        if(currentNode != NULLT){
            while (currentNode.getRight() != NULLT){
                currentNode = currentNode.getRight();
            }
            if(currentNode.getValue() > minValue){
                foundElements.addAll(currentNode.getElements());
            }
        }
        return foundElements;
    }
}

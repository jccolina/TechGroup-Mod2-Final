package datastructures.selfbalancedbst;

import datastructures.selfbalancedbst.MyColorNode.Color;

public class MyRedBlackBST {

    public static final MyColorNode NULLT = new MyColorNode(-1, new Object());
    private MyColorNode root;
    private int size;

    public int getSize() {
        return size;
    }

    public boolean add(double value, Object element) {
        MyColorNode newNode = new MyColorNode(value, element);
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

    public MyColorNode rightRotate(MyColorNode node) {
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

    protected MyColorNode leftRotate(MyColorNode node) {
        MyColorNode parent = node.getParent();
        MyColorNode aux = node.getRight();
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

    private boolean add(MyColorNode cursor, MyColorNode newNode) {
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

    public MyColorNode getRoot() {
        return this.root;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");

        visit(root, builder);

        builder.append("]");
        return builder.toString();
    }

    private void visit(MyColorNode cursor, StringBuilder builder) {
        if (cursor == NULLT) return;

        visit(cursor.getLeft(), builder);
        builder.append(cursor.getValue());
        visit(cursor.getRight(), builder);
    }

    public void clear(){
        this.root = null;
        this.size = 0;
    }
}

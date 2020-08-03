package co.tdude.COEN352Assignment2;

public class Node<E extends Comparable<E>> {
    private E data;
    private Node<E> next;
    private Node<E> prev;

    public Node() {
    }

    public Node(Node copy) {
        this.data = (E) copy.getData();
        this.next = copy.getNext();
        this.prev = copy.getPrev();
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public Node<E> getPrev() {
        return prev;
    }

    public void setPrev(Node<E> prev) {
        this.prev = prev;
    }
}

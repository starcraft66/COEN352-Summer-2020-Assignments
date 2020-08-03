package co.tdude.COEN352Assignment2;

public class DoubleLinkedList<E extends Comparable<E>> implements ADTLList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;
    private Node<E> curr;


    @Override
    public void clear() {
        this.curr = null;
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void insert(E item) {
        Node<E> n = new Node<>();
        n.setData(item);
        n.setNext(this.curr);
        n.setPrev(this.curr.getPrev());
        this.curr = n;
        if (this.head == null)
            this.head = n;
        if (this.tail == null)
            this.tail = n;
        this.size++;
    }

    @Override
    public void append(E item) {
        System.out.println("Append " + item);
        Node<E> n = new Node<>();
        n.setData(item);
        n.setNext(null);
        n.setPrev(this.tail);
        if (this.tail != null)
            this.tail.setNext(n);
        this.tail = n;
        if (this.head == null)
            this.head = n;
        this.size++;
        if (this.curr == null) {
            this.curr = this.head;
        } else {
            this.curr = n;
        }
    }

    @Override
    public E remove() {
        if (this.curr == null) {
            System.out.println("curr is null");
            return null;
        }
        if (this.curr == this.head) {
            this.head = null;
        }
        if (this.tail == this.head) {
            this.tail = null;
        }
        Node<E> copy = new Node<>(this.curr);
        if (this.curr != this.tail) {
            this.curr = this.curr.getNext();
            this.curr.setPrev(copy.getPrev());
        } else {
            this.tail = this.tail.getPrev();
            if (this.tail != null) {
                this.tail.setNext(null);
            }
        }
        this.size--;
        return copy.getData();
    }

    @Override
    public void moveToStart() {
        this.curr = this.head;
    }

    @Override
    public void moveToEnd() {
        this.moveToStart();
        while (this.curr.getNext() != null) {
            this.curr = this.curr.getNext();
        }
    }

    @Override
    public void prev() {
        this.curr = this.curr.getPrev();
    }

    @Override
    public void next() {
        this.curr = this.curr.getNext();
    }

    @Override
    public int length() {
        return this.size;
    }

    @Override
    public Node<E> currPos() {
        return this.curr;
    }

    @Override
    public void moveToPos(int pos) {
        this.moveToStart();
        for (int i = 0; i < pos; i++) {
            this.curr = this.curr.getNext();
        }
    }

    @Override
    public E getValue() {
        return curr.getData();
    }
}

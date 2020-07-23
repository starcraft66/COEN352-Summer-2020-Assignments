package co.tdude.COEN352Assignment1;

// The Node class is technically double-linked but we
// pretend it isn't and never use the previous pointer


public class SingleLinkedList<E> implements ADTLList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;
    private Node<E> curr;


    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void insert(E item) {
        Node<E> n = new Node<>();
        n.setData(item);
        n.setNext(this.curr);
        this.curr = n;
        if (this.head == null)
            this.head = n;
        if (this.tail == null)
            this.tail = n;
        this.size++;
    }

    @Override
    public void append(E item) {
        Node<E> n = new Node<>();
        n.setData(item);
        n.setNext(null);
        if (this.tail != null) {
            this.tail.setNext(n);
        }
        this.tail = n;
        if (this.head == null) {
            this.head = n;
        }
        this.size++;
        if (this.curr == null) {
            this.curr = this.head;
        }
    }

    @Override
    public E remove() {
        if (this.curr == null) {
            return null;
        }
        if (this.curr == this.head) {
            this.head = null;
        }
        if (this.tail == this.head) {
            this.tail = null;
        }
        Node<E> copy = new Node<>(this.curr);
        this.curr = this.curr.getNext();
        this.size--;
        return copy.getData();
    }

    @Override
    public void moveToStart() {
        this.curr = this.head;
    }

    @Override
    public void moveToEnd() {
        while (this.curr.getNext() != null) {
            this.curr = this.curr.getNext();
        }
    }

    @Override
    public void prev() {
        Node<E> temp = this.curr;
        this.moveToStart();
        while (this.curr != null) {
            if (this.curr.getNext().getData().equals(temp.getData())) {
                break;
            }
            this.curr = this.curr.getNext();
        }
        this.curr = temp;
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

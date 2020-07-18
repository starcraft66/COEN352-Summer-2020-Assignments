package co.tdude.COEN352Assignment1;

public class SingleNode<E> {
    private E data;
    private SingleNode<E> next;
    private SingleNode<E> prev;

    public SingleNode() {
    }

    public SingleNode(SingleNode copy) {
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

    public SingleNode<E> getNext() {
        return next;
    }

    public void setNext(SingleNode<E> next) {
        this.next = next;
    }

    public SingleNode<E> getPrev() {
        return prev;
    }

    public void setPrev(SingleNode<E> prev) {
        this.prev = prev;
    }
}

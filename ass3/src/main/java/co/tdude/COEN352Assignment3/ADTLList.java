package co.tdude.COEN352Assignment3;

public interface ADTLList<E extends Comparable<E>> {
    void clear();

    void insert(E item);

    void append(E item);

    E remove();

    void moveToStart();

    void moveToEnd();

    void prev();

    void next();

    int length();

    Node<E> currPos();

    void moveToPos(int pos);

    E getValue();
}

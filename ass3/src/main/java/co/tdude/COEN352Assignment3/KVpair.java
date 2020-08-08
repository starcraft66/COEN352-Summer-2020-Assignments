package co.tdude.COEN352Assignment3;

/**
 * Source code example for "A Practical Introduction to Data
 * Structures and Algorithm Analysis, 3rd Edition (Java)"
 * by Clifford A. Shaffer
 * Copyright 2008-2011 by Clifford A. Shaffer
 */

import java.util.Objects;

/**
 * Container class for a key-value pair
 */
class KVpair<Key, E extends Comparable<E>> implements Comparable<KVpair<Key, E>> {
    private final Key k;
    private final E e;

    /**
     * Constructors
     */
    KVpair() {
        k = null;
        e = null;
    }

    KVpair(Key kval, E eval) {
        k = kval;
        e = eval;
    }

    /**
     * Data member access functions
     */
    public Key key() {
        return k;
    }

    public E value() {
        return e;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KVpair<?, ?> kVpair = (KVpair<?, ?>) o;
        return Objects.equals(k, kVpair.k) &&
                Objects.equals(e, kVpair.e);
    }

    @Override
    public int hashCode() {
        return Objects.hash(k, e);
    }

    @Override
    public String toString() {
        return "KVpair{" +
                "k=" + k +
                ", e=" + e +
                '}';
    }

    @Override
    public int compareTo(KVpair o) {
        return o.e.compareTo(this.e);
    }
}
package co.tdude.COEN352Assignment3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DLLDictionary<Key, E extends Comparable<E>> implements ADTDictionary<Key, E> {
    private final DoubleLinkedList<KVpair<Key, E>> list;  // To store dictionary

    /**
     * Constructors
     */

    public DLLDictionary() {
        list = new DoubleLinkedList<>();
    }

    public DLLDictionary(DLLDictionary d) {
        this.list = d.list;
    }

    /**
     * Reinitialize
     */
    public void clear() {
        list.clear();
    }

    /**
     * Insert an element: append to list
     */
    public void insert(Key k, E e) {
        KVpair<Key, E> temp = new KVpair<Key, E>(k, e);
        list.append(temp);
    }

    /**
     * Use sequential search to find the element to remove
     */
    public E remove(Key k) {
        E temp = find(k);
        if (temp != null)
            list.remove();
        return temp;
    }

    /**
     * Remove the last element
     */
    public E removeAny() {

        if (size() != 0) {
            list.moveToEnd();
            KVpair<Key, E> e = list.remove();
            return e.value();
        } else
            return null;
    }

    /**
     * Find k using sequential search
     *
     * @return Record with key value k
     */
    public E find(Key k) {
        this.list.moveToStart();
        for (int i = 0; i < this.list.length(); i++) {
            KVpair<Key, E> temp = this.list.getValue();
            if (k.equals(temp.key())) {
                return temp.value();
            }
            this.list.next();
        }
        return null; // "k" does not appear in dictionary
    }

    /**
     * @return List size
     */
    public int size() {
        return list.length();
    }


    public int[] createDesendingIndex() {
        List<KVpair<Key, E>> kvplist = new ArrayList<>();
        int i = 0;
        for (this.list.moveToStart(); this.list.currPos() != null; this.list.next()) {
            kvplist.add(new KVpair<Key, E>((Key) Integer.valueOf(i), this.list.currPos().getData().value()));
            i++;
        }
        // nlogn sort
        quicksort(kvplist, 0, kvplist.size() - 1);
        int[] sortedIndex = new int[i];
        i = 0;
        for (KVpair<Key,E> kv : kvplist) {
            sortedIndex[i] = (int)kv.key();
            i++;
        }
        return sortedIndex;
    }

    public void quicksort(List<KVpair<Key, E>> list, int left, int right) {
        int q;
        if (right > left) {
            q = partition(list, left, right);
            quicksort(list, left, q - 1);
            quicksort(list, q + 1, right);
        }
    }

    int partition(List<KVpair<Key, E>> list, int left, int right) {
        E P = list.get(left).value();
        int i = left;
        int j = right + 1;
        for (;;) {
            while (list.get(++i).value().compareTo(P) < 0)
                if (i >= right)
                    break;
            while (list.get(--j).value().compareTo(P) > 0)
                if (j <= left)
                    break;
            if (i >= j)
                break;
            else
                Collections.swap(list, i, j);
        }
        if (j == left)
            return j;
        Collections.swap(list, left, j);
        return j;
    }

    public int hasingSKU(String s, int M) {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
            char c[] = s.substring(j*4, (j*4)+4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }

        char c[] = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }

        return (int)(Math.abs(sum) % M);
    }

    public HashTable createSKUHashingIndex() {
        HashTable<String, Inventory> hashingIndex = new HashTable<>(list.length());
        for (this.list.moveToStart(); this.list.currPos() != null; this.list.next()) {
            // I think this implementation is really stupid given that
            // this is a generic dict and we are implementing this specifically
            // for a non-generic object??? (Inventory) but best just
            // follow the homework instructions.
            if (this.list.getValue().value() instanceof Inventory){
                String sku = ((Inventory)this.list.getValue().value()).getSku();
                int pos = hasingSKU(sku, this.list.length());
                hashingIndex.hashInsert(sku, (Inventory)this.list.getValue().value());
            }
        }
        return hashingIndex;
    }

    public String findBySKU(String key) {
        Inventory a = (Inventory)createSKUHashingIndex().hashSearch(key);
        if (a != null) {
            return a.toString();
        } else {
            return null;
        }
    }
}
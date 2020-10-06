package co.tdude.COEN352Midterm2;

import java.util.Arrays;

/** Max-heap implementation */
public class MaxHeap<E extends Comparable<? super E>> {
    private E[] Heap;   // Pointer to the heap array
    private int size;   // Maximum size of the heap
    private int n;      // Number of things in heap

    /**
     * Constructor supporting preloading of heap contents
     */
    public MaxHeap(E[] h, int num, int max) {
        Heap = h;
        n = num;
        size = max;
        buildheap();
        System.out.println(Arrays.toString(Heap));
    }


    /**
     * Heapify contents of Heap
     */
    public void buildheap() {
        for (int i = n / 2 - 1; i >= 0; i--) siftdown(i);
    }

    /**
     * Put element in its correct place
     */
    private void siftdown(int pos) {
        System.out.println("SiftDown called!");
        assert (pos >= 0) && (pos < n) : "Illegal heap position";
        while (!isLeaf(pos)) {
            int j = leftchild(pos);
            if ((j < (n - 1)) && (Heap[j].compareTo(Heap[j + 1]) < 0))
                j++; // j is now index of child with greater value
            if (Heap[pos].compareTo(Heap[j]) >= 0) return;
            DSUtil.swap(Heap, pos, j);
            pos = j;  // Move down
        }
    }


    /**
     * Remove and return maximum value
     */
    public E removemax() {
        assert n > 0 : "Removing from empty heap";
        DSUtil.swap(Heap, 0, --n); // Swap maximum with last value
        if (n != 0)      // Not on last element
            siftdown(0);   // Put new heap root val in correct place
        return Heap[n];
    }

    /** @return Position for left child of pos */
    public int leftchild(int pos) {
        assert pos < n/2 : "Position has no left child";
        return 2*pos + 1;
    }

    /** @return True if pos a leaf position, false otherwise */
    public boolean isLeaf(int pos)
    { return (pos >= n/2) && (pos < n); }
}
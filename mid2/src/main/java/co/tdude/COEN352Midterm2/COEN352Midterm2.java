package co.tdude.COEN352Midterm2;

import java.util.Arrays;

public class COEN352Midterm2 {

    public static void main(String[] args) {
        //Q1
        Integer[] a1 = {42,20,17,13,28,14,23,15};
        shellsort(a1);

        //Q2
        Integer[] A = {42, 20,17,13, 28, 14, 23, 15};
        MaxHeap<Integer> H = new MaxHeap<>(A, A.length, A.length);

        //Q5
        Integer[] A2 = {42, 20,17,13, 28, 14, 23, 15};
        MaxHeap<Integer> H2 = new MaxHeap<>(A2, A2.length, A2.length);

        //Q6
        selectsort(A);

        //Q9 //Q17
        QuickSort.sort(A);
    }

    static <E extends Comparable<? super E>> void shellsort(E[] A) {
        for (int i = A.length/2; i>2; i/=2) {
            for (int j = 0; j<i; j++) {
                inssort2(A, j, i);
            }
        }
        System.out.println(Arrays.toString(A));
        //inssort2(A, 0, 1);
    }

    static <E extends Comparable<? super E>> void inssort2(E[] A, int start, int incr) {
        for (int i = start+incr; i<A.length; i+=incr) {
            for (int j=i; (j>=incr) && (A[j].compareTo(A[j-incr])<0); j-=incr) {
                DSUtil.swap(A, j, j-incr);
            }
        }
    }

    static <E extends Comparable<? super E>> void selectsort(E[] A) {
        for (int i=0; i<A.length-1; i++) {
            int lowindex = i;
            for (int j=A.length-1; j>i; j--) {
                if (A[j].compareTo(A[lowindex]) < 0) {
                    lowindex = j;
                }
            }
            DSUtil.swap(A, i, lowindex);
            System.out.println("Q8 Swap Performed");
            if (i == 2) {
                System.out.println("Q6 Answer: " + lowindex);
                System.out.println("Q14 Answer: " + Arrays.toString(A));
            }
        }
    }
}

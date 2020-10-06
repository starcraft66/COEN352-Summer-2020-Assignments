package co.tdude.COEN352Midterm2;

// Source code example for "A Practical Introduction
// to Data Structures and Algorithm Analysis"
// by Clifford A. Shaffer, Prentice Hall, 1998.
// Copyright 1998 by Clifford A. Shaffer

import java.util.*;

// A bunch of utility functions.
public class DSUtil {

    // Swap two objects in an array
    public static void swap(Object[] array, int p1, int p2) {
        Object temp = array[p1];
        array[p1] = array[p2];
        array[p2] = temp;
    }

    // Randomly permute the Objects in an array
    static void permute(Object[] A) {
        for (int i = A.length; i > 0; i--) // for each i
            swap(A, i-1, DSUtil.random(i));  //   swap A[i-1] with
    }                                    //   a random element

    // Create a random number function to return values
    // uniformly distributed within the range 0 to n-1.
    static private Random value = new Random(); // Hold the Random class object

    static int random(int n) { // My own function
        return Math.abs(value.nextInt()) % n;
    }

}
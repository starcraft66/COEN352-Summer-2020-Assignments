package co.tdude.COEN352Assignment1;

public class AList<E> implements ADTList<E> {
    private static final int defaultSize = 10; // Default size
    private final int maxSize;        // Maximum size of list
    private final E[] listArray;      // Array holding list elements/**Constructors*//**Create a list with the default capacity.*/
    private int listSize;       // Current # of list items
    private int curr;           // Position of current element

    AList() {
        this(defaultSize);
    }

    /**
     * Create a new list object.@param size Max # of elements list can contain.
     */
    @SuppressWarnings("unchecked")
    // Generic array allocation
    AList(int size) {
        maxSize = size;
        listSize = curr = 0;
        listArray = (E[]) new Object[size];  // Create listArray
    }

    public void clear()         // Reinitialize the list
    {
        listSize = curr = 0;
    }  // Simply reinitialize values/**Insert "it" at current position*/

    public void insert(E it) {
        assert listSize < maxSize : "List capacity exceeded";
        // Shift elements up
        //   to make room
        if (listSize - curr >= 0) System.arraycopy(listArray, curr, listArray, curr + 1, listSize - curr);
        listArray[curr] = it;
        listSize++;               // Increment list size
    }

    /**
     * Append "it" to list
     */
    public void append(E it) {
        assert listSize < maxSize : "List capacity exceeded";
        listArray[listSize++] = it;
    }

    /**
     * Remove and return the current element
     */
    public E remove() {
        if ((curr < 0) || (curr >= listSize))  // No current element
            return null;
        E it = listArray[curr];   // Copy the element
        // Shift them down
        if (listSize - 1 - curr >= 0) System.arraycopy(listArray, curr + 1, listArray, curr, listSize - 1 - curr);
        listSize--;               // Decrement size
        return it;
    }

    public void moveToStart() {
        curr = 0;
    } // Set to front

    public void moveToEnd() {
        curr = listSize;
    } // Set at end

    public void prev() {
        if (curr != 0) curr--;
    }   // Back up

    public void next() {
        if (curr < listSize) curr++;
    }

    /**
     * @return List size
     */
    public int length() {
        return listSize;
    }

    /**
     * @return Current position
     */
    public int currPos() {
        return curr;
    }

    /**
     * Set current list position to "pos"
     */
    public void moveToPos(int pos) {
        assert (pos >= 0) && (pos <= listSize) : "Pos out of range";
        curr = pos;
    }

    /**
     * @return Current element
     */
    public E getValue() {
        assert (curr >= 0) && (curr < listSize) : "No current element";
        return listArray[curr];
    }
}
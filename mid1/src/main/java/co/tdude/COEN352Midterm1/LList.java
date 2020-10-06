package co.tdude.COEN352Midterm1;

/** Linked list implementation */
public class LList<E extends Comparable<E>> implements ADTList<E> {
	
	private Link<E> head; // Pointer to list header
	
	private Link<E> tail; // Pointer to last element
	
	protected Link<E> curr; // Access to current element
	
	private int cnt; // length of list
	
	/** Constructors */
	public LList(int size) { this(); } // Constructor -- Ignore size
	
	public LList() {
		curr = tail = head = new Link<E>(null); // Create header
		cnt = 0;
	}
	
	/** Remove all elements */
	public void clear() {
		head.setNext(null); // Drop access to links
		curr = tail = head = new Link<E>(null); // Create header
		cnt = 0;
	}
	
	/** Insert "it" at current position */
	public void insert(E it) {
		
		
		curr.setNext(new Link<E>(it, curr.next()));
		
		//Link<E> tempLink = new Link<E>(it, curr.next()); 
		//curr.setNext(tempLink);
		
		 
		
		// Using freelist support
		//curr.setNext(Link.get(it, curr.next()));
		
		if (tail == curr) 
			tail = curr.next(); // New tail
		cnt++;
	}
	
	/** Append "it" to list */
	public void append(E it) {
		tail = tail.setNext(new Link<E>(it, null));
		cnt++;
	}
	
	/** Remove and return current element */
	public E remove() {
		if (curr.next() == null) 
			return null; // Nothing to remove
		
		E it = curr.next().element(); // Remember value
		
		if (tail == curr.next()) 
			tail = curr; // Removed last
		
	    //Using freelist
		// Link<E> tempptr = curr.next();
		Link<E> templink = curr.next().next(); 
		curr.setNext(templink); // Remove from list
		// tempptr.release(); 
		
		cnt--; // Decrement count
		return it; // Return value
	}
	
	/** Set curr at list start */
	public void moveToStart()
	{ curr = head; }
	
	/** Set curr at list end */
	public void moveToEnd()
	{ curr = tail; }
	
	/** Move curr one step left; no change if now at front */
	public void prev() {
		if (curr == head) return; // No previous element
		Link<E> temp = head;
		// March down list until we find the previous element
		while (temp.next() != curr) 
			temp = temp.next();
		curr = temp;
	}
	
	/** Move curr one step right; no change if now at end */
	public void next(){ 
		if (curr != tail) curr = curr.next(); 
	}
	
	/** @return List length */
	public int length() { return cnt; }
	
	/** @return The position of the current element */
	public int currPos() {
		Link<E> temp = head;
		int i;
		for (i=0; curr != temp; i++)
		temp = temp.next();
		return i;
	}
	
	/** Move down list to "pos" position */
	public void moveToPos(int pos) {
		assert (pos>=0) && (pos<=cnt) : "Position out of range";
		curr = head;
		for(int i=0; i<pos; i++) curr = curr.next();
	}
	
	/** @return Current element value */
	public E getValue() {
		if(curr.next() == null) return null;
		return curr.next().element();
	}
	
	// Extra stuff not printed in the book.

	  /**
	   * Generate a human-readable representation of this list's contents
	   * that looks something like this: < 1 2 3 | 4 5 6 >.  The vertical
	   * bar represents the current location of the fence.  This method
	   * uses toString() on the individual elements.
	   * @return The string representation of this list
	   */
	  public String toString()
	  {
	    // Save the current position of the list
	    int oldPos = currPos();
	    int length = length();
	    StringBuffer out = new StringBuffer((length() + 1) * 4);

	    moveToStart();
	    out.append("< ");
	    for (int i = 0; i < oldPos; i++) {
	      out.append(getValue());
	      out.append(" ");
	      next();
	    }
	    out.append("| ");
	    for (int i = oldPos; i < length; i++) {
	      out.append(getValue());
	      out.append(" ");
	      next();
	    }
	    out.append(">");
	    moveToPos(oldPos); // Reset the fence to its original position
	    return out.toString();
	  }

	  public E max()
	  {
	  	moveToStart();
	  	E max = null;
	  	while(curr.next() != null)
		{
			if (max == null || max.compareTo(curr.element()) < 0)
			{
				max = curr.element();
			}
		}
	  	return max;
	  }

}

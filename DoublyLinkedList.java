

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author JAMES ROWLAND 18324013 
 *  @version 09/10/18 11:13:22
 */

 
/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an 
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{

    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode
    {
        public final T data;
        public DLLNode next;
        public DLLNode prev;
        public int dataSize;
        
        
        /** 
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
        {
          data = theData;
          prev = prevNode;
          next = nextNode;
          dataSize = 0;
 
        }
    }

    private DLLNode head, tail;
    private int dataSize;
    
    /**
     * Constructor of an empty DLL
     * @return DoublyLinkedList
     */
    public DoublyLinkedList() 
    {
      head = null;
      tail = null;
      dataSize = 0;
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     * Worst-case asymptotic running time cost:Theta(1)
     *
     * Justification:
     * As the function only accesses one integer, the worst case runtime can be Theta(1).
     
     */
    public boolean isEmpty()
    {
     return head == null;
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     * Worst-case asymptotic running time cost:Theta(1)
     *
     * Justification:
     * As the function only accesses one integer, the worst case runtime can be Theta(1).
     
     */
    
    
    public int length(){
    	
    	int length;
    	if(isEmpty()) {
    		length = 0;
    	}
    	else {
    		length = 1;
    		DLLNode currentNode = this.head;
        	while(currentNode.next != null){
        		currentNode = currentNode.next;
        		length++;
        	} 	
    	}
    	return length;
    }
    /**
     * Inserts an element in the doubly linked list
     * @param pos : The integer location at which the new data should be
     *      inserted in the list. We assume that the first position in the list
     *      is 0 (zero). If pos is less than 0 then add to the head of the list.
     *      If pos is greater or equal to the size of the list then add the
     *      element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     * Worst-case asymptotic runtime cost: Theta(N)
     *
     * Justification:
     *  The worst-case would be that the data would be inserted at end - 1.
     *  This would make the function enter a loop resulting in a runtime cost of
     *  Theta(N).
     */
    public void insertBefore( int pos, T data ) 
    {
    	
		DLLNode dataElement = new DLLNode(data, null, null);
		if (isEmpty()) {
			head = dataElement;
			tail = dataElement;
			dataSize++;
		} else {
			if (pos <= 0) {
				dataElement.next = head;
				head.prev = dataElement;
				head = dataElement;
				dataSize++;

			}

			else if (pos >= dataSize) {
				dataElement.prev = tail;
				tail.next = dataElement;
				tail = dataElement;
				dataSize++;
			}
			else {
				int index = 0;
				DLLNode insertionElement = head;
				while (index < pos) {
					insertionElement = insertionElement.next;
					index++;
				}
				DLLNode temporaryElement = dataElement;
				insertionElement.prev.next = temporaryElement;
				temporaryElement.prev = insertionElement.prev;
				insertionElement.prev = temporaryElement;
				temporaryElement.next = insertionElement;
			}

		}

	}
 

    /**
     * Get func
     *
     * Worst-case asymptotic runtime cost: Theta(N)
     *
     * Justification:
     *  The worst-case would be that the data would be inserted at end - 1.
     *  This would make the function enter a loop resulting in a runtime cost of
     *  Theta(N).
     *
     *
     */ 
	public T get(int pos) {
		
		T testData = null;
		if (pos > (dataSize - 1)) {
			return testData;
		}
		else if (pos < 0) {
			return testData;
		}
		else {
			int index = 0;
			DLLNode temp = head;
			while (index <= pos && temp.next != null) {
				temp = temp.next;
				index++;
			}
			testData = temp.data;
			return testData;
		}
	}
   
	 /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified. 
     *
     * Worst-case asymptotic runtime cost: Theta(N)
     *
     * Justification:
     *  worst case would be (dataSize - 2). This would make the function 
     *  enter a loop resulting in a cost of Theta(N).
     */
	public boolean deleteAt(int pos) {

		if (isEmpty()) {
			return false;
		}
		else if (pos > (dataSize - 1)) {
			return false;
		}
		else if (pos < 0) {
			return false;
		}
		else if (pos == 0) {
			if (dataSize == 1) {
				head = null;
				tail = null;
				dataSize--;
			} else
			{
				head = head.next;
				head.prev = null;
				dataSize--;
			}
			return true;
		} else if (pos == (dataSize - 1)) {
			tail = tail.prev;
			tail.next = null;
			dataSize--;
			return true;
		} else {
			int index = 0;
			DLLNode temp = head;
			while (index < pos) {
				temp = temp.next;
				index++;
			}
			temp.prev.next = temp.next;
			temp.next.prev = temp.prev;
			temp.next = null;
			temp.prev = null;
			dataSize--;
			return true;
		}
	}


	/**
     * Reverses the list.
     * 
     * Worst-case asymptotic runtime cost: Theta(N)
     *
     * Justification: The worst-case would be that the list size would be greater than 1as 
     * this would result in the function entering a loop resulting in a runtime cost of Theta(N).
     */
    public void reverse()
	{
		if (isEmpty()) {
		}
		else if (dataSize == 1) {
		}
		else {
			DLLNode temp = head;
			head = tail;
			tail = temp;
			DLLNode insertionElement = head;
			while (insertionElement != null) {
				temp = insertionElement.next;
				insertionElement.next = insertionElement.prev;
				insertionElement.prev = temp;
				insertionElement = insertionElement.next;
			}
		}
	}
    /*
     *    
     * 
     * Worst-case asymptotic running time cost: O(n^2)
     *
     * Justification: Time complexity of nested loops is equal to the number of times the
		innermost statement is executed.
     *  
     */
     public void makeUnique()
    {
      //TODO
    	 if(this.length() > 0) {
    	 int length = this.length();
    	 DLLNode current= this.head;
		 System.out.println("head is : " + current.data);
    	 for (int i = 0; i < length; i++) {
    		 T currentData = current.data;
    		 DLLNode next = this.head;
    		 for(int j = 0; j < length;j++) {
    			 T nextData = next.data;
    			 	if (currentData == nextData && i != j) {
    			 		System.out.println(currentData);
    			 		System.out.println(nextData);
    			 		length--;
    			 		deleteAt(j);
    			 		}
    			 	if(next.next != null) {
        			 	next = next.next;
    			 	}  			 	
    			 }
    	    		current = current.next;
    		 } 
    	 }
    }

    /**
     * This method returns and removes the element that was most recently added by the push method.
     * @return the last item inserted with a push; or null when the list is empty.
     *
     *
     * Worst-case asymptotic running time cost:Theta(1)
     *
     * Justification:
     *  As the position will always be 0, the deleteAt function will never enter a loop.
     *  This means that the runtime cost will not be the same as the worst case runtime of the
     *  deleteAt function. As there is no loop, and the deleteAt will only access integers
     *  the worst case runtime is Theta(1).
     */


	public T pop() {
		if (isEmpty()) {
			return null;
		}
		else {
			T dataElement = head.data;
			deleteAt(0); 
			return dataElement;
		}
	}
      
	/**
     * push
     * @param item : the item to push on the stack
     *
     * Worst-case asymptotic runtime cost: Theta(1)
     *
     * Justification:
     *  
     *  there is no loop, and the insertBefore will only access integers
     *  so the worst case runtime is Theta(1).
     */
     
    public void push(T item) 
    {
     insertBefore(0, item);
    }
 
    /**
     * 
     * ENQUEUE
     * 
     * OLD CODE
     * 
     * 
     	if (this.length()== 0) {
    		DLLNode newNode = new DLLNode(item,this.tail,this.head);
    		this.tail = newNode;
    		this.head = newNode;
    	}
    	else {
    		DLLNode temp = this.head;
    		while(temp.next != null) {
    			temp = temp.next;
    		}
    		temp.next = new DLLNode(item, temp, null);
    	}
     * 
     * 
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to be enqueued to the stack
     *
     * Worst-case asymptotic running time cost:  Theta(1)
     *
     * Justification:
     *  As the position will always be 0 or dataSIze, the insertBefore function will never enter a loop.
     *  This means that the runtime cost will not be the same as the worst case runtime of the
     *  insertBefore function. As there is no loop, and the insertBefore will only access integers
     *  the worst case runtime is Theta(1).
     */
     
    public void enqueue(T item) 
	{

	if (isEmpty()) {
		insertBefore(0, item);
	}
	else {
		insertBefore(dataSize, item);
	}
}

     /**
     *DEQUEUE
     *
     *OLD CODE
     *
     * {	
    	if(this.length() > 0) {
    		T t = this.tail.data;
    		DLLNode temp = this.tail;
    		this.tail = this.tail.prev;
    		
    		return t;
    	}
   
    	T t = null; 
		return t;
     *
     * Worst-case asymptotic running time cost: Theta(1)
     *
     * Justification:
     *  As the position will always be 0, the deleteAt function will never enter a loop.
     *  This means that the runtime cost will not be the same as the worst case runtime of the
     *  deleteAt function. As there is no loop, and the deleteAt will only access integers
     *  the worst case runtime is Theta(1).
     */

	public T dequeue() {
		if (isEmpty()) {
			return null;
		}
		else {
			T dataElement = head.data;
			deleteAt(0);
			return dataElement;
		}
	}


    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     *
     * Worst-case asymptotic running time cost:   Theta(n)
     *
     * Justification:
     *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
     *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the doubly-linked list has 'n' elements.
     *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     */
    public String toString() 
    {
      StringBuilder s = new StringBuilder();
      boolean isFirst = true; 

      // iterate over the list, starting from the head
      for (DLLNode iter = head; iter != null; iter = iter.next)
      {
        if (!isFirst)
        {
          s.append(",");
        } else {
          isFirst = false;
        }
        s.append(iter.data.toString());
      }

      return s.toString();
    }


}



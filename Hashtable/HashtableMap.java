// --== CS400 Project One File Header ==--
// Name: Anais Corona Perez
// Email: coronaperez@wisc.edu
// Team: Red
// Group: DC
// TA: Daniel
// Lecturer: Florian Heimerl
// Notes to Grader: implemented MapADT as best as possible from writeup information


import java.util.NoSuchElementException;
import java.util.LinkedList;

/**
 * This class creates HashtableMap objects that contain
 * a LinkedList array
 *
 */
public class HashtableMap implements MapADT<Object, Object>{
	private LinkedList<Object>[] table;
	private int size;
	private int currItems;
	
	
	/**
	 * HashtableMap constructor. Creates a new HashtableMap object
	 * of the provided capacity
	 * 
	 * @param capacity of the hash table
	 */
	@SuppressWarnings("unchecked")
	public HashtableMap(int capacity) {
		this.size = capacity;
		this.table = new LinkedList[capacity];
		this.currItems = 0;
		this.table = initializeArray(capacity, this.table);
		
	}
	
	/**
	 * Default constructor. Creates a new HashtableMap object
	 * of capacity 20
	 */
	@SuppressWarnings("unchecked")
	public HashtableMap() {
		this.size = 20;
		this.table = new LinkedList[20];
		this.currItems = 0;
		this.table = initializeArray(20, this.table);
	}
	
	/**
	 * Helper method for to initialize each
	 * index of the provided LinkedList array
	 * 
	 * @param capacity the full capacity of the array to be initialized
	 * @param arr the LinkedList array to be initialized
	 * @return the fully initialized LinkedList array
	 */
	private LinkedList<Object>[] initializeArray(int capacity, LinkedList<Object>[] arr) {
		for(int i = 0; i < capacity; i++) {
			arr[i] = new LinkedList<Object>();
		}
		
		return arr;
	}

	
	/**
	 * Inserts a Pair object containing the key and value 
	 * into the hashtable
	 * 
	 * @param key Key of the pair 
	 * @param value Value of the pair
	 * @return false if key is null or equal to a key already in hash table, else it returns true
	 *  
	 */
	public boolean put(Object key, Object value) {
		if(key == null)
			return false;
		if(containsKey(key))
			return false;
		
		//get index 
		int index = Math.abs(key.hashCode()) % this.size;
		
		//insert into the table and increment the counter for currItems
		Pair newVal = new Pair(key, value);
		table[index].add(newVal);
		currItems++;
		
		if(currItems >= 0.80*size)
			reHash();
		
		return true;
	}
	
	/**
	 * Obtains the value assigned to the key
	 * 
	 * @param key Key of the pair
	 * @return value associated with key
	 * @throws NoSuchElementException if value isn't found
	 */
	public Object get(Object key) throws NoSuchElementException {
		
		boolean found = false;
		
		if(containsKey(key) == false)
			throw new NoSuchElementException("Value not found");
		
		//1. Find the index in the array that		
		//contains the LinkedList the value is in
		
		int index =  Math.abs(key.hashCode()) % this.size;
		
		//2.Go through the linked list in search of the 
		//key
		LinkedList<Object> currList = table[index];
		for(int i = 0; i<currList.size(); i++) {
			//3. if found, return the Pair.getValue();
			if(((Pair) currList.get(i)).getKey() == key)
				return ((Pair) currList.get(i)).getValue();
		}
		
		//4. if not found, throw exception;
		if(found == false)
			throw new NoSuchElementException("Value not found");
		
		return null;
	}
	
	/**
	 * Returns the number of key-value pairs stored in hash table
	 * 
	 * @return number of key-value pairs
	 */
	public int size() {
		return this.currItems;
	}
	
	/**
	 * Checks if provided key is within the hash table
	 * 
	 * @param key to be searched for
	 * @return true if found, false otherwise
	 */
	public boolean containsKey(Object key) {
		LinkedList<Object> currList = table[Math.abs(key.hashCode()) % this.size];
		
		for(int i = 0; i < currList.size(); i++) {
			if (((Pair) currList.get(i)).getKey() == key)
				return true;
		}
		return false;
	}
	
	/**
	 * Removes key and its associated value from hash table
	 * 
	 * @param key to be removed
	 * @return value associated with provided key if found, else null
	 */
	public Object remove(Object key) {
		if(containsKey(key) == false)
			return null;
		
		Object retValue = null;
				
		LinkedList<Object> temp = table[Math.abs(key.hashCode()) % this.size];
		
		//Go through LinkedList at the specified index and search for the
		//node containing the key we're looking for
		for(int i = 0; i<temp.size(); i++) {
			//Once the node is found, remove the node and update the currItems counter
			if(((Pair) temp.get(i)).getKey() == key) {
				retValue = ((Pair) temp.get(i)).getValue();
				currItems--;
				table[Math.abs(key.hashCode()) % this.size].remove(i);
			}
		}
		
		return retValue;
	}
	
	/**
	 * Removes all key-value pairs from hash table
	 */
	public void clear() {
		for(int i = 0; i < this.table.length; i++) {
			table[i].clear();
		}
		this.currItems = 0;
	}
	
	/**
	 * Rehashes table
	 * 
	 * Helper Method
	 */
	@SuppressWarnings("unchecked")
	private void reHash(){		
		LinkedList <Object>[] newTable = new LinkedList[2*table.length];
		newTable = initializeArray(2*table.length, newTable);
		
		for(int i = 0; i<table.length; i++) {
			newTable[i] = table[i];
		}
		this.table = newTable;
	}

}

// --== CS400 Project One File Header ==--
// Name: Anais Corona Perez
// Email: coronaperez@wisc.edu
// Team: Red
// Group: DC
// TA: Daniel
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import java.util.NoSuchElementException;

/**
 * Checks the functionality of HashtableMap class
 *
 */
public class HashtableMapTests {
	
	/**
	 * Tests functionality of HashtableMap constructors
	 * 
	 * @return true if tests are passed
	 */
	public static boolean test1() { 
		
		HashtableMap test = new HashtableMap(10);
		if(test.size() != 0) {
			return false;
		}
		
		HashtableMap test2 = new HashtableMap();
		if(test2.size() !=0) {
			return false;
		}
		return true;
	}
	
	/**
	 * Tests functionality of the put method in HashtableMap class
	 * 
	 * @return true if tests are passed
	 */
	public static boolean test2() { 
		HashtableMap test = new HashtableMap(10);
		if(test.put(null, 10))
			return false;
				
		if(!test.put(10, 20))
			return false;
	
		if(test.put(10, 'c'))
			return false;
		
		if(!test.put(25, 'c'))
			return false;
		
		return true;
	}
	
	/**
	 * Tests functionality of the get method in HashtableMap class
	 * 
	 * @return true if tests are passed
	 */
	public static boolean test3() { 
		HashtableMap test = new HashtableMap(10);
		test.put(10, 20);
		try{
			test.get(10);
		}
		catch (NoSuchElementException e){
			return false;
		}
		
		test.put(25, 'c');
		try {
			test.get(25);
		}
		catch (NoSuchElementException e) {
			return false;
		}
		
		try {
			test.get(30);
		}
		catch(NoSuchElementException e) {
		//do nothing, it should throw it since its not in the table
		}
		
		return true;
		}
	
	/**
	 * Tests functionality of containsKey(), remove(), and clear()
	 * 
	 * @return true if tests are passed
	 */
	public static boolean test4() { 
		HashtableMap test = new HashtableMap();
		test.put(10, 25);

		if(!test.containsKey(10))
			return false;
		
		if(test.remove(25) != null)
			return false;
		
		if((int)test.remove(10) != 25)
			return false;
		
		test.clear();
		if(test.size() != 0)
			return false;
		
		return true;
	}
	
	/**
	 * Tests functionality of HashtableMap methods in a combined way,
	 * using methods in a typical use style
	 * 
	 * @return true if tests are passed
	 */
	public static boolean test5() { 
		HashtableMap test = new HashtableMap(3);
		test.put(10, 25);
		test.put(25, 10);
		test.put(57, 56);
		
		if(test.size() != 3)
			return false;
		
		test.remove(57);
		
		if(test.size() != 2) 
			return false;
		
		if(test.containsKey(57)) 
			return false;
		
		test.clear();
		
		if(test.size() != 0) 
			return false;
		
		if(test.containsKey(10) || test.containsKey(25) || test.containsKey(57)) 
			return false;
		
		return true;
	}
}

// --== CS400 Project One File Header ==--
// Name: Anais Corona Perez
// Email: coronaperez@wisc.edu
// Team: Red
// Group: DC
// TA: Daniel
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>




/**
 * Helper class for HashtableMap. Creates Pair objects that contain
 * a key and value
 *
 */
public class Pair {
	private Object key;
	private Object value;
	
	/**
	 * Pair constructor. Creates a new Pair object containing the 
	 * provided key and value	
	 * @param key to be stored in Pair
	 * @param value to be stored in Pair
	 */
	public Pair(Object key, Object value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Gets key of Pair
	 * @return key associated with this Pair
	 */
	public Object getKey() {
		return this.key;
	}
	
	/**
	 * Gets value of Pair
	 * @return value associated with this Pair
	 */
	public Object getValue() {
		return this.value;
	}
}

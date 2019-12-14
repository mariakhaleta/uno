package system;

/**
 * This class is an ID Generator.
 *
 */
class IDGenerator {
	private static IDGenerator generator = null;
	private int userID;
	private int limit;

	private IDGenerator(){
		this.userID = 1;
		this.limit = 100;
	}

	/**
	 * Create an unique instance of this class with the limit defined.
	 * @return a reference to the unique IDGenerator object.
	 */
	static IDGenerator getInstance(){
		if(null == generator)
			generator = new IDGenerator();
		return generator;
	}

	/**
	 * Get an ID.
	 * @return an ID.
	 */
  ID<Integer> getAnID(){
		if(this.userID > this.limit)
			throw new IndexOutOfBoundsException("Too many ID's.");
		ID <Integer> id = new ID <Integer> (this.userID);
		this.userID++;
		return id;
	}
}

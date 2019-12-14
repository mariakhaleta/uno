package system;

/**
 * This class represents an identification (ID). Since the ID is set by the
 * constructor, it can't be modified.
 * @param <Type> is the ID representation.
 */
class ID <Type>{
	private Type id;

	ID(Type ID){
		this.id = ID;
	}

	Type getID(){
		return this.id;
	}
}

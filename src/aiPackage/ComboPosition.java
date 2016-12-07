package aiPackage;

public enum ComboPosition {
	Horizontal("Horizontal"),
	Vertical("Vertical"),
	Diagonal("Diagonal"),
	none("none");
	String id;
	ComboPosition(String id){
		this.id=id;
	}
}

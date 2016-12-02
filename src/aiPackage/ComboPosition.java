package aiPackage;

public enum ComboPosition {
	Horizontal(1),
	Vertical(2),
	Diagonal(3),
	none(0);
	int id;
	ComboPosition(int id){
		this.id=id;
	}
}

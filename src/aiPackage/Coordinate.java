package aiPackage;

public class Coordinate {
	int x,y;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Coordinate(int x,int y){
		this.x=x;
		this.y=y;
	}
	public boolean equals(Coordinate im){
		if(im.getX()==x && im.getY()==y){
			return true;
		}
		return false;
	}
	@Override
	public String toString(){
		return x+","+y;
	}
}

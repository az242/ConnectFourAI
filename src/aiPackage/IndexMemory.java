package aiPackage;

public class IndexMemory {
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
	public IndexMemory(int x,int y){
		this.x=x;
		this.y=y;
	}
	public boolean equals(IndexMemory im){
		if(im.getX()==x && im.getY()==y){
			return true;
		}
		return false;
	}
}

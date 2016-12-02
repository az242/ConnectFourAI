package aiPackage;

import java.util.ArrayList;

public class Combo {
	private ArrayList<IndexMemory> thisCombo;
	private ComboPosition cp;
	public Combo(){
		thisCombo=new ArrayList<IndexMemory>();
		cp=ComboPosition.none;
	}
	public ArrayList<IndexMemory> getCombo(){
		return thisCombo;
	}
	public void setComboPosition(ComboPosition cp){
		this.cp=cp;
	}
	public ComboPosition getComboPosition(){
		return cp;
	}
	public int ComboSize(){
		return thisCombo.size();
	}
	public Combo addToCombo(IndexMemory im){
		thisCombo.add(im);
		return this;
	}
	public boolean contains(IndexMemory im){
		for(int x=0;x<thisCombo.size();x++){
			if(thisCombo.get(x).equals(im)){
				return true;
			}
		}
		return false;
	}
	public boolean equals(Combo c){
		for(int x=0;x<c.getCombo().size();x++){
			if(!contains(c.getCombo().get(x))){
				return false;
			}
		}
		return true;
	}
}

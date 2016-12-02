import java.util.ArrayList;

import aiPackage.Combo;
import aiPackage.ComboPosition;
import aiPackage.IndexMemory;

public class AI {
	players player;
	
	public AI(players player){
		this.player=player;
	}
	public void AIMove(board b){
		//if b already has AI's piece we can work off of
		if(b.contains(player)){
			
			
		}else{
			//randomly place first piece
			b.move(player, (int)Math.floor(Math.random()*b.getCols()));
		}
	}
	/* Finds all possible moves that the bot can take related to biggest combo
	 * possible moves based on COMBO SIZE and COMBO POSITION
	 * will return a array of the possible spots to complete the combo
	 */
	public IndexMemory[] getPossibleMovePosition(board b,int col, Combo c){
		ArrayList<IndexMemory> im = new ArrayList<IndexMemory>();
		if(c.getComboPosition()==ComboPosition.Horizontal){
			
		}else if(c.getComboPosition()==ComboPosition.Vertical){
			
		}else if(c.getComboPosition()==ComboPosition.Diagonal){
			
		}
		return (IndexMemory[]) im.toArray();
	}
	/*
	 * finds the biggest combo in a arrayList
	 */
	public Combo findMaxCombo(ArrayList<Combo> combos){
		Combo biggestCombo = combos.get(0);
		for(int x=0;x<combos.size();x++){
			if(biggestCombo.ComboSize()<combos.get(x).ComboSize()){
				biggestCombo=combos.get(x);
			}
		}
		return biggestCombo;
	}
	/*
	 * Find all the tokens that are in a row and stores them
	 */
	public ArrayList<Combo> findAllCombos(board b){
		ArrayList<Combo> combos= new ArrayList<Combo>();
		for(int x=0;x<b.getRows();x++){
			for(int y=0;y<b.getCols();y++){
				if(b.getIndex(x, y)==player){
					Combo[] temp = getCombos(x,y,b);
					//merge all combos (leaving out duplicates)
					for(int z=0;z<temp.length;z++){
						boolean shouldAdd = true;
						for(int a=0;a<combos.size();a++){
							if(combos.get(a).equals(temp[z])){
								shouldAdd=false;
							}
						}
						if(shouldAdd){
							combos.add(temp[z]);
						}
					}
					//end merge
				}
			}
		}
		return combos;
	}
	
	/*
	 * Find all combos starting from the cords x,y on board b
	 */
	private Combo[] getCombos(int x,int y,board b){
		ArrayList<Combo> temp=new ArrayList<Combo>();
		for(int a=0;a<5;a++){
			//check if list has that combo already
			boolean shouldAdd = true;
			Combo tempCombo = helperCheck(a,x,y,b,new Combo());
			//check if each entry equals the new found combo
			for(int c=0;c<temp.size();c++){
				if(temp.get(c).equals(tempCombo)){
					shouldAdd = false;
				}
			}
			//if we never find a match we add it
			if(shouldAdd){
				temp.add(tempCombo);
			}
		}
		return (Combo[]) temp.toArray();
	}
	/*
	 * helperCheck goes through each of the directions. 
	 */
	private Combo helperCheck(int mode, int x, int y,board b,Combo c){
		if(b.getIndex(x, y)!=player){
			return c;
		}else if(mode==0 && y+1<b.getCols()){
			return (helperCheck(0,x,y+1,b,c.addToCombo(new IndexMemory(x,y))));
		}else if(mode==1 && x-1>=0 && y+1<b.getCols()){
			return helperCheck(1,x-1,y+1,b,c.addToCombo(new IndexMemory(x,y)));
		}else if(mode==2 && x-1>=0){
			return helperCheck(2,x-1,y,b,c.addToCombo(new IndexMemory(x,y)));
		}else if(mode==3 && x-1>=0 && y-1>=0){
			return helperCheck(3,x-1,y-1,b,c.addToCombo(new IndexMemory(x,y)));
		}else if(mode==4 && y-1<=0){
			return helperCheck(4,x,y-1,b,c.addToCombo(new IndexMemory(x,y)));
		}else{
			return c;
		}
	}
}

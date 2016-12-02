import java.util.ArrayList;

import aiPackage.Combo;
import aiPackage.IndexMemory;

public class AI {
	players player;
	
	public AI(players player){
		this.player=player;
	}
	public void AIMove(board b){
		//if b already has AI's piece we can work off of
		if(b.contains(player)){
			//check if horizontals are open
			
		}else{
			//randomly place first piece
			b.move(player, (int)Math.floor(Math.random()*b.getCols()));
		}
	}
	/* Finds he positin of the token that we just entered as a move
	 */
	public IndexMemory getMovePosition(board b,int col){
		IndexMemory im=null;
		for(int x=b.getRows()-1;x>=0;x--){
			if(b.getIndex(x, col)==player){
				im= new IndexMemory(x,col);
			}
		}
		return im;
	}
	/*
	 * Find all the tokens that are in a row and stores them
	 */
	public void findAllCombos(board b){
		for(int x=0;x<b.getRows();x++){
			for(int y=0;y<b.getCols();y++){
				if(b.getIndex(x, y)==player){
					//start searching for combos from this index
					getCombos(x,y,b);
				}
			}
		}
	}
	private ArrayList<Combo> getCombos(int x,int y,board b){
		ArrayList<Combo> temp=new ArrayList<Combo>();
		for(int a=0;a<5;a++){
			temp.add(helperCheck(a,x,y,b,new Combo()));
		}
		return temp;
	}
	private Combo helperCheck(int mode, int x, int y,board b,Combo c){
		if(b.getIndex(x, y).getSymbol()==player.getSymbol()){
			c.addToCombo(new IndexMemory(x,y));
		}else{
			return c;
		}
		
		switch(mode){
		case 0:
			//right
			if(y+1<b.getCols())
				return helperCheck(0,x,y+1,b,c);
			break;
		case 1:
			//diag top right
			if(x-1>=0 && y+1<b.getCols())
				return helperCheck(1,x-1,y+1,b,c);
			break;
		case 2:
			//vertical up
			if(x-1>=0)
				return helperCheck(2,x-1,y,b,c);
			break;
		case 3:
			//diag top left
			if(x-1>=0 && y-1>=0)
				return helperCheck(3,x-1,y-1,b,c);
			break;
		case 4:
			//left
			if(y-1<=0)
				return helperCheck(4,x,y-1,b,c);
			break;
		}
	}
}

import java.util.ArrayList;

import aiPackage.Combo;
import aiPackage.ComboPosition;
import aiPackage.Coordinate;

public class AI {
	players player;
	
	public AI(players player){
		this.player=player;
	}
	public void AIMove(board b){
		//if b already has AI's piece we can work off of
		if(b.contains(player)){
			//finds all the possible moves
			ArrayList<Coordinate> PossibleMoves = getPossibleMovePosition(b,findMaxCombos(findAllCombos(b)));
			//random move out of the list
			int random = (int) Math.floor(Math.random()*PossibleMoves.size());
			if(PossibleMoves.size()==0){
				//move randomly when there are no optimal spots
				b.move(player, (int)Math.floor(Math.random()*b.getCols()));
			}else{
				//move based on biggest combo
				b.move(player, PossibleMoves.get(random).getY());
			}
		}else{
			//randomly place first piece
			b.move(player, (int)Math.floor(Math.random()*b.getCols()));
		}
	}
	/* Finds all possible moves that the bot can take related to biggest combo
	 * possible moves based on COMBO SIZE and COMBO POSITION
	 * will return a array of the possible spots to complete the combo
	 */
	public ArrayList<Coordinate> getPossibleMovePosition(board b, ArrayList<Combo> c){
		ArrayList<Coordinate> im = new ArrayList<Coordinate>();
		for(int x=0;x<c.size();x++){
			//scroll through all combos and add possible moves for each combo state.
			if(c.get(x).getComboPosition()==ComboPosition.Horizontal){
				Coordinate left = getMostLeft(c.get(x));
				left.setY(left.getY()-1);
				Coordinate right = getMostRight(c.get(x));
				right.setY(right.getY()+1);
				if(validMove(b,left)){
					im.add(left);
				}
				if(validMove(b,right)){
					im.add(right);
				}
			}else if(c.get(x).getComboPosition()==ComboPosition.Vertical){
				Coordinate top = getMostTop(c.get(x));
				top.setY(top.getY()-1);
				if(validMove(b,top)){
					im.add(top);
				}
			}else if(c.get(x).getComboPosition()==ComboPosition.Diagonal){
				Coordinate left = getMostLeft(c.get(x));
				Coordinate right = getMostLeft(c.get(x));
				left.setY(left.getY()-1);
				right.setY(right.getY()+1);
				if(left.getX()>right.getX()){
					left.setX(left.getX()+1);
					right.setX(right.getX()-1);
				}else{
					left.setX(left.getX()-1);
					right.setX(right.getX()+1);
				}
				if(validMove(b,left)){
					im.add(left);
				}
				if(validMove(b,right)){
					im.add(right);
				}
			}else if(c.get(x).getComboPosition()==ComboPosition.none){
				Coordinate center = c.get(x).getCombo().get(0);
				Coordinate ontop = new Coordinate(center.getX()+1,center.getY());
				Coordinate left = new Coordinate(center.getX(),center.getY()-1);
				Coordinate right = new Coordinate(center.getX(),center.getY()+1);
				if(validMove(b,ontop)){
					im.add(ontop);
				}
				if(validMove(b,left)){
					im.add(left);
				}
				if(validMove(b,right)){
					im.add(right);
				}
			}
		}
		return im;
	}
	/*
	 * returns true or false if the board position is valid. 
	 */
	public boolean validMove(board b,Coordinate im){
		if(im.getX()>=0 && im.getX()<b.getRows() && im.getY()>=0 && im.getY()<b.getCols() && b.getIndex(im)==players.NULL && b.getIndex(im.getX()-1,im.getY())!=players.NULL){
			return true;
		}
		return false;
	}
	/*
	 * Gets the most left indexMemory of the combo
	 */
	public Coordinate getMostLeft(Combo c){
		Coordinate temp = c.getCombo().get(0);
		for(int x=0;x<c.getCombo().size();x++){
			if(temp.getY()>c.getCombo().get(x).getY()){
				temp = c.getCombo().get(x);
			}
		}
		return temp;
	}
	/*
	 * gets most right indexmemory of the combo
	 */
	public Coordinate getMostRight(Combo c){
		Coordinate temp = c.getCombo().get(0);
		for(int x=0;x<c.getCombo().size();x++){
			if(temp.getY()<c.getCombo().get(x).getY()){
				temp = c.getCombo().get(x);
			}
		}
		return temp;
	}
	/*
	 * Gets the most top indexMemory of the combo
	 */
	public Coordinate getMostTop(Combo c){
		Coordinate temp = c.getCombo().get(0);
		for(int x=0;x<c.getCombo().size();x++){
			if(temp.getX()<c.getCombo().get(x).getX()){
				temp = c.getCombo().get(x);
			}
		}
		return temp;
	}
	/*
	 * finds the biggest combo in a arrayList
	 */
	public ArrayList<Combo> findMaxCombos(ArrayList<Combo> combos){
		ArrayList<Combo> biggestCombo = new ArrayList<Combo>();
		int size=combos.get(0).ComboSize();
		biggestCombo.add(combos.get(0));
		for(int x=0;x<combos.size();x++){
			if(size<combos.get(x).ComboSize()){
				biggestCombo= new ArrayList<Combo>();
				biggestCombo.add(combos.get(x));
			}else if(size==combos.get(x).ComboSize()){
				biggestCombo.add(combos.get(x));
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
					ArrayList<Combo> temp = getCombos(x,y,b);
					//merge all combos (leaving out duplicates)
					for(int z=0;z<temp.size();z++){
						boolean shouldAdd = true;
						for(int a=0;a<combos.size();a++){
							if(combos.get(a).equals(temp.get(z))){
								shouldAdd=false;
							}
						}
						if(shouldAdd){
							combos.add(temp.get(z));
						}
					}
					//end merge
				}
			}
		}
		for(int x=0;x<combos.size();x++){
			System.out.print("Found a "+combos.get(x).getComboPosition().toString()+" combo: ");
			for(int y=0;y<combos.get(x).getCombo().size();y++){
				System.out.print(" x,y: "+combos.get(x).getCombo().get(y).toString());
			}
			System.out.println();
		}
		return combos;
	}
	
	/*
	 * Find all combos starting from the cords x,y on board b
	 */
	private ArrayList<Combo> getCombos(int x,int y,board b){
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
		return temp;
	}
	/*
	 * helperCheck goes through each of the directions. 
	 */
	private Combo helperCheck(int mode, int x, int y,board b,Combo c){
		if(x<0 || y<0 || x>=b.getRows() || y>=b.getCols()){
			return c;
		}
		if(b.getIndex(x, y)!=player){
			if(c.ComboSize()>1){
				if(mode==0 || mode==4){
					c.setComboPosition(ComboPosition.Horizontal);
				}else if(mode==1 || mode==3){
					c.setComboPosition(ComboPosition.Diagonal);
				}else if(mode==2){
					c.setComboPosition(ComboPosition.Vertical);
				}
			}else{
				c.setComboPosition(ComboPosition.none);
			}
			return c;
		}
		if(mode==0){//horizontal right
			c.addToCombo(new Coordinate(x,y));
			return helperCheck(0,x,y+1,b,c);
		}else if(mode==1){//diag bot right
			c.addToCombo(new Coordinate(x,y));
			return helperCheck(1,x-1,y+1,b,c);
		}else if(mode==2){// vertical
			c.addToCombo(new Coordinate(x,y));
			return helperCheck(2,x-1,y,b,c);
		}else if(mode==3){ // diag bot left
			c.addToCombo(new Coordinate(x,y));
			return helperCheck(3,x-1,y-1,b,c);
		}else if(mode==4){// horizontal left
			c.addToCombo(new Coordinate(x,y));
			return helperCheck(4,x,y-1,b,c);
		}else{
			return c;
		}
	}
}

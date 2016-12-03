import aiPackage.IndexMemory;

public class board {
	private int rows;
	private int cols;
	players winner=null;
	private players uhh[][];
	public board(int rows,int cols){
		this.rows=rows;
		this.cols=cols;
		uhh=new players[rows][cols];
		for(int x=0;x<rows;x++){
			for(int y=0;y<cols;y++){
				uhh[x][y]=players.NULL;
			}
		}
	}
	public void draw(){
		for(int x=rows-1;x>=0;x--){
			for(int y=0;y<cols;y++){
				System.out.print(uhh[x][y].getSymbol());
			}
			System.out.println();
		}
	}
	public boolean move(players player,int col){
		if(col>=cols){
			return false;
		}
		for(int x = 0 ;x<rows;x++){
			if(uhh[x][col]==players.NULL){
				uhh[x][col]=player;
				if(checkWon(x,col,player)==true){
					winner=player;
				}
				return true;
			}
		}
		return false;
	}
	public boolean hasWon(){
		if(winner==null){
			return false;
		}
		return true;
	}
	public players getIndex(IndexMemory im){
		if(im.getX()>rows || im.getY()>cols || im.getX()<0 || im.getY()<0){
			return null;
		}
		return uhh[im.getX()][im.getY()];
	}
	public players getIndex(int x,int y){
		if(x>rows || y>cols || x<0 || y<0){
			return null;
		}
		return uhh[x][y];
	}
	private boolean checkWon(int x,int y,players player){
		for(int asd=0;asd<5;asd++){
			if(helperCheck(asd,x,y,0,player)){
				return true;
			}
		}
		return false;
	}
	private boolean helperCheck(int mode, int x, int y,int count,players player){
		if(x<0 || y<0){
			return false;
		}
		if(uhh[x][y].getSymbol()==player.getSymbol()){
			count++;
			if(count==4)
				return true;
		}
		
		switch(mode){
		case 0:
			//right
			if(y+1<cols)
				return helperCheck(0,x,y+1,count,player);
			break;
		case 1:
			//diag bot right
			if(x-1>=0 && y+1<cols)
				return helperCheck(1,x-1,y+1,count,player);
			break;
		case 2:
			//vertical down
			if(x-1>=0)
				return helperCheck(2,x-1,y,count,player);
			break;
		case 3:
			//diag bot left
			if(x-1>=0 && y-1>=0)
				return helperCheck(3,x-1,y-1,count,player);
			break;
		case 4:
			//left
			if(y-1<=0)
				return helperCheck(4,x,y-1,count,player);
			break;
		}
		return false;
	}
	public boolean contains(players player){
		for(int x=0;x<rows;x++){
			for(int y=0;y<cols;y++){
				if(uhh[x][y]==player){
					return true;
				}
			}
		}
		return false;
	}
	public boolean isEmpty(){
		for(int x=0;x<rows;x++){
			for(int y=0;y<cols;y++){
				if(uhh[x][y]!=players.NULL){
					return false;
				}
			}
		}
		return true;
	}
	public boolean isEmpty(int x,int y){
		if(uhh[x][y]==players.NULL){
			return true;
		}
		return false;
	}
	public int getRows() {
		return rows;
	}
	public int getCols() {
		return cols;
	}
}

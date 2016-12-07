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
		for(int x=0;x<cols;x++){
			System.out.print(x);
		}
		System.out.println();;
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
		if(helperCheck(0,x,y,player)+helperCheck(1,x,y,player)==4){ //horizontal
			return true;
		}else if(helperCheck(2,x,y,player)+helperCheck(3,x,y,player)==4){ //diag / 
			return true;
		}else if(helperCheck(4,x,y,player)+helperCheck(5,x,y,player)==4){// diag \
			return true;
		}else if(helperCheck(6,x,y,player)==4){ // vertical
			return true;
		}
		return false;
	}
	private int helperCheck(int mode, int x, int y,players player){
		if(x<0 || y<0 || x>=rows || y>=cols){
			return 0;
		}
		if(uhh[x][y].getSymbol()==player.getSymbol()){
			if(mode==0){//horizontal right
				return helperCheck(0,x,y+1,player) + 1;
			}else if(mode==1){//horizontal left
				return helperCheck(1,x,y-1,player) + 1;
			}else if(mode==2){//diag / down left
				return helperCheck(2,x-1,y-1,player) + 1;
			}else if(mode==3){//diag / up left
				return helperCheck(3,x+1,y+1,player) + 1;
			}else if(mode==4){//diag \ down left
				return helperCheck(4,x-1,y+1,player) + 1;
			}else if(mode==5){//diag \ up right
				return helperCheck(5,x+1,y-1,player) + 1;
			}else if(mode==6){//vertical
				return helperCheck(6,x-1,y,player) + 1;
			}
		}else{
			return 0;
		}
		return 0;
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

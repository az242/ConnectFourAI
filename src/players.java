
public enum players {
	one('X'),
	two('T'),
	NULL('O');
	char symbol;
	public char getSymbol() {
		return symbol;
	}
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	players(char x){
		symbol=x;
	}
}

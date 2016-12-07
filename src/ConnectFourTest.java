
public class ConnectFourTest {
	public static void main(String[] args){
		board test = new board(5,6);
		test.move(players.two, 4);test.move(players.two, 1);test.move(players.two, 2);test.move(players.two, 3);
		test.draw();
		
		System.out.println( test.winner);
	}
}

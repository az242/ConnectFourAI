import java.util.Scanner;

public class main {
	public static void main(String[] args){
		//player one is user, player 2 is AI.
		board test = new board(5,6);
		AI ai = new AI(players.two);
		test.draw();
		Scanner x = new Scanner(System.in);
		//main turn loop
		while(!test.hasWon()){
			System.out.println("Enter your move");
			int col = x.nextInt();
			test.move(players.one, col);
			ai.AIMove(test);
			test.draw();
		}
		System.out.println(test.winner+" has won!");
	}
	
}

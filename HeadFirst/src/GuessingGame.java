
public class GuessingGame {

	private Player player1 =  new Player();
	private Player player2 =  new Player();
	private Player player3 =  new Player();
	
	public void startGame(){
		
		System.out.println("Im going to think a number...");
		
		int targetNumber =  (int)(Math.random()*10);
		
		
		
		int player1Number;
		int player2Number;
		int player3Number;
		boolean player1GuessOk = false;
		boolean player2GuessOk = false;
		boolean player3GuessOk = false;
		
		while(!player1GuessOk && !player2GuessOk && !player3GuessOk ){
			System.out.println("Please Guess The Number..." + targetNumber);
			player1Number = player1.startGuessing();
			player2Number = player1.startGuessing();
			player3Number = player1.startGuessing();
			
			
			System.out.println("Player 1 guess :" +player1Number);
			System.out.println("Player 2 guess :" +player2Number);
			System.out.println("Player 3 guess :" +player3Number);
			
			if(player1Number ==targetNumber){
				player1GuessOk =true;
			}else if(player2Number ==targetNumber){
				player2GuessOk = true;
			}
			else if(player3Number ==targetNumber){
				player3GuessOk = true;

			}
			
			System.out.println("Player 1 guess is :" +player1GuessOk);
			System.out.println("Player 2 guess is :" +player2GuessOk);
			System.out.println("Player 3 guess is :" +player3GuessOk);
			
			
		}
		
		
	}
	
}

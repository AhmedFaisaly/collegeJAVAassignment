package algonquin.cst8110.assignment4;

/*
 Lab Teacher: Howard Rosenblum
 Author:Wang,Tao 040857654
 course: CST8110 300 ;
 */
public class Assign4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game();
		game.welcome();
		game.getPotFromUser();
		while (game.isDone() == false) {
			game.getBetFromUser();
			while (game.getBetAmount() == 0) {
				if (game.playAgain() == false) {
					game.goodbye();
					game.setDone(true);
					break;
				} else {
					game.getBetFromUser();
				}
			}
			if (game.getDone()) {
				break;
			}
			game.getChoiceFromUser();
			game.playOneRound();
			if (game.getPotAmout() == 0) {
				if (game.playAgain() == false) {
                    game.goodbye();
					break;
				} else {
					game.getPotFromUser();
				}
			}

		}
	}

}

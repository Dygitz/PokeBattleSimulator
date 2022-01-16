package Main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import Pokemon.*;

public class BattleLogic {
	private boolean turn; //True = player1; False = player2
	private boolean gameOver;
	private Pokemon pokemon1;
	private Pokemon pokemon2;
	
	public BattleLogic() {
        ArrayList<Pokemon> pokemon = new ArrayList<Pokemon>(Arrays.asList(new Alakazam(), new Blastoise(), new Charizard(), new Clefable(), new Raichu(), new Snorlax(), new Venusaur(), new Cofagrigus()));
		int temp = (int) (pokemon.size()*Math.random());
		pokemon1 = pokemon.get(temp);
		pokemon.remove(temp);
		temp = (int) (pokemon.size()*Math.random());
		pokemon2 = pokemon.get(temp);
		gameOver = false;
		if(pokemon1.getSpeed() > pokemon2.getSpeed())
			turn = true;
		else if(pokemon2.getSpeed() > pokemon1.getSpeed())
			turn = false;
		else {
			Random random = new Random();
			turn = random.nextBoolean();
		}
    }

	public boolean getTurn() { return turn; }
	
	public void setTurn(boolean turn) { this.turn = turn; }
	
	public void setGameover(boolean gameOver) { this.gameOver = gameOver; }
	
	public boolean getGameOver() { return gameOver; }
	
	public Pokemon getPokemon1() { return pokemon1; }
	
	public Pokemon getPokemon2() { return pokemon2; }
	
	public void loopConsole() {
		Scanner userInput = new Scanner(System.in);
		int player1Move, player2Move;
		System.out.println("Player 1 is " + pokemon1.getName() + " and has " + pokemon1.getHp() + " hp");
		System.out.println("Player 2 is " + pokemon2.getName() + " and has " + pokemon2.getHp() + " hp");
		while(!gameOver) {
			//If the game is over
			if(!pokemon1.isAlive() || !pokemon2.isAlive()) {
				if(pokemon1.isAlive())
					System.out.println("Player 1 wins!");
				else
					System.out.println("Player 2 wins!");
				gameOver = true;
				break;
			}
			
			System.out.println("Player 1, what is your move? (1-4): ");
			player1Move = userInput.nextInt();
			pokemon1.move(pokemon2, player1Move);
			System.out.println("Player 1 hp: " + pokemon1.getHp());
			System.out.println("Player 2 hp: " + pokemon2.getHp());
			
			if(!pokemon1.isAlive() || !pokemon2.isAlive()) {
				if(pokemon1.isAlive())
					System.out.println("Player 1 wins!");
				else
					System.out.println("Player 2 wins!");
				gameOver = true;
				break;
			}
			
			System.out.println("Player 2, what is your move? (1-4): ");
			player2Move = userInput.nextInt();
			pokemon2.move(pokemon1, player2Move);
			System.out.println("Player 1 hp: " + pokemon1.getHp());
			System.out.println("Player 2 hp: " + pokemon2.getHp());
		}
	}
	
}

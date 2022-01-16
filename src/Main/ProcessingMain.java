package Main;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import controlP5.Button;
import controlP5.ControlEvent;
import controlP5.ControlFont;
import controlP5.ControlP5;

public class ProcessingMain extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Main.ProcessingMain");
	}
	
	BattleLogic game;
	int player1Move, player2Move;
	PImage background, pokemon1Sprite, pokemon2Sprite;
	final int SIDE_BAR_LENGTH = 300, BOTTOM_BAR_LENGTH = 150;
	ControlP5 cp5;
	PFont pFont;
	ControlFont controlFont;
	
	public void settings() {
		size(600 + 2*SIDE_BAR_LENGTH, 600 + BOTTOM_BAR_LENGTH);
	}
	
	public void setup() {
		game = new BattleLogic();
		pokemon1Sprite = loadImage(game.getPokemon1().getSpritePath());
		pokemon2Sprite = loadImage(game.getPokemon2().getSpritePath());
		background = loadImage("Main\\Background.png");
		background.resize(600, 600);
		pokemon1Sprite.resize(250, 0);
		pokemon2Sprite.resize(150, 0);
		cp5 = new ControlP5(this);
		cp5.setBroadcast(false);
		pFont = createFont("Main\\PokemonGb-RAeo.ttf", 20, true); // use true/false for smooth/no-smooth
		controlFont = new ControlFont(pFont, 16);
		Button[] player1Buttons = new Button[4];
		Button[] player2Buttons = new Button[4];
		textFont(pFont);
		for(int i = 0; i < game.getPokemon1().getMoves().length; i++)
			player1Buttons[i] = cp5.addButton(game.getPokemon1().getMoves()[i]).setValue(0).setPosition(25, 350+(i*100)).setSize(SIDE_BAR_LENGTH-45, 75);
		for(int i = 0; i < game.getPokemon2().getMoves().length; i++)
			player2Buttons[i] = cp5.addButton(game.getPokemon2().getMoves()[i]).setValue(1).setPosition(621+SIDE_BAR_LENGTH, 350+(i*100)).setSize(SIDE_BAR_LENGTH-45, 75);
		for(int i = 0; i < player1Buttons.length; i++)
			player1Buttons[i].getCaptionLabel().setFont(controlFont);
		for(int i = 0; i < player2Buttons.length; i++)
			player2Buttons[i].getCaptionLabel().setFont(controlFont);
		cp5.setBroadcast(true);
	}
	
	public void draw() {
		if(!game.getGameOver()) {
			//Outline
			background(255);
			image(background, SIDE_BAR_LENGTH, 0);
			image(pokemon1Sprite, 35 + SIDE_BAR_LENGTH, 300);
			image(pokemon2Sprite, 375 + SIDE_BAR_LENGTH, 110);
			strokeWeight(10);
			stroke(169);
			line(SIDE_BAR_LENGTH, 0, SIDE_BAR_LENGTH, height - BOTTOM_BAR_LENGTH);
			line(SIDE_BAR_LENGTH, height - BOTTOM_BAR_LENGTH, SIDE_BAR_LENGTH + 600, height - BOTTOM_BAR_LENGTH);
			line(SIDE_BAR_LENGTH + 600, height - BOTTOM_BAR_LENGTH, SIDE_BAR_LENGTH + 600, 0);
			line(SIDE_BAR_LENGTH + 600, 4, SIDE_BAR_LENGTH, 4);
			strokeWeight(9);
			stroke(169);
			line(4, 4, SIDE_BAR_LENGTH-6, 4);
			line(4, 4, 4, 600 + BOTTOM_BAR_LENGTH);
			line(4, 600 + BOTTOM_BAR_LENGTH-4, 600 + 2*SIDE_BAR_LENGTH, 600 + BOTTOM_BAR_LENGTH-4);
			line(600 + 2*SIDE_BAR_LENGTH-4, 600 + BOTTOM_BAR_LENGTH-4, 600 + 2*SIDE_BAR_LENGTH-4, 0);
			line(SIDE_BAR_LENGTH + 600, 4, 600 + 2*SIDE_BAR_LENGTH, 4);
			
			//Stats
			fill(50);
			textAlign(LEFT);
			textSize(17);
			for(int i = 0; i < game.getPokemon1().getStats().length; i++)
				text(game.getPokemon1().getStats()[i], 25, 50+(40*i));
			for(int i = 0; i < game.getPokemon2().getStats().length; i++)
				text(game.getPokemon2().getStats()[i], 625+SIDE_BAR_LENGTH, 50+(40*i));
			
			textAlign(CENTER, CENTER);
			textSize(26);
			if(!game.getPokemon1().isAlive() || !game.getPokemon2().isAlive()) {
				if(game.getPokemon1().isAlive())
					text("Player 1 wins!", (600 + 2*SIDE_BAR_LENGTH)/2, 660);
				else
					text("Player 2 wins!", (600 + 2*SIDE_BAR_LENGTH)/2, 660);
				game.setGameover(true);
				cp5.addButton("Restart").setPosition((600 + 2*SIDE_BAR_LENGTH)/2-85, 550+BOTTOM_BAR_LENGTH-15).setSize(170, 50).getCaptionLabel().setFont(controlFont);
				return;
			}
			if(game.getTurn())
				text("It is player 1's turn!", (600 + 2*SIDE_BAR_LENGTH)/2, 660);
			else
				text("It is player 2's turn!", (600 + 2*SIDE_BAR_LENGTH)/2, 660);
		}
	}
	
	public void controlEvent(ControlEvent event) {
		if(game.getTurn()) {
			for(int i = 0; i < game.getPokemon1().getMoves().length; i++) {
				if(event.getController().getName().equals(game.getPokemon1().getMoves()[i])) {
					game.getPokemon1().move(game.getPokemon2(), i+1);
					game.setTurn(false);
					break;
				}
			}
		}
		
		else {
			for(int j = 0; j < game.getPokemon2().getMoves().length; j++) {
				if(event.getController().getName().equals(game.getPokemon2().getMoves()[j])) {
					game.getPokemon2().move(game.getPokemon1(), j+1);
					game.setTurn(true);
					break;
				}
			}
		}
	}
	
	public void Restart() {
		game = new BattleLogic();
		pokemon1Sprite = loadImage(game.getPokemon1().getSpritePath());
		pokemon2Sprite = loadImage(game.getPokemon2().getSpritePath());
		pokemon1Sprite.resize(250, 0);
		pokemon2Sprite.resize(150, 0);
		cp5.setBroadcast(false);
		Button[] player1Buttons = new Button[4];
		Button[] player2Buttons = new Button[4];
		for(int i = 0; i < game.getPokemon1().getMoves().length; i++)
			player1Buttons[i] = cp5.addButton(game.getPokemon1().getMoves()[i]).setValue(0).setPosition(25, 350+(i*100)).setSize(SIDE_BAR_LENGTH-45, 75);
		for(int i = 0; i < game.getPokemon2().getMoves().length; i++)
			player2Buttons[i] = cp5.addButton(game.getPokemon2().getMoves()[i]).setValue(1).setPosition(621+SIDE_BAR_LENGTH, 350+(i*100)).setSize(SIDE_BAR_LENGTH-45, 75);
		for(int i = 0; i < player1Buttons.length; i++)
			player1Buttons[i].getCaptionLabel().setFont(controlFont);
		for(int i = 0; i < player2Buttons.length; i++)
			player2Buttons[i].getCaptionLabel().setFont(controlFont);
		cp5.setBroadcast(true);
		cp5.getController("Restart").remove();
	}

}

package Pokemon;

import Main.Pokemon;

public class Charizard extends Pokemon {
	
	public Charizard() {
		//maxHp, atk, def, spAtk, spDef, speed;
		super(78, 84, 78, 109, 85, 100, "Fire");
	}
	
	public void move(Pokemon other, int choice) {
		if(choice == 1)
			dragonClaw(other);
		else if(choice == 2)
			ember(other);
		else if(choice == 3)
			airSlash(other);
		else if(choice == 4)
			growl(other);
	}

	public String getName() {
		return "Charizard";
	}

	private void dragonClaw(Pokemon other) {
		damage(other, "atk", 80, "Dragon");
	}

	private void ember(Pokemon other) {
		damage(other, "spAtk", 60, "Fire");
	}

	private void airSlash(Pokemon other) {
		//95% Accuracy
		if((int) (Math.random()*20) != 0)
			damage(other, "spAtk", 75, "Flying");
	}

	private void growl(Pokemon other) {
		other.setDef(other.getDef()/2);
	}

	public String[] getMoves() { 
		return new String[] {"Dragon Claw", "Ember", "Air Slash", "Growl"};
	}
	
}

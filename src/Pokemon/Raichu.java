package Pokemon;

import Main.Pokemon;

public class Raichu extends Pokemon {
	
	public Raichu() {
		//maxHp, atk, def, spAtk, spDef, speed;
		super(60, 90, 55, 90, 80, 110, "Electric");
	}
	
	public void move(Pokemon other, int choice) {
		if(choice == 1)
			thunderbolt(other);
		else if(choice == 2)
			electroBall(other);
		else if(choice == 3)
			slam(other);
		else if(choice == 4)
			thunderPunch(other);
	}

	public String getName() {
		return "Raichu";
	}

	private void thunderbolt(Pokemon other) {
		damage(other, "spAtk", 90, "Electric");
		//10% Chance
		if(!other.getType().equals("Electric") && (int) (Math.random()*10) == 0)
			other.setParalyzed(true);
	}

	private void electroBall(Pokemon other) {
		double ratio = (double) getSpeed() / other.getSpeed();
		int power;
		if(ratio < 1)
			power = 40;
		else if(ratio < 2)
			power = 60;
		else if(ratio < 3)
			power = 80;
		else if(ratio < 4)
			power = 120;
		else
			power = 150;
		damage(other, "spAtk", power, "Electric");
	}

	private void slam(Pokemon other) {
		//75% Chance
		if((int) (Math.random()*4) != 0)
			damage(other, "atk", 80, "Normal");
	}

	private void thunderPunch(Pokemon other) {
		damage(other, "spAtk", 75, "Electric");
		//10% Chance
		if(!other.getType().equals("Electric") && (int) (Math.random()*10) == 0)
			other.setParalyzed(true);
	}

	public String[] getMoves() { 
		return new String[] {"Thunderbolt", "Electro Ball", "Slam", "Thunder Punch"};
	}
	
}

package Pokemon;

import Main.Pokemon;

public class Cofagrigus extends Pokemon {
	
	public Cofagrigus() {
		//maxHp, atk, def, spAtk, spDef, speed;
		super(58, 50, 145, 95, 105, 30, "Ghost");
	}

	public String getName() {
		return "Cofagrigus";
	}
	
	public void move(Pokemon other, int choice) {
		if(choice == 1)
			darkPulse(other);
		else if(choice == 2)
			shadowClaw(other);
		else if(choice == 3)
			Astonish(other);
		else if(choice == 4)
			Haze(other);
	}

	private void darkPulse(Pokemon other) {
		damage(other, "spAtk", 80, "Dark");
	}	

	private void shadowClaw(Pokemon other) {
		damage(other, "atk", 70, "Ghost");
	}

	private void Astonish(Pokemon other) {
		damage(other, "atk", 30, "Ghost");
	}

	private void Haze(Pokemon other) {
		setAtk(getBaseAtk());
		setDef(getBaseDef());
		setSpAtk(getSpAtk());
		setSpDef(getSpDef());
		other.setSpAtk(other.getSpAtk());
		other.setSpDef(other.getSpDef());
		other.setAtk(other.getBaseAtk());
		other.setDef(other.getBaseDef());
	}
	
	public String[] getMoves() { 
		return new String[] {"Dark Pulse", "Shadow Claw", "Astonish", "Haze"};
	}
}

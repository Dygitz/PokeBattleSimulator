package Pokemon;

import Main.Pokemon;

public class Clefable extends Pokemon {
	
	public Clefable() {
		//maxHp, atk, def, spAtk, spDef, speed;
		super(95, 70, 73, 95, 90, 60, "Fairy");
	}
	
	public void move(Pokemon other, int choice) {
		if(choice == 1)
			disarmingVoice(other);
		else if(choice == 2)
			charm(other);
		else if(choice == 3)
			meteorMash(other);
		else if(choice == 4)
			moonBlast(other);
	}

	public String getName() {
		return "Clefable";
	}

	private void disarmingVoice(Pokemon other) {
		damage(other, "spAtk", 40, "Fairy");
	}

	private void charm(Pokemon other) {
		other.setDef(other.getDef()/3);
	}

	private void meteorMash(Pokemon other) {
		//90% Accuracy
		if((int) (Math.random()*10) != 0) {
			damage(other, "atk", 90, "Steel");
			//20% Chance of Raising Attack
			if((int) (Math.random()*5) == 0)
				setAtk(getAtk()*2);
		}

	}

	private void moonBlast(Pokemon other) {
		damage(other, "spAtk", 95, "Fairy");
		//30% Chance
		if((int) (Math.random()*10) < 3)
			other.setSpAtk(other.getSpAtk()/2);
	}

	public String[] getMoves() { 
		return new String[] {"Disarming Voice", "Charm", "Meteor Mash", "Moon Blast"};
	}
	
}

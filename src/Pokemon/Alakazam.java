package Pokemon;

import Main.Pokemon;

public class Alakazam extends Pokemon {
	
	public Alakazam() {
		//maxHp, atk, def, spAtk, spDef, speed;
		super(55, 50, 45, 135, 95, 120, "Psychic");
	}
	
	public String getName() {
		return "Alakazam";
	}

	public void move(Pokemon other, int choice) {
		if(choice == 1)
			calmMind();
		else if(choice == 2)
			psychoCut(other);
		else if(choice == 3)
			psychic(other);
		else if(choice == 4)
			thunderWave(other);
	}
	
	private void calmMind() {
		if(getSpAtk() / getBaseSpAtk() < 4)
			setSpAtk(getSpAtk() + getBaseSpAtk()/2);
		if(getSpDef() / getBaseSpDef() < 4)
			setSpDef(getSpDef() + getBaseSpDef()/2);
	}
	
	private void psychoCut(Pokemon other) {
		damage(other, "atk", 70, "Psychic");
	}
	
	private void psychic(Pokemon other) {
		damage(other, "spAtk", 90, "Psychic");
		//10% chance
		if((int) (Math.random()*10) == 0)
			other.setSpDef(other.getSpDef()/2);
	}
	
	private void thunderWave(Pokemon other) {
		//90% chance
		if(!other.getType().equals("Electric") && (int) (Math.random()*10) != 0) {
			other.setSpeed(other.getSpeed()/2);
			other.setParalyzed(true);
		}
	}

	public String[] getMoves() { 
		return new String[] {"Calm Mind", "Psycho Cut", "Psychic", "Thunderwave"};
	}
	
}

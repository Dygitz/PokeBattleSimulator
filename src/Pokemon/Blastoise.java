package Pokemon;

import Main.Pokemon;

public class Blastoise extends Pokemon {
	
	public Blastoise() {
		//maxHp, atk, def, spAtk, spDef, speed;
		super(79, 83, 100, 85, 105, 78, "Water");
	}
	
	public String getName() {
		return "Blastoise";
	}
	
	public void move(Pokemon other, int choice) {
		if(choice == 1)
			hydroPump(other);
		else if(choice == 2)
			ironDefense();
		else if(choice == 3)
			aquaTail(other);
		else if(choice == 4)
			bite(other);
	}
	
	private void hydroPump(Pokemon other) {
		//80% chance
		if((int) (Math.random()*5) != 0)
			damage(other, "spAtk", 110, "Normal");
	}
	
	private void ironDefense() {
		if(getDef() / getBaseDef() < 4)
			setDef(getDef() + getBaseDef()/2);
	}
	
	private void aquaTail(Pokemon other) {
		//90% chance
		if((int) (Math.random()*10) != 0)
			damage(other, "atk", 90, "Water");
	}
	
	private void bite(Pokemon other) {
		damage(other, "atk", 60, "Dark");
	}
	
	public String[] getMoves() { 
		return new String[] {"Hydro Pump", "Iron Defense", "Aqua Tail", "Bite"};
	}
	
}

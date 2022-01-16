package Pokemon;

import Main.Pokemon;

public class Venusaur extends Pokemon {
	
	public Venusaur() {
		//maxHp, atk, def, spAtk, spDef, speed;
		super(80, 82, 83, 100, 100, 80, "Grass");
	}
	
	public void move(Pokemon other, int choice) {
		if(choice == 1)
			petalBlizzard(other);
		else if(choice == 2)
			tackle(other);
		else if(choice == 3)
			doubleEdge(other);
		else if(choice == 4)
			synthesis();
	}

	public String getName() {
		return "Venusaur";
	}

	private void petalBlizzard(Pokemon other) {
		damage(other, "atk", 90, "Grass");
	}

	private void tackle(Pokemon other) {
		damage(other, "atk", 40, "Normal");
	}

	private void doubleEdge(Pokemon other) {
		int oldHp = other.getHp();
		damage(other, "atk", 120, "Normal");
		int damageDealt = oldHp - other.getHp();
		setHp(getHp()- (int) (damageDealt/3));
	}

	private void synthesis() {
		setHp(getHp() + getMaxHp()/2);
		if(getHp() > getMaxHp())
			setHp(getMaxHp());
	}

	public String[] getMoves() { 
		return new String[] {"Petal Blizzard", "Tackle", "Double Edge", "Synthesis"};
	}


	
}

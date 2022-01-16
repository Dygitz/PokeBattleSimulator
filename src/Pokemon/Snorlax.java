package Pokemon;
import Main.Pokemon;


public class Snorlax extends Pokemon {

	public Snorlax() {
		//maxHp, atk, def, spAtk, spDef, speed;
		super(160, 110, 65, 65, 110, 30, "Normal");
	}
	
	public void move(Pokemon other, int choice) {
		if(choice == 1)
			bodySlam(other);
		else if(choice == 2)
			lick(other);
		else if(choice == 3)
			recover();
		else if(choice == 4)
			crunch(other);
	}

	public String getName() {
		return "Snorlax";
	}

	private void bodySlam(Pokemon other) {
		damage(other, "atk", 85, "Normal");
		//30% Chance
		if(!other.getType().equals("Electric") && (int) (Math.random()*10) < 3)
			other.setParalyzed(true);
	}

	private void lick(Pokemon other) {
		damage(other, "atk", 30, "Ghost");
		//30% Chance
		if(!other.getType().equals("Electric") && (int) (Math.random()*10) < 3)
			other.setParalyzed(true);
	}

	private void recover() {
		setHp(getHp() + getMaxHp()/2);
		if(getHp() > getMaxHp())
			setHp(getMaxHp());
	}

	private void crunch(Pokemon other) {
		damage(other, "atk", 80, "Dark");
		//20% Chance
		if((int) (Math.random()*5) == 0)
			other.setDef(other.getDef()/2);
	}

	public String[] getMoves() { 
		return new String[] {"Body Slam", "Lick", "Recover", "Crunch"};
	}

}

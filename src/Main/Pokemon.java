package Main;

import java.util.ArrayList;

public abstract class Pokemon {
	private int hp, atk, def, spAtk, spDef, speed;
	private final int MAX_HP, BASE_ATK, BASE_DEF, BASE_SP_ATK, BASE_SP_DEF, BASE_SPEED;
	private String type;
	private boolean paralyzed;

	public Pokemon(int maxHp, int atk, int def, int spAtk, int spDef, int speed, String type) {
		this.hp = maxHp;
		this.atk = atk;
		this.def = def;
		this.spAtk = spAtk;
		this.spDef = spDef;
		this.speed = speed;
		this.type = type;
		
		this.MAX_HP = maxHp;
		this.BASE_ATK = atk;
		this.BASE_DEF = def;
		this.BASE_SP_ATK = spAtk;
		this.BASE_SP_DEF = spDef;
		this.BASE_SPEED = speed;
	}

	public abstract void move(Pokemon other, int choice);
	
	public String[] getStats() {
		return new String[] {"Name: " + getName(), "Hp: " + hp + " (" + (int) ((double) hp/MAX_HP*100) + ")", "Atk: "+atk, "Def: "+def, "spAtk: "+spAtk, "spDef: "+spDef, "Spe: "+speed, "Type: "+type};
	}
	
	public abstract String[] getMoves();
	
	public String getSpritePath() {
		return "PokeSprites\\" + getName() + ".png";
	}
	
	public abstract String getName();
	
	private ArrayList<String> getWeaknesses(String type) {
		return JSONFileReader.readJSON(type, "weaknesses");
	}

	private ArrayList<String> getStrengths(String type) {
		return JSONFileReader.readJSON(type, "strengths");
	}
	
	private ArrayList<String> getImmunities(String type) {
		return JSONFileReader.readJSON(type, "immunes");
	}
	
	public boolean isAlive() {
		return hp > 0;
	}
	
	public void heal(int amt) {
		if(hp + amt > MAX_HP)
			hp = MAX_HP;
		else
			hp += amt;
	}
	
	public void damage(Pokemon other, String atkType, double atkPower, String moveType) {
		/*
		 * a = attacker's Level
		 * b = attacker's Attack or Special
		 * c = attack Power
		 * d = defender's Defense or Special
		 * x = same-Type attack bonus (1 or 1.5)
		 * y = Type modifiers (40, 20, 10, 5, 2.5, or 0)
		 * z = a random number between 217 and 255
		 * atkType = either "atk" or "spAtk"
		 */
		double a, b = 0, c, d = 0, x, y, z;
		
		a = 100;
		c = atkPower;
		
		if(type.equals(moveType))
			x = 1.5;
		else
			x = 1;
		
		if(atkType.equals("atk")) {
			b = atk;
			d = other.def;
		}
		else if(atkType.equals("spAtk")) {
			b = spAtk;
			d = other.spDef;
		}
		
		if(getStrengths(moveType).contains(other.type))
			y = 2;
		else if(getWeaknesses(moveType).contains(other.type))
			y = 0.5;
		else if(getImmunities(moveType).contains(other.type))
			y = 0;
		else
			y = 1;
		
		z = (Math.random()*39) + 217;
		
		int damage = (int) (((((((((2*a)/5 + 2)*b*c)/d)/50)+2)*x)*y/10)*z)/255;
		
		//75% chance to damage if paralyzed
		if(paralyzed && (int) (Math.random()*4) != 0) {
			if(other.hp - damage < 0)
				other.hp = 0;
			else
				other.hp -= damage;
		}
		else {
			if(other.hp - damage < 0)
				other.hp = 0;
			else
				other.hp -= damage;
		}
	}

	public int getHp() { return hp; }

	public void setHp(int hp) { this.hp = hp; }

	public int getAtk() { return atk; }

	public void setAtk(int atk) { this.atk = atk; }

	public int getDef() { return def; }

	public void setDef(int def) { this.def = def; }

	public int getSpAtk() { return spAtk; }

	public void setSpAtk(int spAtk) { this.spAtk = spAtk; }

	public int getSpDef() { return spDef; }

	public void setSpDef(int spDef) { this.spDef = spDef; }

	public int getSpeed() { return speed; }

	public void setSpeed(int speed) { this.speed = speed; }

	public int getMaxHp() { return MAX_HP; }
	
	public String getType() { return type; }
	
	public int getBaseAtk() { return BASE_ATK; }

	public int getBaseDef() { return BASE_DEF; }

	public int getBaseSpAtk() { return BASE_SP_ATK; }

	public int getBaseSpDef() { return BASE_SP_DEF; }

	public int getBaseSpeed() { return BASE_SPEED; }
	
	public boolean isParalyzed() { return paralyzed; }

	public void setParalyzed(boolean paralyzed) { this.paralyzed = paralyzed; }
}

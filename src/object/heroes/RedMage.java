package object.heroes;

import object.basecard.Hero;
import object.spells.Fireblast;

public class RedMage extends Hero {
	private static final Fireblast power = new Fireblast();

	public RedMage() {
		super("Red Mage", "TOP TEIR MAGE OF THE MAGIC TOWER", 35, power);
	}

	public Fireblast getPower() {
		return power;
	}

}
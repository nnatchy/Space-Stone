package object.heroes;

import object.basecard.Hero;
import object.basecard.Minion;
import object.spells.Innerrage;

public class BadWitch extends Hero {
	private static final Innerrage power = new Innerrage();

	public BadWitch() {
		super("Bad Witch", "OUTLAW MAGICIAN", 35, power);
	}

	public void skill(Minion target) {
		power.cast(target);
	}

	public Innerrage getPower() {
		return power;
	}

}

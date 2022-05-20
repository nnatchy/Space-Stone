package object.heroes;

import object.basecard.Hero;
import object.spells.Heal;

public class LightPaladin extends Hero {

	private static final Heal power = new Heal();

	public LightPaladin() {
		super("Light Paladin", "PALADIN OF THE LIGHT", 50, power);
	}

	public Heal getPower() {
		return power;
	}

}

package object.heroes;

import object.basecard.Hero;
import object.spells.Buff;

public class LightHawk extends Hero {
	private static final Buff power = new Buff();

	public LightHawk() {
		super("Light Hawk", "IS THAT HAWK OR EAGLE?", 40, power);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getName() + "\n" + this.getDescription() + "\n" + this.getPower();
	}

	public Buff getPower() {
		return power;
	}

}

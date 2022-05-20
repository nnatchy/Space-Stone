package object.heroes;

import object.basecard.Hero;
import object.spells.Quickrecruit;

public class OgreKing extends Hero {
	private static final Quickrecruit power = new Quickrecruit();

	public OgreKing() {
		super("Ogre King", "KING OF ORGE HOARD", 40, power);
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		// TODO Auto-generated method stub
		return this.getName() + "\n" + this.getDescription() + "\n" + this.getPower();
	}

	public Quickrecruit getPower() {
		return power;
	}

}

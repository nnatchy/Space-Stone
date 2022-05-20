package object.spells;

import Interface.AttackMinion;
import object.basecard.Minion;
import object.basecard.Spell;

public class Fireblast extends Spell implements AttackMinion {

	public Fireblast() {
		super("Fireblast", "REDUCE 1 HEALTH OF THE TARGET", 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cast(Minion target) {
		// TODO Auto-generated method stub
		target.damageMinion(1);
	}

}

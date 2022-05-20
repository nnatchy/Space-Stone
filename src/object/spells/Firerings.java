package object.spells;

import Interface.AttackMinion;
import object.basecard.Minion;
import object.basecard.Spell;

public class Firerings extends Spell implements AttackMinion {
	public Firerings() {
		super("Fire Rings", "REDUCE 1 HEALTH OF ALL OPPONENT MINIONS", 2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cast(Minion target) {
		// TODO Auto-generated method stub
		for (Minion minion : target.getOwner().getMinionBoard()) {
			minion.damageMinion(1);
		}
	}

}

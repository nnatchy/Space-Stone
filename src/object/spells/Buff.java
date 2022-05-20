package object.spells;

import Interface.BuffMinion;
import object.basecard.Minion;
import object.basecard.Spell;

public class Buff extends Spell implements BuffMinion {

	public Buff() {
		super("Buff", "INCREASE 1 ATTACK AND 2 HEALTH OF THE TARGET", 1);
		// TODO Auto-generated constructor stub
		this.setCost(4);
	}

	@Override
	public void cast(Minion target) {
		// TODO Auto-generated method stub
		target.buffMinion(1, 2);
	}

}

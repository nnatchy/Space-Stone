package object.spells;

import Interface.BuffMinionAble;
import object.basecard.Minion;
import object.basecard.Spell;

public class Heal extends Spell implements BuffMinionAble {
	public Heal() {
		super("Heal", "HEAL 2 HEALTH OF THE TARGET", 1);
		// TODO Auto-generated constructor stub
		this.setCost(2);
	}

	@Override
	public void cast(Minion target) {
		// TODO Auto-generated method stub
		target.healMinion(2);
	}
}

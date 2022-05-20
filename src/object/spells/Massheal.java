package object.spells;

import Interface.BuffMinionAble;
import object.basecard.Minion;
import object.basecard.Spell;

public class Massheal extends Spell implements BuffMinionAble {

	public Massheal() {
		super("Mass Heal", "HEAL 2 HEALTH OF ALL ALLY MINIONS", 2);
		// TODO Auto-generated constructor stub
		this.setCost(5);
	}

	public void cast(Minion target) {
		// TODO Auto-generated method stub
		for (Minion minion : target.getOwner().getMinionBoard()) {
			minion.healMinion(2);
		}
	}

}

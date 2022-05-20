package object.spells;

import Interface.BuffMinionAble;
import object.basecard.Minion;
import object.basecard.Spell;

public class Powerinfusion extends Spell implements BuffMinionAble {

	public Powerinfusion() {
		super("Power Infusion", "INCREASE 2 ATTACK AND 6 HEALTH OF THE TARGET", 3);
		this.setCost(8);
	}

	public void cast(Minion target) {
		target.buffMinion(2, 6);
	}

}

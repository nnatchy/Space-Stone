package object.spells;

import Interface.AttackMinionAble;
import object.basecard.Minion;
import object.basecard.Spell;

public class InstantKill extends Spell implements AttackMinionAble {

	public InstantKill() {
		super("Instant Kill", "DESTROY TARGET MINION", 3);
	}

	public void cast(Minion target) {
		target.setHealth(0);
	}

}

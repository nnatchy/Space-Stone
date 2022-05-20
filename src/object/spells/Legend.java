package object.spells;

import Interface.SpawnMinion;
import Logic.GameLogic;
import object.basecard.Spell;
import object.minions.Dragon;
import player.Player;

public class Legend extends Spell implements SpawnMinion {

	public Legend() {
		super("Legend", "SUMMON 12 HEALTH AND 12 ATTACK MINION", 3);
		// TODO Auto-generated constructor stub
		this.setCost(12);
	}
// summon 12/12 minion

	public void cast() {
		// TODO Auto-generated method stub
		Dragon dragon = new Dragon();
		Player player = GameLogic.checkPlayer();
		player.spawnMinion(dragon);
	}

}

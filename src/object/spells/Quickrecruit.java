package object.spells;

import Interface.SpawnMinionAble;
import Logic.GameLogic;
import object.basecard.Spell;
import object.minions.Gobuta;
import player.Player;

public class Quickrecruit extends Spell implements SpawnMinionAble {

	public Quickrecruit() {
		super("Quick Recruit", "SUMMON 1 LEVEL GOBUTA", 1);
		// TODO Auto-generated constructor stub
		this.setCost(5);
	}

	public void cast() {
		// TODO Auto-generated method stub
		Gobuta gobuta = new Gobuta();
		Player player = GameLogic.checkPlayer();
		player.spawnMinion(gobuta);
	}

}

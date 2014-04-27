package rltut;

public class PlayerAi extends CreatureAi {
	@Override
	public void onEnter(int x, int y, Tile tile) {
		if (tile.isGround()) {
			creature.x = x;
			creature.y = y;
		} else if (tile.isDiggable()) {
			creature.dig(x, y);
		}
	}

	public PlayerAi(Creature creature) {
		super(creature);
	}
}
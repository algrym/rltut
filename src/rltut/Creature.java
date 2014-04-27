package rltut;

import java.awt.Color;

public class Creature {
	private final World world;
	private CreatureAi ai;

	public void setCreatureAi(CreatureAi ai) {
		this.ai = ai;
	}

	public int x, y;

	private final char glyph;

	public char getGlyph() {
		return glyph;
	}

	private final Color color;

	public Color getColor() {
		return color;
	}

	public Creature(World world, char glyph, Color color) {
		this.world = world;
		this.glyph = glyph;
		this.color = color;
	}

	public void dig(int wx, int wy) {
		world.dig(wx, wy);
	}

	public void moveBy(int mx, int my) {
		Creature other = world.creature(x + mx, y + my);

		if (other == null)
			ai.onEnter(x + mx, y + my, world.tile(x + mx, y + my));
		else
			attack(other);
	}

	public void attack(Creature other) {
		world.remove(other);
	}

	public void update() {
		ai.onUpdate();
	}

	public boolean canEnter(int wx, int wy) {
		return world.tile(wx, wy).isGround() && world.creature(wx, wy) == null;
	}
}

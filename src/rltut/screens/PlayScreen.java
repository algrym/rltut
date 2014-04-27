package rltut.screens;

import java.awt.event.KeyEvent;

import rltut.Creature;
import rltut.CreatureFactory;
import rltut.World;
import rltut.WorldBuilder;
import asciiPanel.AsciiPanel;

public class PlayScreen implements Screen {
	private World world;

	private final Creature player;

	private final int screenWidth;
	private final int screenHeight;

	public PlayScreen() {
		screenWidth = 80;
		screenHeight = 21;
		createWorld();

		CreatureFactory creatureFactory = new CreatureFactory(world);
		player = creatureFactory.newPlayer();
	}

	private void createWorld() {
		world = new WorldBuilder(90, 31).makeCaves().build();
	}

	public int getScrollX() {
		return Math.max(
				0,
				Math.min(player.x - screenWidth / 2, world.getWidth()
						- screenWidth));
	}

	public int getScrollY() {
		return Math.max(
				0,
				Math.min(player.y - screenHeight / 2, world.getHeight()
						- screenHeight));
	}

	private void displayTiles(AsciiPanel terminal, int left, int top) {
		for (int x = 0; x < screenWidth; x++) {
			for (int y = 0; y < screenHeight; y++) {
				int wx = x + left;
				int wy = y + top;

				terminal.write(world.glyph(wx, wy), x, y, world.color(wx, wy));
			}
		}
	}

	private void scrollBy(int mx, int my) {
		player.x = Math.max(0, Math.min(player.x + mx, world.getWidth() - 1));
		player.y = Math.max(0, Math.min(player.y + my, world.getHeight() - 1));
	}

	@Override
	public void displayOutput(AsciiPanel terminal) {
		int left = getScrollX();
		int top = getScrollY();

		displayTiles(terminal, left, top);

		terminal.write(player.getGlyph(), player.x - left, player.y - top,
				player.getColor());
		
		terminal.write("You are having fun.", 1, 1);
		terminal.writeCenter("-- press [escape] to lose or [enter] to win --",
				22);
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		switch (key.getKeyCode()) {
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_H:
			scrollBy(-1, 0);
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_L:
			scrollBy(1, 0);
			break;
		case KeyEvent.VK_UP:
		case KeyEvent.VK_K:
			scrollBy(0, -1);
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_J:
			scrollBy(0, 1);
			break;
		case KeyEvent.VK_Y:
			scrollBy(-1, -1);
			break;
		case KeyEvent.VK_U:
			scrollBy(1, -1);
			break;
		case KeyEvent.VK_B:
			scrollBy(-1, 1);
			break;
		case KeyEvent.VK_N:
			scrollBy(1, 1);
			break;
		case KeyEvent.VK_ESCAPE:
			return new LoseScreen();
		case KeyEvent.VK_ENTER:
			return new WinScreen();
		}
		return this;
	}
}

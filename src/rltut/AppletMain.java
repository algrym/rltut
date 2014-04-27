package rltut;

import java.applet.Applet;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import rltut.screens.Screen;
import rltut.screens.StartScreen;
import asciiPanel.AsciiPanel;

public class AppletMain extends Applet implements KeyListener {
	private static final long serialVersionUID = -7229682743009622702L;

	private final AsciiPanel terminal;
	private Screen screen;

	public AppletMain() {
		super();
		terminal = new AsciiPanel();
		add(terminal);
		screen = new StartScreen();
		addKeyListener(this);
		repaint();
	}

	@Override
	public void init() {
		super.init();
		this.setSize(terminal.getWidth() + 20, terminal.getHeight() + 20);
	}

	@Override
	public void repaint() {
		terminal.clear();
		screen.displayOutput(terminal);
		super.repaint();
	}

	@Override
	public void keyPressed(KeyEvent key) {
		screen = screen.respondToUserInput(key);
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent key) {
	}

	@Override
	public void keyTyped(KeyEvent key) {
	}
}

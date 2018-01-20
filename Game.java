/**
 * Game.java
 * @author Yusuf A. Taha
 * @see GamePanel.java
 */
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Game extends JFrame implements ActionListener, KeyListener
{
	Timer tmr; // Swing.Timer
	GamePanel game; // a gamepanel that controls the player component

	/**
	 * CONSTRUCTOR
	 */
    public Game()
	{
		super("Bulletzer");    // Calls JFrame's constructor
		setSize(600,800);      // sets the window size
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);

		tmr = new Timer(10, this); // listens to actions every 10 ms
		tmr.start();               // starts the timer

		game = new GamePanel(); // creates the player's game panel
		add(game); // adds the game panel to the Game JFrame

		addKeyListener(this);
    }

	// ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈
	//                                  Actionlistener methods
	// ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈

	/**
	 * invoked whenever an action occurs
	 * @param evt an actionevent
	 */
	public void actionPerformed(ActionEvent evt)
	{
		if(game != null) {game.refresh(); game.repaint();}
	}

	// ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈
	//                                    keylistner methods
	// ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈

	/**
	 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯UNUSED⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
	 * invoked when a key is "typed"
	 * @param keyevt an keyeventevent
	 */
    public void keyTyped(KeyEvent keyevt) {}

	/**
	 * invoked when a key is "pressed"
	 * @param keyevt an keyeventevent
	 */
    public void keyPressed(KeyEvent keyevt) {game.setKey(keyevt.getKeyCode(),true);}

	/**
	 * invoked when a key is "released"
	 * @param keyevt an keyeventevent
	 */
    public void keyReleased(KeyEvent keyevt) {game.setKey(keyevt.getKeyCode(),false);}

	// ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈
	// 			 							   Main
	// ┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈

    public static void main(String[] arguments) {Game frame = new Game();}
}
/**
 * Game.java
 * @author Yusuf A. Taha
 * Where the magic happens
 *         🎩
 */

import java.util.LinkedList;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

// TODO add alien objects
public final class Game extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;

	private final Timer CLOCK = new Timer(10, this); // listens to actions every 10 ms
	private final GamePanel GAMEPANEL; // a gamepanel that controls the player component

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

		CLOCK.start();		  // starts the timer

		GAMEPANEL = new GamePanel(); // creates the player's game panel
		add(GAMEPANEL); // adds the game panel to the Game JFrame
    }

	/**
	 * invoked whenever an action occurs
	 * @param evt : an actionevent that occurred
	 * @see gamepanel#refresh()
	 */
	public void actionPerformed(ActionEvent evt)
	{
		// refreshes only if gamepanel is done initializing
		if(GAMEPANEL != null) {GAMEPANEL.refresh(); GAMEPANEL.repaint();}
	}

	/**
	 * Curtains up! Center stage!
	 * Instantiates the game frame object
	 * which also instantiates the panel object
	 */
    public static void main(String...arguments) {new Game();}
}

final class GamePanel extends JPanel implements KeyListener
{
	private static final long serialVersionUID = 1L;

	private Player player  = new Player(275,680); // the playerObject
	private boolean[] keys = new boolean[KeyEvent.KEY_LAST+1]; // boolean array of the keys
	private LinkedList<PlayerBullet> playerBullets = new LinkedList<PlayerBullet>();

	// Images
	private final Image HEALTHBAR = new ImageIcon("Images/healthBar.png").getImage();

	/**
	 * CONSTRUCTOR
	 */
	public GamePanel()
	{
		setSize(600,800);
		addKeyListener(this);
	}

	/**
	 * @param key : the key in value on the keyboard
	 * @param state : the bool state explained below
	 * sets the state for a key on the keyboard
	 * true  = pressed
	 * false = released
	 */
    public void setKey(int key, boolean state) {keys[key] = state;}

	/**
	 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯UNUSED⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
	 * invoked when a key is typed
	 * @param keyevt : a KeyEvent that occured
	 */
    public void keyTyped(KeyEvent keyevt) {}

	/**
	 * invoked when a key is pressed
	 * @param keyevt : a KeyEvent that occured
	 */
    public void keyPressed(KeyEvent keyevt) {setKey(keyevt.getKeyCode(),true);}

	/**
	 * invoked when a key is released
	 * @param keyevt : a KeyEvent that occured
	 */
    public void keyReleased(KeyEvent keyevt) {setKey(keyevt.getKeyCode(),false);}

	/**
	 * invoked whenever an action
	 * is performed to refresh the
	 * actions and the game state.
	 */
	public void refresh()
	{
		Player.refreshBullet(); // refreshes the bullet speed and delay

		requestFocusInWindow();

		// X movement
		if (keys[KeyEvent.VK_RIGHT] && Player.getX()<545) {Player.moveX(true);}
		if (keys[KeyEvent.VK_LEFT] && Player.getX()>5) 	 {Player.moveX(false);}

		// Y movement
		if (keys[KeyEvent.VK_DOWN] && Player.getY()<680) {Player.moveY(false);}
		if (keys[KeyEvent.VK_UP] && Player.getY()>5) {Player.moveY(true);}

		// firing a bullet
		if (keys[KeyEvent.VK_SPACE] && Player.getBulletDelayIterator() == 0)
			{playerBullets.add(new PlayerBullet(Player.getX()-50, Player.getY()-80));}

		// sheild powerup
		if (keys[KeyEvent.VK_Z]) {}

		// penetration powerup
		if (keys[KeyEvent.VK_X]) {}

		// boost powerup
		// shift+arrows



		// moving bullet after firing
		for (int i = 0; i<playerBullets.size(); i++)
		{
			PlayerBullet bull = playerBullets.get(i);
			if (bull != null) {bull.moveY();}
		}

		// removing bullets that are out of screen
		if (playerBullets.size() != 0)
			{if ((playerBullets.getFirst()).getY() < -30) {playerBullets.removeFirst();}}
	}

	/**
	 * draws to graphic component
	 * @param g : the graphics component
	 */
	@Override
    public void paintComponent(Graphics g)
	{
		// backGround
		g.setColor(Color.black);

		// healthbars
		g.fillRect(0,0,600,800);
		for (int i = 0; i<Player.getHealth(); i++)
			{g.drawImage(HEALTHBAR, 10+(14*i),735,this);}

		// The player object
		Player.draw(g);

		// The player's bullets
		// if(playerBullet!=null) {playerBullet.draw(g);}
		for (int i = 0; i<playerBullets.size(); i++) {(playerBullets.get(i)).draw(g);}
    }
}
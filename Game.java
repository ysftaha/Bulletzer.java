/**
 * Game.java
 * @author Yusuf A. Taha
 * Where the magic happens
 *        (._.)
 */

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
// TODO add alien and player
//		bullet objects
// TODO add JSON gamefile
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
	private PlayerBullet playerBullet = null;

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
		requestFocusInWindow();

		// X movement
		if(keys[KeyEvent.VK_RIGHT] && player.getX()<545) {player.moveX(true);}
		if(keys[KeyEvent.VK_LEFT] && player.getX()>5) 	 {player.moveX(false);}

		// Y movement
		if(keys[KeyEvent.VK_DOWN] && player.getY()<680) {player.moveY(false);}
		if(keys[KeyEvent.VK_UP] &&player.getY()>5) {player.moveY(true);}

		// firing a bullet
		if(keys[KeyEvent.VK_SPACE])
		{
			playerBullet = new PlayerBullet(player.getX()-50, player.getY()-80);
		}

		// sheild powerup
		if(keys[KeyEvent.VK_Z]) {}

		// penetration powerup
		if(keys[KeyEvent.VK_X]) {}

		// boost powerup
		// shift+arrows
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
		g.fillRect(0,0,600,800);

		// The player object
		player.draw(g);

		// The player's bullets
		if(playerBullet!=null) {playerBullet.draw(g);}
    }
}
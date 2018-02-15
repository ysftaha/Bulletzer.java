/**
 * Game.java
 * @author Yusuf A. Taha
 * Where the magic happens
 *         🎩
 */

import java.util.LinkedList;
import java.util.ArrayList;

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
	public void actionPerformed(final ActionEvent evt)
	{
		// refreshes only if gamepanel is done initializing
		if(GAMEPANEL != null) {GAMEPANEL.refresh(); GAMEPANEL.repaint();}
	}

	/**
	 * Curtains up! Center stage!
	 * Instantiates the game frame object
	 * which also instantiates the panel object
	 */
    public static void main(final String...arguments) {new Game();}
}

final class GamePanel extends JPanel implements KeyListener
{
	// dont worry about this for now
	private static final long serialVersionUID = 1L;

	// boolean array of the keys
	private static boolean[] keys = new boolean[KeyEvent.KEY_LAST+1];
	//arraylist of player bullets on screen
	private static LinkedList<PlayerBullet> playerBullets = new LinkedList<PlayerBullet>();
	// arraylist of tokens on screen
	private static ArrayList<Token> tokens = new ArrayList<Token>();
	// array of timers for tokens (sheild and frenzy)
	private static int[]  tokensTmr = {1000,1000};
	private static boolean[]  tokensTmrSwitch = {false,false};

	// probability of a token spawning
	private int tokenProbability;

	// Images
	private static final Image HEALTHBAR = new ImageIcon("Images/healthBar.png").getImage();
	private static final Image PLAYERB = new ImageIcon("Images/playerBullet.png").getImage();
	private static final Image SHEILDED = new ImageIcon("Images/sheildHUD.png").getImage();


	static {tokens.add(new Token(2, 50, 50));}

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
    public void setKey(final int key, final boolean state) {keys[key] = state;}

	/**
	 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯UNUSED⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
	 * invoked when a key is typed
	 * @param keyevt : a KeyEvent that occured
	 */
    public void keyTyped(final KeyEvent keyevt) {}

	/**
	 * invoked when a key is pressed
	 * @param keyevt : a KeyEvent that occured
	 */
    public void keyPressed(final KeyEvent keyevt) {setKey(keyevt.getKeyCode(),true);}

	/**
	 * invoked when a key is released
	 * @param keyevt : a KeyEvent that occured
	 */
    public void keyReleased(final KeyEvent keyevt) {setKey(keyevt.getKeyCode(),false);}

	/**
	 * invoked whenever an action
	 * is performed to refresh the
	 * actions and the game state.
	 */
	public void refresh()
	{
		tokenProbability = (int)(Math.random() * 200 +1);
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
			{playerBullets.add(new PlayerBullet(PLAYERB, Player.getX()-50, Player.getY()-80));}

		// sheild powerup
		if (keys[KeyEvent.VK_Z]) {}

		// penetration powerup
		if (keys[KeyEvent.VK_X]) {}



		// MOVING THE BULLETS
		for (int i = 0; i<playerBullets.size(); i++)
		{
			PlayerBullet bull = playerBullets.get(i);
			if (bull != null) {bull.moveY(true);}
		}

		// REMOVING PLAYERBULLETS THAT ARE OUT OF THE SCREEN
		if (playerBullets.size() != 0)
			{if ((playerBullets.getFirst()).getY() < -30) {playerBullets.removeFirst();}}

		// TOKEN TIMER LOGIC
			// Sheild token
		if (tokensTmrSwitch[0] == true && tokensTmr[0] > 0)
			{tokensTmr[0]--;} // decrementing the timer
		else if (tokensTmr[0] == 0)
		{
			tokensTmr[0] = 1000; // reseting the timer to 10 secs
			Player.setSheilded(false); // turning the sheild off
		}
			// Frenzy token
		if (tokensTmrSwitch[1] == true && tokensTmr[1] > 0)
			{tokensTmr[1]--;} // decrementing the timer
		else if (tokensTmr[1] == 0)
		{
			tokensTmr[1] = 1000; // reseting the timer to 10 secs
			Player.setBulletDelayInterval(12); // turning the interval back to OG
		}

	}

	/**
	 * draws to graphic component
	 * @param g the graphics component
	 */
	@Override
    public void paintComponent(final Graphics g)
	{
		// backGround
		g.setColor(Color.black);

		// healthbars
		g.fillRect(0,0,600,800);
		for (int i = 0; i<Player.getHealth(); i++)
			{g.drawImage(HEALTHBAR, 10+(14*i),735,this);}

		// sheilded mask over healthbars
		if (Player.isSheilded())
			{g.drawImage(SHEILDED, -230,660,this);}

		// The player object
		Player.draw(g);

		/*
		// TOKENS
		// 	spawnng
		if (tokenProbability == 1) {tokens.add(new Token((int)(Math.random()*3 + 1), (int)(Math.random()*560 + 10), 5));}
		//	drawing
		for (Token token : tokens)
		{
			token.draw(g);
			// token.moveY(false);
			if (token.collidePlayer())
			{
				token.reward();
				tokens.remove(token);
			}
		}
		*/

		// handels bullet rewards and removes the token off the screen
		for (Token tkn : tokens)
		{
			tkn.draw(g);
			if (tkn.collidePlayer())
			{
				switch (tkn.getType())
				{
					case 1:
						tkn.rewardHealth();
						break;

					case 2:
						tkn.rewardSheild();
						tokensTmrSwitch[0] = true; // sets the sheilded
												   // timer countdown to start
						break;

					case 3:
						tkn.rewardFrenzy();
						tokensTmrSwitch[1] = true;// sets the Frenzy
												  // timer countdown to start
						break;

					default:
						System.out.println("Token type Error!");
						break;
				}

				tokens.remove(tkn);
			}
		}

		// The player's bullets
		for (int i = 0; i<playerBullets.size(); i++) {(playerBullets.get(i)).draw(g);}
    }
}
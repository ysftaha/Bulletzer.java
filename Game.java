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

// @SuppressWarnings("all")
public final class Game extends JFrame implements ActionListener
{
	// dont worry about this for now (editor yelling at me for it)
	private static final long serialVersionUID = 1L;

	// listens to actions every 10 ms
	private final Timer CLOCK = new Timer(10, this);
	// a gamepanel that controls the player component
	private final GamePanel GAMEPANEL;

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
		if(GAMEPANEL != null) {GAMEPANEL.update(); GAMEPANEL.repaint();}
	}

	/**
	 * Curtains up! Center stage!
	 * Instantiates the game frame object
	 * which also instantiates the panel object
	 */
    public static void main(final String...arguments) {new Game();}
}

// @SuppressWarnings("all")
final class GamePanel extends JPanel implements KeyListener
{
	// dont worry about this for now (editor yelling at me for it)
	private static final long serialVersionUID = 1L;

	// gameState possible values
	public enum State
		{MAINMENU, INSTRUCTIONS, ABOUT, INGAME, PAUSE, GAMEOVER;}
	// gameState signaling what part of the GUI we are in
	private static State gameState;
	// boolean array of the keys
	private static boolean[] keys = new boolean[KeyEvent.KEY_LAST+1];
	//arraylist of player bullets on screen
	private static LinkedList<Bullet> playerBullets = new LinkedList<Bullet>();
	// arraylist of tokens on screen
	private static ArrayList<Token> tokens = new ArrayList<Token>();
	// array of timers for tokens (sheild and frenzy)
	private static int[]  tokensTmr = {1000,1000};
	// boolean list for the previous list to turn in countdown (decrementing)
	private static boolean[]  tokensTmrSwitch = {false,false};
	// probability of a token spawning
	private static int tokenProbability;
	// enemy spawning probability
	private static int enemyProbability;
	// Enemy objects list
	private static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	// Enemy Bullets list
	private static ArrayList<Bullet> enemyBullets = new ArrayList<Bullet>();
	// goes from 0 to 2 where 0 = play, 1 = instructions, 2 = about
	private static int menuState = 0;
	// which enemy doe?
	private static int whichEnm;
	// Objects that will be get initialized in iteration
	private static Token tkn; Enemy enm; Bullet enmB; Bullet enmBull;

	// Images
		// INGAME Imgaes
	private static final Image HEALTHBAR = new ImageIcon("Images/healthBar.png").getImage();
	private static final Image DARKENERGY = new ImageIcon("Images/darkEnergyBar.png").getImage();
	private static final Image SHIELDEM = new ImageIcon("Images/SheildEm.png").getImage();
	private static final Image FRENZYEM = new ImageIcon("Images/FrenzyEm.png").getImage();
		// MAINMENU Images
	private static final Image LOGO = new ImageIcon("Images/BulletzerLogo.png").getImage();
	private static final Image MENU1 = new ImageIcon("Images/menu1.png").getImage();
	private static final Image MENU2 = new ImageIcon("Images/menu2.png").getImage();
	private static final Image MENU3 = new ImageIcon("Images/menu3.png").getImage();
	private static final Image LICENSE = new ImageIcon("Images/license.png").getImage();
	private static final Sound sound   = new Sound();


	// static
	// {
	// 	enemies.add(new Enemy(100, 40, 1, 1));
	// 	enemies.add(new Enemy(200, 40, 1, 2));
	// 	enemies.add(new Enemy(300, 40, 1, 3));
	// }
	/**
	 * CONSTRUCTOR
	 */
	public GamePanel()
	{
		setSize(600,800);
		addKeyListener(this);
		gameState = State.MAINMENU;
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
	 * invoked when a key is released
	 * @param keyevt : a KeyEvent that occured
	 */
    public void keyReleased(final KeyEvent keyevt) {setKey(keyevt.getKeyCode(),false);}

	/**
	 * invoked when a key is pressed
	 * @param keyevt : a KeyEvent that occured
	 * @see Sound.java
	 */
    public void keyPressed(final KeyEvent keyevt)
	{
		setKey(keyevt.getKeyCode(),true);
		int keyCode = keyevt.getKeyCode();
		if (gameState == State.MAINMENU) // if we are in main menu
		{
			if (keyCode == KeyEvent.VK_UP)
			{
				switch(menuState)
				{
					case 0:
						menuState = 2;
						break;
					case 1:
						menuState = 0;
						break;
					case 2:
						menuState = 1;
						break;
				}
				sound.playSound("button");
			}
			if (keyCode == KeyEvent.VK_DOWN)
			{
				switch(menuState)
				{
					case 0:
						menuState = 1;
						break;
					case 1:
						menuState = 2;
						break;
					case 2:
						menuState = 0;
						break;
				}
				sound.playSound("button");
			}

			if (keyCode == KeyEvent.VK_ENTER)
			{
				switch(menuState)
				{
					case 0:
						gameState = State.INGAME;
						break;
					case 1:
						gameState = State.INSTRUCTIONS;
						break;
					case 2:
						gameState = State.ABOUT;
						break;
				}
			}
		}
		else if (gameState == State.INGAME) // Enter Pause menue
			{if (keyCode == KeyEvent.VK_ESCAPE) {gameState = State.PAUSE;}}

		else if (gameState == State.PAUSE) // Exit Pause menue
			{if (keyCode == KeyEvent.VK_ESCAPE) {gameState = State.INGAME;}}
	}

	/**
	 * invoked whenever an action
	 * is performed to update the
	 * actions and the game state.
	 */
	public void update()
	{
		switch (gameState)
		{
			case MAINMENU:
				mainMenu();
				break;
			case INGAME:
			    inGame();
				break;
			case PAUSE:
				pause();
				break;
			case GAMEOVER:
				gameOver();
				break;
			default:
				System.out.println("Game state Error.");
				break;
		}
	}

	/**
	 * refreshes the frames
	 * to draw the  main menue
	 */
	public void mainMenu() {requestFocusInWindow();}

	/**
	 * draws the diffrent images
	 * in the main menu
	 * @param g the graphics component
	 * the function is drawing to
	 */
	public void paintMainMenu(final Graphics g)
	{
		g.drawImage(LOGO, 100,200,this);
		g.drawImage(LICENSE, 145,720,this);
		switch (menuState)
		{
			case 0:
				g.drawImage(MENU1, 50,300,this);
				break;
			case 1:
				g.drawImage(MENU2, 50,311,this);
				break;
			case 2:
				g.drawImage(MENU3, 50,322,this);
				break;
		}
	}

	/**
	 * refreshes the frames
	 * to simulate playing
	 * the game
	 */
	public void inGame()
	{
		requestFocusInWindow();
		tokenProbability = (int)(Math.random()*180) + 1; // probability of a token spawning
		enemyProbability = (int)(Math.random()*80) + 1; // probability of a enemy spawning

		// Spawning Enemies
		if (enemyProbability == 1)
			{enemies.add(new Enemy((int)(Math.random()*560) +10, 10, 8, (int)(Math.random()*3) + 1));}

		// 	Spawning Tokens
		// handeling logic other than spawning in paintInGame
		if (tokenProbability == 1)
		{
			tokens.add(new Token((int)(Math.random()*3 + 1), (int) (Math.random()*560 + 10), 5));
		}

		if (Player.getHealth() < 1) {gameState = State.GAMEOVER;}
		Player.refreshBullet(); // refreshes the bullet speed and delay

		// X movement
		if (keys[KeyEvent.VK_RIGHT] && Player.getX()<545) {Player.moveX(true);}
		if (keys[KeyEvent.VK_LEFT] && Player.getX()>5) 	 {Player.moveX(false);}


		// Y movement
		if (keys[KeyEvent.VK_DOWN] && Player.getY()<680) {Player.moveY(false);}
		if (keys[KeyEvent.VK_UP] && Player.getY()>5) {Player.moveY(true);}

		// firing a bullet
		if (keys[KeyEvent.VK_SPACE] && Player.getBulletDelayIterator() == 0)
		{
			if (Player.getdarkEnergy() > 0) // making sure we aren't going to melt the gun
			{
				if(Player.getBulletDelayInterval() == 12) // making sure we are not in frenzy mode
				{
					// adds the Playerbullet to the Playerbullets linkedlist to be drawn later
					playerBullets.add(new Bullet(1, Player.getX()-50, Player.getY()-80, 90, 0, 9));
					sound.playSound("playerShoot");
					Player.setdarkEnergy(Player.getdarkEnergy()-1); // expends darkenergy to shoot a bullet
				}
				// if in frenzy mode do not expend dark energy
				else
				{playerBullets.add(new Bullet(1, Player.getX()-50, Player.getY()-80, 90, 0, 9));
				 sound.playSound("playerShoot");}
			}
		}
		// if we are not pressing space we regenerate dark energy by 0.01 per refresh
		if (!keys[KeyEvent.VK_SPACE] && Player.getdarkEnergy() < 50)
			{Player.setdarkEnergy(Player.getdarkEnergy()+0.1);}

		// MOVING THE BULLETS
		for (int i = playerBullets.size()-1; i>-1; i--)
		{
			Bullet bull = playerBullets.get(i);
			if (bull != null) {bull.setY((int)(bull.getY()-bull.getspeed()));}
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
		// ENEMY HANDELING AND LOGIC
			// moving enemies
		for (int i = enemies.size()-1; i > -1; i--)
		{
			enm = enemies.get(i);
			switch (enm.getType())
			{
				case 1:
					if (!enm.isHitEdge())
					{
						enm.setY(enm.getY() + enm.getSpeed());
						enm.setX(enm.getX() + enm.getSpeed());
						if (enm.getX() > 540) {enm.setHitEdge(true);}
					}
					else if (enm.isHitEdge())
					{
						enm.setY(enm.getY() + enm.getSpeed());
						enm.setX(enm.getX() - enm.getSpeed());
						if (enm.getX() < 0) {enm.setHitEdge(false);}
					}
					break;
				case 2:
					enm.setY(enm.getY()+1);
					break;
				case 3:
					if (enm.getSpaceTime() < (int)Math.random()*300 +200)
					{
						enm.setY(enm.getY()+1);
						enm.setSpaceTime(enm.getSpaceTime()+1);
					}
					else
					{
						enm.setTimeIsHoax(enm.getTimeIsHoax()+1);
						if (enm.getTimeIsHoax() == 120)
						{
							enm.setSpaceTime(0);
							enm.setTimeIsHoax(0);
						}
					}
					break;
			}
		}
			// Checking For collisions
		for (int i = playerBullets.size() - 1; i>-1; i--)
		{
			Bullet bull = playerBullets.get(i);
			for (int j = enemies.size() -1 ; j > -1; j--)
			{
				enm = enemies.get(j);
				if (bull != null && enm != null)
				{
					if (bull.collideWith(enm))
					{
						playerBullets.remove(bull);
						if (enm.getEnemyHp()>0) {enm.setEnemyHp(enm.getEnemyHp()-1);}
						else {enemies.remove(enm);}
					}
				}
			}

			for (int k = enemyBullets.size() -1 ; k > -1; k--)
			{
				enmBull = enemyBullets.get(k);
				if (bull.collideWith(enmBull))
				{
					enemyBullets.remove(enmBull);
					playerBullets.remove(bull);
				}
			}
		}

		// ENEMY BULLETS
			// Spawning them
		for (Enemy enm : enemies)
			{for (Bullet bull : enm.spawnBullets()) {enemyBullets.add(bull);}}
			// Moving them
		for (Bullet bull: enemyBullets)
		{
			switch(bull.getMvType())
			{
				case 0:
					bull.setY(bull.getY()-bull.getspeed());
					break;
				case 1:
					bull.setY(bull.getY()-bull.getspeed());
					bull.setX(bull.getX()+bull.getspeed());
					break;
				case 2:
					bull.setX(bull.getX()+bull.getspeed());
					break;
				case 3:
					bull.setY(bull.getY()+bull.getspeed());
					bull.setX(bull.getX()+bull.getspeed());
					break;
				case 4:
					bull.setY(bull.getY()+bull.getspeed());
					break;
				case 5:
					bull.setY(bull.getY()+bull.getspeed());
					bull.setX(bull.getX()-bull.getspeed());
					break;
				case 6:
					bull.setX(bull.getX()-bull.getspeed());
					break;
				case 7:
					bull.setY(bull.getY()-bull.getspeed());
					bull.setX(bull.getX()-bull.getspeed());
					break;
			}

		}

		// Removing them
		for (int i = enemyBullets.size()-1; i>-1; i--)
		{
			enmB = enemyBullets.get(i);
			if (enmB.getX() > 800 || enmB.getX()< 0 || enmB.getY()< 0 || enmB.getY() > 800)
				{enemyBullets.remove(enmB);}
		}


			// Collision with Player
		for (int i = enemyBullets.size()-1; i>-1; i--)
		{
			enmB = enemyBullets.get(i);
			if (enmB.collideWithPlayer())
			{
				if(!Player.isSheilded()) {Player.setHealth(Player.getHealth()-1);}
				enemyBullets.remove(i);
			}
		}
	}

	/**
	 * Painting ingame objects
	 * and asthetics on the screen
	 * @param g the graphics component
	 * to paint to
	 */
	public void paintInGame(final Graphics g)
	{
		// TOKENS
			// Token DRAWING AND REWARDS
		// handels bullet rewards and removes the token off the screen
		// decrementing to avoid ConcurrentModificationException
		for (int i = tokens.size()-1; i>-1; i--)
		{
			tkn = tokens.get(i);
			tkn.draw(g);
			tkn.moveY();
			if (tkn.collidePlayer())
			{
				switch (tkn.getType())
				{
					case 1:
						tkn.rewardHealth();
						break;
					case 2:
						tkn.rewardSheild();
						sound.playSound("sheild");
						tokensTmrSwitch[0] = true; // sets the sheilded
												   // timer countdown to start
						break;
					case 3:
						tkn.rewardFrenzy();
						sound.playSound("frenzy");
						tokensTmrSwitch[1] = true; // sets the Frenzy
												   // timer countdown to start
						break;
					default:
						System.out.println("Token type Error.");
						break;
				}

				tokens.remove(tkn);
			}
		}
		// ENEMIES
		for (Enemy enm: enemies) {enm.draw(g);}

		// healthbars
		for (int i = 0; i<Player.getHealth(); i++)
			{g.drawImage(HEALTHBAR, 10+(14*i), 735, this);}

		// dark Energy bars
		for (int i = 0; i<Player.getdarkEnergy()/5; i++)
			{g.drawImage(DARKENERGY, 580 - (14*i), 735, this);}

		// empowered sprite around Player sprite (Sheild active)
		if (Player.isSheilded())
			{g.drawImage(SHIELDEM, Player.getX()-12, Player.getY()-7, this);}

		// empowered sprite above Player sprite (Frenzy active)
		if (Player.getBulletDelayInterval() == 6)
			{g.drawImage(FRENZYEM, Player.getX()+6, Player.getY()-22, this);}

		// The player object
		Player.draw(g);

		// The player's bullets
		for (int i = playerBullets.size()-1; i>-1; i--) {(playerBullets.get(i)).draw(g);}

		// enemy bullets
		for (int i = enemyBullets.size()-1; i>-1; i--) {(enemyBullets.get(i)).draw(g);}
	}

	/**
	 * refreshes the frames
	 * to simulate a pause
	 * menu
	 */
	public void pause() {requestFocusInWindow();}
	public void paintPause(final Graphics g) {}

	/**
	 * refreshes the frames
	 * to simulate a talent
	 * crisis
	 */
	public void gameOver() {requestFocusInWindow();}
	public void paintGameOver(final Graphics g) {}

	/**
	 * draws to graphic component
	 * @param g the graphics component
	 * depending on waht gamestate we
	 * are in
	 * @see #paintMainMenu(Graphics)
	 * @see #paintInGame(Graphics)
	 * @see #paintPause(Graphics)
	 * @see #paintGameOver(Graphics)
	 */

	@Override
    public void paintComponent(final Graphics g)
	{
		// backGround
		g.setColor(Color.black);
		g.fillRect(0,0,600,800);

		switch (gameState)
		{
			case MAINMENU:
				paintMainMenu(g);
				break;
			case INGAME:
				paintInGame(g);
				break;
			case PAUSE:
				paintPause(g);
				break;
			case GAMEOVER:
				paintGameOver(g);
				break;
			default:
				System.out.println("Paint Function Error.");
				break;
		}
	}
}
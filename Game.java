/**
 * Game.java
 * @author Yusuf A. Taha
 * @see GamePanel.java
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

/**
 * The game frame class
 */
// TODO Move keylistener to game panel
// ？actionPerformed checking for game == null?
public final class Game extends JFrame implements ActionListener, KeyListener
{
	private final Timer clock; // swing timer
	private final GamePanel gamePanel; // a gamepanel that controls the player component

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

		clock = new Timer(10, this); // listens to actions every 10 ms
		clock.start();			     // starts the timer

		gamePanel = new GamePanel(); // creates the player's game panel
		add(gamePanel); // adds the game panel to the Game JFrame

		addKeyListener(this);
    }

	/**
	 * invoked whenever an action occurs
	 * @param evt : an actionevent that occurred
	 */
	public void actionPerformed(ActionEvent evt)
	{
		// refreshes only if there is an actual gamepanel
		if(gamePanel != null) {gamePanel.refresh(); gamePanel.repaint();}
	}

	/**
	 * Curtains up! Center stage!
	 * Instantiates the game frame object
	 * which also instantiates the panel object
	 */
    public static void main(String...arguments) {Game frame = new Game();}

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
    public void keyPressed(KeyEvent keyevt) {gamePanel.setKey(keyevt.getKeyCode(),true);}

	/**
	 * invoked when a key is released
	 * @param keyevt : a KeyEvent that occured
	 */
    public void keyReleased(KeyEvent keyevt) {gamePanel.setKey(keyevt.getKeyCode(),false);}
}

class GamePanel extends JPanel
{
	private int playerX,playerY;
	private boolean[] keys = new boolean[KeyEvent.KEY_LAST+1]; // boolean array of the keys

	// Image dependencies
	private Image playerImg;
	private Image playerBulletImg;
	private Image enemyBulletImg;

	/**
	 * CONSTRUCTOR
	 */
	public GamePanel()
	{
		playerImg  	  = new ImageIcon("playerShip.png").getImage();
		playerBulletImg = new ImageIcon("playerBullet.png").getImage();
		enemyBulletImg  = new ImageIcon("enemyBullet.png").getImage();

	    playerX = 280;
        playerY = 735;
		setSize(600,800);
	}

	/**
	 * @param key : the key in value on the keyboard
	 * @param state : the bool state explained below
	 * sets the state for a key on the keyboard
	 * true  = pressed
	 * false = released
	 */
    public void setKey(int key, boolean state) {keys[key] = state;}

	public void refresh()
	{
		// X movement
		if(keys[KeyEvent.VK_RIGHT] && playerX<555 ) {playerX += 5;}
		if(keys[KeyEvent.VK_LEFT] && playerX>5) {playerX -= 5;}

		// Y movement
		if(keys[KeyEvent.VK_UP] && playerY>5) {playerY -= 5;}
		if(keys[KeyEvent.VK_DOWN] && playerY<735) {playerY += 5;}

		// fire
		if(keys[KeyEvent.VK_SPACE]) {}

		// sheild powerup

		// timewarp powerup

		// boost powerup

	}

	@Override
    public void paintComponent(Graphics g)
	{
		// backGround
		g.setColor(Color.black);
		g.fillRect(0,0,600,800);

		// playerShip
		g.setColor(Color.black);
		g.fillRect(playerX,playerY,40,40);

		// playerImg.paintIcon(this, g, playerX,playerY);
		g.drawImage(playerImg, playerX, playerY, this);
		g.drawImage(playerBulletImg, 90, 90, this);
		g.drawImage(enemyBulletImg, 200, 90, this);
    }
}

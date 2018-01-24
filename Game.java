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
	private Image playerImg		  = new ImageIcon("playerShip.png").getImage();
	private Image playerBulletImg = new ImageIcon("playerBullet.png").getImage();

	private Image enemyBulletImg  = new ImageIcon("enemyBullet.png").getImage();
	private Image enemy1Img  = new ImageIcon("enemy1.png").getImage();
	private Image enemy2Img  = new ImageIcon("enemy2.png").getImage();

	private Image healthBarImg  = new ImageIcon("healthBar.png").getImage();

	private Image heartTKImg  = new ImageIcon("heartTK.png").getImage();

	/**
	 * CONSTRUCTOR
	 */
	public GamePanel()
	{
	    playerX = 275;
        playerY = 680;
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
		if(keys[KeyEvent.VK_RIGHT] && playerX<545 ) {playerX += 5;} // go right
		if(keys[KeyEvent.VK_LEFT] && playerX>5) {playerX -= 5;} // go left

		// Y movement
		if(keys[KeyEvent.VK_DOWN] && playerY<680) {playerY += 5;} // go down
		if(keys[KeyEvent.VK_UP] && playerY>5) {playerY -= 5;} // go up

		// fire
		if(keys[KeyEvent.VK_SPACE]) {}

		// sheild powerup

		// timewarp powerup

		// boost powerup

	}



	/**
	 * draws onto the graphic
	 */
	@Override
    public void paintComponent(Graphics g)
	{
		// backGround
		g.setColor(Color.black);
		g.fillRect(0,0,600,800);

		// playerShip rect
		// g.setColor(Color.black);
		// g.fillRect(playerX,playerY,40,40);

		// player
		// playerImage
		g.drawImage(playerBulletImg, 90, 90, this);
		// enemies
		g.drawImage(enemy1Img, 300, 90, this);
		g.drawImage(enemy2Img, 300, 190, this);
		// enemy bullet
		g.drawImage(enemyBulletImg, 200, 90, this);
		// health bars
		g.drawImage(healthBarImg, 10, 730, this);
		g.drawImage(heartTKImg, 100, 30, this);

		g.drawImage(playerImg, playerX, playerY, this);
    }
}

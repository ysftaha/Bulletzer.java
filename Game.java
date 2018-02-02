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

/**
 * The game frame class
 */
// TODO Move keylistener to game panel ?
public final class Game extends JFrame implements ActionListener/*,  KeyListener */
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

    }

	/**
	 * invoked whenever an action occurs
	 * @param evt : an actionevent that occurred
	 * @see gamepanel#refresh()
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
}

final class GamePanel extends JPanel implements KeyListener
{
	private Player player;
	private int testX,testY;
	private boolean[] keys = new boolean[KeyEvent.KEY_LAST+1]; // boolean array of the keys


	// Image dependencies
	private Image playerImg		  = new ImageIcon("playerShip.png").getImage();
	private Image playerBulletImg = new ImageIcon("playerBullet.png").getImage();
	private Image enemyBulletImg  = new ImageIcon("enemyBullet.png").getImage();
	private Image enemy1Img       = new ImageIcon("enemy1.png").getImage();
	private Image enemy2Img       = new ImageIcon("enemy2.png").getImage();
	private Image enemy3Img       = new ImageIcon("enemy3.png").getImage();
	private Image healthBarImg    = new ImageIcon("healthBar.png").getImage();
	private Image heartTKImg      = new ImageIcon("heartTK.png").getImage();

	/**
	 * CONSTRUCTOR
	 */
	public GamePanel()
	{
		player = new Player(275,680,this);
	    testX = 275;
        testY = 680;
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
		if(keys[KeyEvent.VK_RIGHT] && testX<545 ) {testX += 5;} // right
		if(keys[KeyEvent.VK_LEFT] && testX>5) {testX -= 5;} // left

		// Y movement
		if(keys[KeyEvent.VK_DOWN] && testY<680) {testY += 5;} // down
		if(keys[KeyEvent.VK_UP] && testY>5) {testY -= 5;} // up

		// fire
		if(keys[KeyEvent.VK_SPACE]) {}

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

		// playerImage
		g.drawImage(playerBulletImg, 90, 90, this);
		// enemies
		g.drawImage(enemy1Img, 300, 90, this);
		g.drawImage(enemy2Img, 300, 190, this);
		g.drawImage(enemy3Img, 300, 290, this);
		// enemy bullet
		g.drawImage(enemyBulletImg, 200, 90, this);
		// health bars
		g.drawImage(healthBarImg, 10, 730, this);
		g.drawImage(heartTKImg, 100, 30, this);

		g.drawImage(playerImg, testX, testY, this);
    }
}
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
 *
 */
public final class Game extends JFrame implements ActionListener, KeyListener
{
	private final Timer tmr; // swing timer
	private final GamePanel game; // a gamepanel that controls the player component

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

	/**
	 * invoked whenever an action occurs
	 * @param evt an actionevent
	 */
	public void actionPerformed(ActionEvent evt)
	{
		if(game != null) {game.refresh(); game.repaint();}
	}

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


    public static void main(String[] arguments) {Game frame = new Game();}
}

class GamePanel extends JPanel
{
	private int playerX,playerY;
	private boolean[] keys; // boolean array of the keys
	private Image playerImg;
	private Image playerBulletImg;
	private Image enemyBulletImg;

	public GamePanel()
	{
		keys = new boolean[KeyEvent.KEY_LAST+1];
		// backGround = new ImageIcon("OuterSpace.jpg").getImage();
		playerImg = new ImageIcon("playerShip.png").getImage();
		playerBulletImg = new ImageIcon("playerBullet.png").getImage();
		enemyBulletImg = new ImageIcon("enemyBullet.png").getImage();

	    playerX = 280;
        playerY = 735;
		setSize(600,800);
	}

    public void setKey(int k, boolean v) {keys[k] = v;}

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

		// sheild

		// increased movement

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

/**
 * GamePanel.java
 * @author Yusuf A. Taha
 * @see Game.java
 */
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class GamePanel extends JPanel
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
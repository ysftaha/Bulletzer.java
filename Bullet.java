/**
 * Bullet.java
 * @author Yusuf Taha
 * @see PlayerBullet.java
 * @see EnemyBullet.java
 * parent class for bullets
 * in the game 🔫
 */

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Bullet
{
	private double x, y;      // the coordinates of the bullet
	private Image img;        // the bullet sprite
	private int type;
	private double speed;
	private int mvType;

	/**
	 * CONSTRUCTOR
	 * @param type 1 is player
	 * bullet. 2 is enemy bullet
	 * @param x the horizontal
	 * coordinate of the bullet
	 * @param y the vertical
	 * coordinate of the bullet
	 * @param angle the angle for
	 * which to calculate the bullet's
	 * movement
	 */
	public Bullet(final int type, final double x, final double y,
			final double angle, final int mvType, final double speed)
	{
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.mvType = mvType;

		switch(type)
		{
			case 1:
				img = new ImageIcon("Images/playerBullet.png").getImage();
				break;
			case 2:
				img = new ImageIcon("Images/enemyBullet.png").getImage();
				break;
		}
	}

	///////////////////////
	// SETTERS & GETTERS //
	///////////////////////

	/**
	 * @return the Bullet's horizontal component (X)
	 */
	public double getX() {return x;}

	/**
	 * @param x the y to set
	 */
	public void setX(final double x) {this.x = x;}


	/**
	 * @return the Bullet's vertical component (Y)
	 */
	public double getY() {return y;}

	/**
	 * @param y the y to set
	 */
	public void setY(final double y) {this.y = y;}

	/**
	 * @return the speed
	 */
	public double getspeed() {return speed;}

	/**
	 * @param speed the speed to set
	 */
	public void setspeed(double speed) {this.speed = speed;}

	/**
	 * @return the type
	 */
	public int getType() {return type;}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {this.type = type;}


	/**
	 * @return the mvType
	 */
	public int getMvType() {return mvType;}

	/**
	 * @param mvType the mvType to set
	 */
	public void setMvType(int mvType) {this.mvType = mvType;}

	//////////////////////
	//  OTHER FUNCTIONS //
	//////////////////////

	/**
	 * Draws the bullet object
	 * @param g the graphics
	 * component we drawing to
	 */
	public void draw(final Graphics g)
	{g.drawImage(img, (int)getX(), (int)getY(), null);}

	/**
	 * Checks if the bullet collided
	 * with an enemy
	 * @param enemy : an enemy object
	 * that we are checking collision
	 * for
	 * @return true or false. true
	 * meaning the bullet is colliding
	 * with the enemy
	 */
	public boolean collideWith(Enemy enemy)
	{
		final int enemyX = enemy.getX(), enemyY = enemy.getY();
		return (enemyX-80 < x && enemyX + 10 > x && enemyY < y && enemyY + 10 > y);
	}

	public boolean collideWith(Bullet bullet)
	{
		final int enmBullX = (int)bullet.getX(), enmBullY = (int)bullet.getY();
		return (enmBullX-80 < x && enmBullX + 10 > x && enmBullY < y && enmBullY + 10 > y);
	}

	/**
	 * an overloading function of
	 * #collideWith(Enemy enemy)
	 * @param Player the player
	 * object the bullet is colliding
	 * with
	 * @return boolean indicating
	 * collision
	 */
	public boolean collideWithPlayer()
	{
		final int playerX = Player.getX(), playerY = Player.getY();
		return (playerX - 10 < x && playerX + 42 > x && playerY < y && playerY + 32 > y);
	}
}

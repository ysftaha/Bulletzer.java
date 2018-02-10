/**
 * Bullet.java
 * @author Yusuf A. Taha
 * @see PlayerBullet.java
 * @see EnemyBullet.java
 * parent class for bullets
 * in the game 🔫
 */

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Bullet
{
	private int x, y;      // the coordinates of the bullet
	private Image img;     // the bullet sprite
    private int speed = 9; // the bullet's speed

    /**
     * CONSTRUCTOR
     * @param x : the horizontal
     * coordinate of the bullet
     * @param y : the vertical
     * coordinate of the bullet
     */
	public Bullet(final Image img, final int x, final int y)
    {
		this.x = x;
		this.y = y;
		this.img = img;
	}

    ///////////////////////
	// SETTERS & GETTERS //
    ///////////////////////

    /**
     * @return the Bullet's horizontal component (X)
     */
    public int getX() {return x;}

    /**
     * Moves the Bullet horizontaly
     * @param direction false is left true is right
     */
	public void moveX(final boolean direction) {x += direction == false ? -speed : speed;}

    /**
     * @return the Bullet's vertical component (Y)
     */
	public int getY() {return y;}

    /**
     * Moves the Bullet vertically
     * @param direction false is down true is up
     */
	public void moveY(final boolean direction) {y += direction == true ? -speed : speed;}

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

    /*
	public boolean collideWith(Enemy enemy)
    {
		final int ax = enemy.getX(), ay = enemy.getY();
		return (ax < x && ax + 48 > x && ay < y && ay + 48 > y);
	}
    */

    /**
     * an overloading function of
     * #collideWith(Enemy enemy)
     * @param Player : the player
     * object the bullet is colliding
     * with
     * @return boolean indicating
     * collision
     */

    /*
	public boolean collideWith(Player player)
    {
		final int ax = player.getX(), ay = player.getY();
		return (ax + 10 < x && ax + 42 > x && ay < y && ay + 32 > y);
	}
    */

	//////////////////////
    //  OTHER FUNCTIONS //
    //////////////////////

    /**
     * Draws the bullet object
     * @param g the graphics
	 * component we drawing to
     */
	public void draw(final Graphics g)
        {g.drawImage(img, getX(), getY(), null);}

}
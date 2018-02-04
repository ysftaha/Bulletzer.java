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
public abstract class Bullet
{
	private int x, y; // the coordinates of the bullet

    /**
     * CONSTRUCTOR
     * @param x : the horizontal
     * coordinate of the bullet
     * @param y : the vertical
     * coordinate of the bullet
     */
	public Bullet(final int x, final int y)
    {
		this.x = x;
		this.y = y;
	}

	// SETTERS & GETTERS
    public int getX() {return x;}

	public int getY() {return y;}

	public void moveY() {y -= 5;}

	public void moveX() {x += 7;}

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

    /**
     * Draws the bullet object
     * @param g : the graphics
	 * component we drawing to
     */
	public abstract void draw(Graphics g);

}
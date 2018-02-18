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
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Bullet
{
	private double x, y;      // the coordinates of the bullet
    private double dx, dy;
	private Image img;        // the bullet sprite
    private double speed = 9;
    private double radians;

    /**
     * CONSTRUCTOR
     * @param x : the horizontal
     * coordinate of the bullet
     * @param y : the vertical
     * coordinate of the bullet
     */
	public Bullet(final Image img, final double x, final double y, final double angle)
    {
		this.x = x;
		this.y = y;
		this.img = img;

		radians = Math.toRadians(angle);

		dx = Math.cos(radians) * speed;
		dy = Math.sin(radians) * speed;
	}

    ///////////////////////
	// SETTERS & GETTERS //
    ///////////////////////

    /**
     * @return the Bullet's horizontal component (X)
     */
    public double getX() {return x;}

    /**
     * Moves the Bullet horizontaly
     * @param direction false is left true is right
     */
	public void moveX(final boolean direction) {x += direction == false ? -speed : speed;}

    /**
     * @return the Bullet's vertical component (Y)
     */
	public double getY() {return y;}

    /**
     * Moves the Bullet vertically
     * @param direction false is down true is up
     */
	public void moveY(final boolean direction) {y += direction == true ? -speed : speed;}

	/**
	 * @return the speed
	 */
	public double getspeed() {return speed;}

	/**
	 * @param speed the speed to set
	 */
	public void setspeed(double speed) {this.speed = speed;}

	/**
	 * @return the radians
	 */
	public double getRadians() {return radians;}

	/**
	 * @param radians the radians to set
	 */
	public void setRadians(double radians) {this.radians = radians;}


	/**
	 * @return the dx
	 */
	public double getDx() {return dx;}

	/**
	 * @param dx the dx to set
	 */
	public void setDx(double dx) {this.dx = dx;}

	/**
	 * @return the dy
	 */
	public double getDy() {return dy;}

	/**
	 * @param dy the dy to set
	 */
	public void setDy(double dy) {this.dy = dy;}

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
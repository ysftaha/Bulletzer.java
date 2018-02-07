/**
 * Player.java
 * @author Yusuf A. Taha
 * @see Game.java
 * The player object 🚀
 */

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public final class Player
{
    // Images
    private final static Image PLAYERIMG = new ImageIcon("Images/playerShip.png").getImage();

    // player object feilds {•̃_•̃}
	private static int x, y;
	private static int health = 5;
	private static int speed  = 7;
	private static int bulletDelayInterval = 12; // the interval to wait between bullets
	private static int bulletDelayIterator = 0;  // decrements by one from
                                                 // bulletDelayInterval to zero then resets

    /**
	 * CONSTRUCTOR
     * @param x : player's x coordinate
     * @param y : player's y coordinate
	 */
	public Player(final int x, final int y)
    {
		Player.x = x;
		Player.y = y;
	}

    ///////////////////////
	// SETTERS & GETTERS //
    ///////////////////////

    /**
     * @return the Health of the player
     */
	public static int getHealth() {return health;}

    /**
     * @param health the health
     * to set
     */
	public static void setHealth(int health) {Player.health = health;}

	/**
	 * @return the speed of the player's movement
	 */
	public static int getSpeed() {return speed;}

    /**
     * @param speed the speed
     * to set
     */
	public static void setSpeed(int speed) {Player.speed = speed;}

	/**
	 * @return the horizontal coordinate (X)
	 */
	public static int getX() {return x;}

    /**
     * Moves the player horizontaly
     * @param direction false is left true is right
     */
	public static void moveX(boolean direction) {x += direction == false ? -speed : speed;}

	/**
	 * @return the the vetrical coordinate (Y)
	 */
	public static int getY() {return y;}

    /**
     * Moves the player vertically
     * @param direction false is down true is up
     */
	public static void moveY(boolean direction) {y += direction == true ? -speed : speed;}

	/**
	 * @return the bulletDelayIterator
	 */
	public static int getBulletDelayIterator() {return bulletDelayIterator;}

	/**
	 * @param bulletDelayIterator the bulletDelayIterator to set
	 */
	public static void setBulletDelayIterator(int bulletDelayIterator)
        {Player.bulletDelayIterator = bulletDelayIterator;}


	/**
	 * @return the bulletDelayInterval
	 */
	public static int getBulletDelayInterval() {return bulletDelayInterval;}

	/**
	 * @param bulletDelayInterval the bulletDelayInterval to set
	 */
	public static void setBulletDelayInterval(int bulletDelayInterval)
        {Player.bulletDelayInterval = bulletDelayInterval;}

    //////////////////////
    //  OTHER FUNCTIONS //
    //////////////////////

    /**
     * Draws the player object
     * @param g : the graphics
	 * component we drawing to
     */
	public static void draw(Graphics g) {g.drawImage(PLAYERIMG, x, y, null);}

    /**
	 * refreshes the bullet delaytime
     * and speed
     */
    public static void refreshBullet()
    {
        if (bulletDelayIterator == 0) {bulletDelayIterator = bulletDelayInterval;}
		else {bulletDelayIterator--;}
    }
}
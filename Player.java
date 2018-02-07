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
	private static int bulletDelay = 0;

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

	// SETTERS & GETTERS
    /**
     * @return the Health of the player
     */
	public int getHealth() {return health;}

    /**
     * @param health : the health
     * to set
     */
	public void setHealth(int health) {Player.health = health;}

	/**
	 * @return the speed of the player's movement
	 */
	public int getSpeed() {return speed;}

    /**
     * @param speed : the speed
     * to set
     */
	public void setSpeed(int speed) {Player.speed = speed;}

	/**
	 * @return the horizontal coordinate (X)
	 */
	public int getX() {return x;}

    /**
     * Moves the player horizontaly
     * @param direction : false is left true is right
     */
	public void moveX(boolean direction) {x += direction == false ? -speed : speed;}

	/**
	 * @return the the vetrical coordinate (Y)
	 */
	public int getY() {return y;}

    /**
     * Moves the player vertically
     * @param direction : false is down true is up
     */
	public void moveY(boolean direction) {y += direction == true ? -speed : speed;}

	/**
	 * @return the bulletDelay
	 */
	public int getBulletDelay() {return bulletDelay;}

	/**
	 * @param bulletDelay the bulletDelay to set
	 */
	public void setBulletDelay(int bulletDelay) {Player.bulletDelay = bulletDelay;}

    /**
     * Draws the player object
     * @param g : the graphics
	 * component we drawing to
     */
	public void draw(Graphics g) {g.drawImage(PLAYERIMG, x, y, null);}
}
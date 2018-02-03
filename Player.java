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
	private int x, y;
	private int health;
	private int speed = 7;

    /**
	 * CONSTRUCTOR
     * @param x : player's x coordinate
     * @param y : player's y coordinate
	 */
	public Player(final int x, final int y)
    {
		this.x = x;
		this.y = y;
	}

	// SETTERS & GETTERS
	public int getHealth() {return health;}

    /**
     * Adds or subtracts one hp
     * @param value : a boolean with
     * false meaning subtract one
     * from health and true meaning
     * add one to health
     */
	public void setHealth(boolean value) {health += value == false ? -1 : 1 ;}

	public int getSpeed() {return speed;}

    /**
     * increases or decreases
     * the speed of the player
     * @param value : a boolean
     * where false is decrease
     * and true is increase
     */
	public void setSpeed(boolean value) {speed += value== false? -2:2;}

	public int getX() {return x;}

	public int getY() {return y;}

    /**
     * Moves the player horizontaly
     * @param direction : false is left true is right
     */
	public void moveX(boolean direction) {x += direction == false ? -speed : speed;}

    /**
     * Moves the player vertically
     * @param direction : false is down true is up
     */
	public void moveY(boolean direction) {y += direction == true ? -speed : speed;}

    /**
     * Draws the player object
     * @param g : the graphics
	 * component we drawing to
     */
	public void draw(Graphics g) {g.drawImage(PLAYERIMG, x, y, null);}
}
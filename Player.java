/**
 * Player.java
 * @author Yusuf A. Taha
 * @see Game.java
 * The player object
 */

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

// TODO Figure out dying
public class Player
{
	public final GamePanel game;

    // Images
    private final static Image PLAYERIMG = new ImageIcon("playerShip.png").getImage();

    // player feilds (Attributes) {•̃_•̃}
	private int x, y;
	private int health;
	private static int speed = 7;

    /**
	 * CONSTRUCTOR
     * @param x : player's x coordinate
     * @param y : player's y coordinate
     * @param game : the panel where the
     * player is going to be drawn
	 */
	public Player(final int x, final int y, final GamePanel game)
    {
		this.x = x;
		this.y = y;
		this.game = game;
	}

	// SETTERS & GETTERS

	public int getHealth() {return health;}

    /**
     * Adds or subtracts one hp
     * @param add : a boolean with
     * false meaning subtract one
     * from health and true meaning
     * add one to health
     */
	public void setHealth(boolean add) {health += add == false ? -1 : 1 ;}

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
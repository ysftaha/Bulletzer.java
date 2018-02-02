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
	// ?????????
	private static final int MAX_DEATH_FRAME = 300;
	private int dying = 0;

	private final GamePanel game;

    // Images
    private static Image PLAYERIMG = new ImageIcon("playerShip.png").getImage();
    private static Image DEADIMG   = new ImageIcon("deadShip.png").getImage();

    // player Info {•̃_•̃}
	private int x, y;
	private static final int SPEED = 9;

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
    /** @return int x */
	public int getX() {return x;}

    /** @return int y */
	public int getY() {return y;}

    /** make player dead */
	public void setDead() {dying = 1;}

    /** raise player from the dead */
	public void revive() {dying = 0;}

    /**
     * Moves the player horizontaly
     * @param direction : false is left true is right
     */
	public void moveX(boolean direction)
    {
        x += direction == false ? -SPEED : SPEED;
		x = Math.max(50, Math.min(600, x)); // ?????????
	}

    /**
     * Moves the player vertically
     * @param direction : false is down true is up
     */
	public void moveY(boolean direction)
    {
        y += direction == false ? -SPEED : SPEED;
		y = Math.max(50, Math.min(800, x)); // ?????????
	}

    /**
     * Draws the player object
     * @param g : the graphics
	 * component we drawing to
     */
	public void draw(Graphics g)
    {
		if (dying > 0)
        {
			// g.drawImage(DEAD, x, y, null);
			dying += 1;
		}
		// if the player is not dead
		else {g.drawImage(PLAYERIMG, x, y, null);}
	}

}
/**
 * Player.java
 * @author Yusuf A. Taha
 * @see Game.java
 * The player object.
 */

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public final class Player
{
	// ?????????
	private static final int MAX_DEATH_FRAME = 300;
	private int dying_frame = 0;

	private final GamePanel game;

    // Images
    public static Image PLAYERIMG = new ImageIcon("playerShip.png").getImage();
    public static Image DEADIMG   = new ImageIcon("deadShip.png").getImage();

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
    /**
     * X coordinate getter
     * @return int x
     */
	public int getX() {return x;}

    /**
     * Y coordinate getter
     * @return int y
     */
	public int getY() {return y;}

	public void setDead() {dying_frame = 1;}
	public void revive() {dying_frame = 0;}

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
		if (dying_frame > 0)
        {
			// g.drawImage(DEAD, x, y, null);
			dying_frame += 1;
		}
		// if the player is not dead
		else {g.drawImage(PLAYERIMG, x, y, null);}
	}

}
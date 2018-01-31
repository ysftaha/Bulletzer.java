/**
 * Player.java
 * @author Yusuf A. Taha
 * @see Game.java
 * The player object.
 */

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics;
import java.io.File;

public class Player
{

	private static final int SPEED = 9;
	// private static final int MAX_DEATH_FRAME = 300;
    // public static Image deadImg = ImageIcon(new File("Playerdeath.png")).getImage();

    // Images
    public static Image playerImg = ImageIcon(new File("playerShip.png")).getImage();

    // player Info {•̃_•̃}
	private int x, y;
	private int dying_frame = 0;

	private final GamePanel game;

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

	public void move(Direction direction)
    {
        x += direction == Direction.LEFT ? -SPEED : SPEED;
		// NOTE: 858 is the right boundary of the screen - 52, the width of the shooter sprite.
		x = Math.max(50, Math.min(858, x));
	}

	public void draw(Graphics g)
    {
		if (dying_frame > 0)
        {
			g.drawImage(DEAD, x, y, null);
			dying_frame += 1;
		}
		else
        {
			g.drawImage(SHOOTER, x, y, null);
		}
	}

}
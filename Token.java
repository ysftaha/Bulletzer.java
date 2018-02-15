/**
 * Token.java
 * @author Yusuf A. Taha
 * Token's class
 */

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Token
{

    private int x, y;
    private int speed = 6, type;
	private String[] imageDirs =
		{"Images/heartTK.png", "Images/sheildTK.png", "Images/bulletTK.png"};

    public Token(final int type, final int x, final int y)
    {
		this.type = type;
        this.x = x;
        this.y = y;
    }

    ///////////////////////
	// SETTERS & GETTERS //
    ///////////////////////

	/**
	 * @return the token's horizontal component (x)
	 */
	public int getX() {return x;}

    /**
     * Moves the token horizontaly
     * @param direction false is left true is right
     */
	public void moveX(final boolean direction) {x += direction == false ? -speed : speed;}

	/**
	 * @return the token's vertical component (Y)
	 */
	public int getY() {return y;}

    /**
     * Moves the token vertically
     * @param direction false is down true is up
     */
	public void moveY(final boolean direction) {y += direction == true ? -speed : speed;}

	/**
	 * @return the speed
	 */
	public int getSpeed() {return speed;}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(final int speed) {this.speed = speed;}

	/**
	 * @return the type
	 */
	public int getType() {return type;}

	//////////////////////
    //  OTHER FUNCTIONS //
    //////////////////////

    /**
     * Draws the token object
     * @param g the graphics
	 * component we drawing to
     */
	public void draw(final Graphics g)
		{g.drawImage(new ImageIcon(imageDirs[type-1]).getImage(), getX(), getY(), null);}

	public boolean collidePlayer()
	{
		final int playerX = Player.getX(), playerY = Player.getY();
		return (playerX - 10 < x && playerX + 42 > x && playerY < y && playerY + 32 > y);
	}

	public void rewardHealth()
		{if (Player.getHealth() < 5) {Player.setHealth(Player.getHealth()+1);} }

	public void rewardSheild()
		{Player.setSheilded(true);}

	public void rewardFrenzy()
		{Player.setBulletDelayInterval(6);}

}
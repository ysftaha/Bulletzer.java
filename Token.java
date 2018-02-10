/**
 * Token.java
 * @author Yusuf A. Taha
 * Token's parent class
 */

import java.awt.Graphics;

public abstract class Token
{

    private int x;
    private int y;
    private int speed;

    public Token(final int x, final int y)
    {
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
	public void moveX(boolean direction) {x += direction == false ? -speed : speed;}

	/**
	 * @return the token's vertical component (Y)
	 */
	public int getY() {return y;}

    /**
     * Moves the token vertically
     * @param direction false is down true is up
     */
	public void moveY(boolean direction) {y += direction == true ? -speed : speed;}

	/**
	 * @return the speed
	 */
	public int getSpeed() {return speed;}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {this.speed = speed;}

	//////////////////////
    //  OTHER FUNCTIONS //
    //////////////////////

	public abstract void draw(Graphics g);
	public abstract void collideObject(Player player);
}
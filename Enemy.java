/**
 * Enemy.java
 * @author Yusuf A. Taha
 * @see Game.java
 * The enemy object
 * class 👾
 */

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public final class  Enemy
{
	// Enemy object feilds
    private int x;
    private int y;
    private int speed;
    private int type;
    private Image enemyImg;

    public Enemy(final int x, final int y,
                 final int speed, final int type)
    {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.type = type;
        enemyImg = new ImageIcon(String.format("Images/enemy%d.png", type)).getImage();
    }

    ///////////////////////
	// SETTERS & GETTERS //
    ///////////////////////

	/**
	 * @return the enemy's horizontal component (x)
	 */
	public int getX() {return x;}

    /**
     * Moves the enemy horizontaly
     * @param direction false is left true is right
     */
    public void moveX(final boolean direction) {x += direction == false ? -speed : speed;}

	/**
	 * @return the enemy's vertical component (y)
	 */
	public int getY() {return y;}

	/**
     * Moves the enemy vertically
     * @param direction false is down true is up
     */
	public void moveY(final boolean direction) {y += direction == true ? -speed : speed;}

	/**
	 * @return the enemy's moving speed
	 */
	public int getSpeed() {return speed;}

	/**
	 * @param speed the enemy's moving speed to set
	 */
	public void setSpeed(final int speed) {this.speed = speed;}

	/**
	 * @return the type
	 */
	public int getType() {return type;}

	/**
	 * @param type the type to set
	 */
	public void setType(final int type) {this.type = type;}

    //////////////////////
    //  OTHER FUNCTIONS //
    //////////////////////

    public void draw(final Graphics g) {g.drawImage(enemyImg, x, y, null);}
}
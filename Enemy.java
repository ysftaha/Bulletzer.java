/**
 * Enemy.java
 * @author Yusuf A. Taha
 * @see Game.java
 * The enemy object
 * class 👾
 */

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public final class  Enemy
{
	// Enemy object feilds
    private int x;
    private int y;
    private int speed;
    private int type;
    private Image enemyImg;
	private int enemyHp;
	// used for second enemy movement
	private  boolean hitEdge = false;
	// used for third enemy movement
	private int spaceTime = 0;
	private int timeIsHoax = 0;
	private final Sound sound   = new Sound();

	/**
	 * CONSTRUCTOR
	 */
    public Enemy(final int x, final int y,
                 final int speed, final int type)
    {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.type = type;
        enemyImg = new ImageIcon(String.format("Images/enemy%d.png", type)).getImage();

		switch(type) // sets anemy hp based on type
		{
			case 1:
				enemyHp = 5;
				break;
			case 2:
				enemyHp = 7;
				break;
			case 3:
				enemyHp = 10;
				break;
		}
    }

    ///////////////////////
	// SETTERS & GETTERS //
    ///////////////////////

	/**
	 * @return the enemy's y coordinate
	 */
	public int getY() {return y;}

	/**
	 * @param val the y to set
	 */
	public void setY(final int val) {this.y = val;}

	/**
	 * @return the enemy's x to get
	 */
	public int getX() {return x;}

	/**
	 * @param val the x to set
	 */
	public void setX(final int val) {this.x = val;}

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

	/**
	 * @return the hitEdge
	 */
	public boolean isHitEdge() {return hitEdge;}

	/**
	 * @param hitEdge the hitEdge to set
	 */
	public void setHitEdge(boolean hitEdge) {this.hitEdge = hitEdge;}

	/**
	 * @return the spaceTime
	 */
	public int getSpaceTime() {return spaceTime;}

	/**
	 * @param spaceTime the spaceTime to set
	 */
	public void setSpaceTime(int spaceTime) {this.spaceTime = spaceTime;}

	/**
	 * @return the timeIsHoax
	 */
	public int getTimeIsHoax() {return timeIsHoax;}

	/**
	 * @param timeIsHoax the timeIsHoax to set
	 */
	public void setTimeIsHoax(int timeIsHoax) {this.timeIsHoax = timeIsHoax;}

	/**
	 * @return the enemyHp
	 */
	public int getEnemyHp() {return enemyHp;}

	/**
	 * @param enemyHp the enemyHp to set
	 */
	public void setEnemyHp(int enemyHp) {this.enemyHp = enemyHp;}


    //////////////////////
    //  OTHER FUNCTIONS //
    //////////////////////

    /** draws the enemy onto the graphic component
     * @param g the graphics component the function
     * is drawing to
     */
    public void draw(final Graphics g) {g.drawImage(enemyImg, x, y, null);}

	/**
	 * spawns bullets depending on
	 * the instance's type
	 * @return arraylist of bullets
	 * produced by the object
	 */

	public ArrayList<Bullet> spawnBullets()
	{
		ArrayList <Bullet> retBullets = new ArrayList<Bullet>();
		int rand = (int) (Math.random() * 200) + 1; // probability of enemy shooting
		switch (this.type)
		{
			case 1:
				if(rand == 1) {retBullets.add(new Bullet(2, x+20, y+30, 0, (int) (Math.random() * 7) + 1, 4));}
				break;
			case 2:
				if(rand == 1) {retBullets.add(new Bullet(2, x+20, y+30, 0, 4, 4));}
				if(rand == 1) {retBullets.add(new Bullet(2, x+20, y+30, 0, 3, 4));}
				if(rand == 1) {retBullets.add(new Bullet(2, x+20, y+30, 0, 5, 4));}
				break;
			case 3:
				if(rand == 1) {retBullets.add(new Bullet(2, x+20, y+30, 0, 4, 4));}
				if(rand == 1) {retBullets.add(new Bullet(2, x+20, y+30, 0, 3, 4));}
				if(rand == 1) {retBullets.add(new Bullet(2, x+20, y+30, 0, 5, 4));}
				if(rand == 1) {retBullets.add(new Bullet(2, x+20, y+30, 0, 6, 4));}
				if(rand == 1) {retBullets.add(new Bullet(2, x+20, y+30, 0, 7, 4));}
				break;
		}
		return retBullets;
	}
}
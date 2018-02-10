/**
 * Token.java
 * @author Yusuf A. Taha
 * Token's parent class
 */

import java.awt.Graphics;


public class Token
{

    private int x, y;
    private int speed = 10, type;
	private String[] imageDirs =
		{"Images/healthTK.png", "Images/sheildTK.png", "Images/bulletTK.png"};

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
		{g.drawImage(img, getX(), getY(), null);}

	private boolean collidePlayer()
	{
		final int ax = Player.getX(), ay = Player.getY();
		return (ax + 10 < x && ax + 42 > x && ay < y && ay + 32 > y);
	}

	public void reward()
	{
		int playerFeild;
		switch(type)
		{
			case 1: // 1 health token
				playerFeild = Player.getHealth();
				if (Player.getHealth()<6)
				{
					playerFeild += this.collidePlayer()? 1:0;
					Player.setHealth(playerFeild);
				}
				break;

			case 2: // speed token
				playerFeild = Player.getSpeed();
				if (Player.getSpeed()<12)
				{
					playerFeild += this.collidePlayer()? 3:0;
					Player.setSpeed(playerFeild);
				}
				break;

			case 3: // sheild token
				playerField = Player.isSheilded();
				if (Player.isSheilded() == false) {Player.setSheilded(true);}
				break;

			default:
				System.out.println("Token type invalid");
				break;
		}
	}
}
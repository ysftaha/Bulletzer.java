import java.awt.Image;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class PlayerBullet extends Bullet
{
    private static Image BULLETIMG = new ImageIcon("Images/playerBullet.png").getImage();

    /**
     * CONSTRUCTOR
     * @param x : Bullet's horizontal coordinate
     * @param y : Bullet's vertical coordinate
     * @see Bullet.java CONSTRICTOR for super
     */
    public PlayerBullet(final int x, final int y) {super(x,y);}

    public int getDeltaY() {return -10;}
	public int getDeltaX() {return 0;}

    public void draw(Graphics g)
    {
        g.drawImage(BULLETIMG, getX(), getY(), null);
    }

}
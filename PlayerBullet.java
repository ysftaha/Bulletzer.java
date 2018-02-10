import java.awt.Image;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class PlayerBullet extends Bullet
{

    /**
     * CONSTRUCTOR
     * @param x : Bullet's horizontal coordinate
     * @param y : Bullet's vertical coordinate
     * @see Bullet.java CONSTRICTOR for super
     */
    public PlayerBullet(final Image img, final int x, final int y)
        {super(img,x,y);}

}
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics;
import java.io.File;

public class Player{

	private static final int SPEED = 9;

	public static BufferedImage Player, DEAD;
	private static final int MAX_DEATH_FRAME = 300;


	static
	{
		try
		{
			Player  = ImageIO.read(new File("images/Player.png"));
			DEAD    = ImageIO.read(new File("images/Playerdeath.png"));
		}
		catch (IOException exception){}
	}

	private final GamePanel game;
	private int x, y;

	private int dying_frame = 0;

	public Player(final int x, final int y, final GamePanel game){
		this.x = x;
		this.y = y;
		this.game = game;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public void setDead(){
		dying_frame = 1;
	}

	public void revive(){
		dying_frame = 0;
	}

	public void move(Direction direction){
		x += direction == Direction.LEFT ? -SPEED : SPEED;
		// NOTE: 858 is the right boundary of the screen - 52, the width of the Player sprite.
		x = Math.max(50, Math.min(858, x));
	}

	public void draw(Graphics g){
		if (dying_frame > 0){
			g.drawImage(DEAD, x, y, null);
			dying_frame += 1;
		}
		else{
			g.drawImage(Player, x, y, null);
		}
	}

}
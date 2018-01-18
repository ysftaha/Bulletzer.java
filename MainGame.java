/**
 * mainGame.java
 * @author Yusuf A. Taha
 * where the magic happens
 */

import java.awt.*;
import javax.swing.JFrame;

/**
 * Main class for the game
 */
public class MainGame extends JFrame
{
    private boolean running = true;
    private int fps = 60;
    private int windowHeight = 800;
    private int windowWidth  = 800;
    private int x = 0;

    /**
     * CONSTRUCTOR
     */
    public MainGame()
    {
        super("Bulletzer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(windowWidth,windowHeight);
        setVisible(true);
        setResizable(true);
    }



    /**
     * This method starts the game and runs it in a loop
     */
    public void run() throws InterruptedException
    {
        while(running)
        {
            long time = System.currentTimeMillis();
            update();
            draw();
            //  delay for each frame  -  time it took for one frame
            time = (1000/fps) - (System.currentTimeMillis() - time);
            if (time > 0)
            {
                try{Thread.sleep(time);}
                catch(InterruptedException exception){exception.printStackTrace();}
            }
            x++;
        }
    }

    /**
     * This method will check for input, move things
     * around and check for win conditions, etc
     */
    void update() {}

    /**
     * This method will draw everything
     */
    void draw()
    {
        Graphics g = getGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, windowWidth, windowHeight);

        g.setColor(Color.BLACK);
        g.drawOval(x, 10, 20, 20);
    }

    public static void main(String...arguments) throws InterruptedException
    {
        MainGame game = new MainGame();
        game.run();
        System.exit(0);
    }
}

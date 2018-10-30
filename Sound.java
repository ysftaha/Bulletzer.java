/**
 * Sound.java
 * @author Youssof A. Taha
 * handels .wav sound files
 */

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound
{
	private AudioInputStream audioInputStream;
	private Clip clip;


	/**
	 * Takes the sound as a string
	 * and tries to play it
	 * -- to many exceptions to handle
	 * so used the direct subclass (Exception)
	 * @param sound a string for the .wav sound
	 * path in the Sounds folder
	 */
	public void playSound (String sound)
	{
		// sound file's directory
		String soundURL = "Sounds/" + sound + ".wav";

		// loads the sound and starts it
		try
		{
			audioInputStream =
				AudioSystem.getAudioInputStream(this.getClass().getResource(soundURL));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.printf("Can't load %s.wav\n", sound);
		}
	}
}

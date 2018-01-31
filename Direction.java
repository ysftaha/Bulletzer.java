/**
 * Direction.java (enum)
 * @author Yusuf A. Taha
 * @see Player.java
 * Im figuring it out still
 */

// TODO dump this and use a bool
public enum Direction
{
	LEFT, RIGHT;

	public static Direction getOpposite(Direction direction)
    {
		if (direction == LEFT)
			return RIGHT;
		return LEFT;
	}
}

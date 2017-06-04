package MagicNumberWonderland;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class GeneralValues {
	static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

	public static int FRAME_WIDTH = gd.getDisplayMode().getWidth();
	public static int FRAME_HEIGHT = gd.getDisplayMode().getHeight();
	public static int TIMER_DELAY = 50;
}

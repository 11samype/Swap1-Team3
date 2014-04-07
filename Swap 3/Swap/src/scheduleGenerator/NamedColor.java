package scheduleGenerator;

import java.awt.Color;

public enum NamedColor {
	  BLUE(Color.BLUE, "Blue"),
	  RED(Color.RED, "Red"),
	  WHITE(Color.white, "White"), 
	  LIGHTGRAY(Color.LIGHT_GRAY, "Light Gray"), 
	  GRAY(Color.gray, "Gray"), 
	  DARKGRAY(Color.DARK_GRAY, "Dark Gray"), 
	  GREEN(Color.green, "Green"), 
	  MAGENTA(Color.magenta, "Magenta"), 
	  CYAN(Color.cyan, "Cyan"), 
	  BLACK(Color.black, "Black"), 
	  PINK(Color.pink, "Pink"), 
	  ORANGE(Color.orange, "Orange"), 
	  YELLOW(Color.yellow, "Yellow");

	  private final Color awtColor;
	  private final String colorName;

	  private NamedColor(Color awtColor, String name) {
	    this.awtColor = awtColor;
	    this.colorName = name;
	  }

	  public Color getAwtColor() {
	    return awtColor;
	  }

	  public String getColorName() {
	    return colorName;
	  }
	}

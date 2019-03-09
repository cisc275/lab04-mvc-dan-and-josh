/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/
import java.util.*;

public class Model {
    int xLoc = 0;
    int yLoc = 0;
    int direction = 5;
    int xMod = 0;
    int yMod = 0;
    final int xIncr = 8;
    final int yIncr = 2;
    int frameWidth;
    int frameHeight;
    int imgWidth;
    int imgHeight;
    Map<Integer, Integer> dirMap = new HashMap<Integer, Integer>() {{
	    put(0, 7);
	    put(1, 4);
	    put(2, 5);
	    put(3, 2);
	    put(4, 1);
	    put(5, 6);
	    put(6, 3);
	    put(7, 0);
	}};
    Map<Integer, Integer []> movMods = new HashMap<Integer,Integer []>() {{
	     put(0, new Integer [] {1,0} );
	     put(1, new Integer [] {0,-1} );
	     put(2, new Integer []  {1,-1} );
	     put(3, new Integer []  {-1,-1} );
	     put(4, new Integer []  {0,1} );
	     put(5, new Integer []  {1,1} );
	     put(6, new Integer []  {-1,1} );
	     put(7, new Integer []  {-1,0} );
	 }};	    

    
    public Model(int fw, int fh, int iw, int ih) {
	frameWidth = fw;
	frameHeight = fh;
	imgWidth = iw;
	imgHeight = ih;
    }

    public void updateLocationAndDirection() {
	xLoc += xIncr * xMod;
	yLoc += yIncr * yMod;
	if (isColliding()) {
	    // System.out.println("Colliding, " + xLoc + "");
	    changeDirection();
	}
	setMoveMods();
    }

    public int getX() {
	return xLoc;
    }

    public int getY() {
	return yLoc;
    }

    public int getDirect() {
	return direction;
    }

    // Returns true if the orc is colliding with the boundaries of the screen
    private boolean isColliding() {
	if ((xLoc < 0)) {
	    //System.out.println("Left");
	    return true;
	} else if (yLoc < 0) {
	    //System.out.println("Top");
	    return true;
	} else if ((xLoc + imgWidth) >= frameWidth) {
	    //System.out.println("Right");
	    return true;
	} else if ((yLoc + imgHeight) >= frameHeight) {
	    //System.out.println("Bottom");
	    return true;
	}
	return false;
    }

    // Changes direction based on current direction
    private void changeDirection() {
	direction = dirMap.get(direction);
    }

    // Sets the motion flags based on current direction
	   
    private void setMoveMods() {
	Integer []mods =  movMods.get(direction);
	xMod = mods[0];
	yMod = mods[1];
    }
}

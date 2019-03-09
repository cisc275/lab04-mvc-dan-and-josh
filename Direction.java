

public enum Direction {

    EAST("east"),
    NORTH("north"),
    NORTHEAST("northeast"),
    NORTHWEST("northwest"),	
    SOUTH("south"),
    SOUTHEAST("southeast"),
    SOUTHWEST("southwest"),
    WEST("west");
	
    private String name = null;
	
    private Direction(String s){
	name = s;
    }
    public String getName() {
	return name;
    }


}

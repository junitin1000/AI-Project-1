public class Box implements Cloneable{

    boolean top;
    boolean bottom;
    boolean left;
    boolean right;
    String takenBy;

    public Box(){
        top = false;
        bottom = false;
        left = false;
        right = false;
        takenBy = "";
    }

    // Override the clone method to create a deep copy of a Box.
    @Override
    public Box clone() {
        try {
            return (Box) super.clone();
        } catch (CloneNotSupportedException e) {
            // Handle the exception as needed.
            return null;
        }
    }


}

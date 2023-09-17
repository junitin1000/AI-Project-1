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

    public boolean checkComplete(String name){
        boolean complete = false;
        if (top && bottom && left && right){
            takenBy = name;
            complete = true;
//            if (!name.equals("mind greg"))
//                System.out.println("BOX TAKEN BY " + name + "!!!");
        }
        return complete;
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

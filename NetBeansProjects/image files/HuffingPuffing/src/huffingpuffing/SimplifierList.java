package huffingpuffing;

//  SimplifierList  --- A recursive list used to keep track of a group
//                      of ImageSimplifier by their names
//
public class SimplifierList {
    
    // Does this list have nothing it it?
    private boolean empty;

    // The first simplifier in the list and its name
    private String name;
    private ImageSimplifier simplifier;
    
    //  All of the other simplifiers in the list
    private SimplifierList others;
    
    // Create a new, empty Simplifier list
    public SimplifierList() {
        empty = true;
    }
    
    // Add an additional simplifier to an existing list
    public SimplifierList( String label, ImageSimplifier simp, SimplifierList rest ) {
        name = label;
        simplifier = simp;
        others = rest;
        empty = false;
    }
    
    // Extract the first simplfier that matches a given name from the list
    public ImageSimplifier getSimplifier( String label ) {
        if ( empty ) {
            return null;
        } else if ( label.equals( name ) ) {
            return simplifier;
        } else {
            return others.getSimplifier( label );
        }
    }
}
    
package rubik;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author suketk
 */
interface ITransform
{
    // Front Transformation --> Right --> Back --> Left
    void setTransform(Transformations transform);
    
    //Returns position of corner "toFind". Changes value of "orientation".
    Corner whereIs(Corner toFind,int[] orientation);

    //Returns position of edge "toFind". Changes value of "orientation".
    Edge whereIs(Edge toFind,int [] orientation);

    //Returns what is at "position".
    Edge whatIsAt(Edge position);
    Corner whatIsAt(Corner position);

    //return position's orientation
    int getOrientation(Edge position);    
    int getOrientation(Corner position);

    //Returns correct sequence of moves, with regard to the transformation.
    String returnMoves(String moves);

}

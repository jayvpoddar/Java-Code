package rubik;
import static rubik.Move.*;
import static rubik.Corner.*;
import static rubik.Edge.*;
/**
 *
 * @author jayp
 */
class Transform implements ITransform
{
    Transformations transform;
    CubieCube cube=new CubieCube();

    public Transform(CubieCube cube)
    {
        this.cube=cube;
    }
    public void setTransform(Transformations transform)
    {
        this.transform=transform;
    }

    //For Front transformation
    Corner[] cpF= { URF, UFL, ULB, UBR, DFR, DLF, DBL, DRB };

    Edge[] epF= { UF, UL, UB, UR, FL, BL, BR, FR, DF, DL, DB, DR };
    int[] epoF= {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 };
    
    Move[] movesF={U,R,F,D,L,B};

    //For Right transformation
    Corner[] cpR = { UBR, URF, UFL, ULB, DRB, DFR, DLF, DBL };
    
    Edge[] epR= { UR, UF, UL, UB, FR, FL, BL, BR, DR, DF, DL, DB };
    int[] epoR= {  0,  0,  0,  0,  1,  1,  1,  1,  0,  0,  0,  0 };

    Move[] movesR={U,B,R,D,F,L};

    //For Back transformation
    Corner[] cpB = { ULB, UBR, URF, UFL, DBL, DRB, DFR, DLF };
    
    Edge[] epB= { UB, UR, UF, UL, BR, FR, FL, BL, DB, DR, DF, DL };
    int[] epoB= {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 };

    Move[] movesB={U,L,B,D,R,F};

    //For Left transformation
    Corner[] cpL = { UFL, ULB, UBR, URF, DLF, DBL, DRB, DFR };

    Edge[] epL= { UL, UB, UR, UF, BL, BR, FR, FL, DL, DB, DR, DF };
    int[] epoL= {  0,  0,  0,  0,  1,  1,  1,  1,  0,  0,  0,  0 };

    Move[] movesL={U,F,L,D,B,R};

    // Arrays of all the transformations for:
    // Corner mapping
    Corner[][] cpTrans={cpF,cpR,cpB,cpL};
    // Edge mapping
    Edge [][] epTrans={epF,epR,epB,epL};
    // Edge orinetation mapping
    int [][] epoTrans={epoF,epoR,epoB,epoL};
    // Move mapping
    Move[][] movesTrans={movesF,movesR,movesB,movesL};

    private Move map(Move move)
    {
        return movesTrans[transform.ordinal()][move.ordinal()];
    }

    private String transformMove(char move)
    {
       if(Character.isLowerCase(move))
       {
           move=Character.toUpperCase(move);
           return (map(Move.valueOf(move+"")).toString()).toLowerCase();
       }
       return (map(Move.valueOf(move+"")).toString());
    }

    private Corner map(Corner corner)
    {
        return cpTrans[transform.ordinal()][corner.ordinal()];
    }

    private Edge map(Edge edge)
    {    
        return epTrans[transform.ordinal()][edge.ordinal()];
    }

    private int mapO(Edge position)
    {
        int orientation=0;
        if(isMiddleLayer(cube.ep[position.ordinal()]))
        {
            if(transform==Transformations.LEFT||transform==Transformations.RIGHT)
            {
                orientation=1;
            }
        }

        orientation+=cube.epo[position.ordinal()]+epoTrans[transform.ordinal()][position.ordinal()];
        orientation%=2;

        return orientation;
    }


    private Edge unMap(Edge edge)
    {
        Edge position=null;
        for (Edge e:Edge.values())
        {
            if(epTrans[transform.ordinal()][e.ordinal()]== edge)
            {
                position=e;
                break;
            }
        }
        return position;
    }

    private Corner unMap(Corner corner)
    {
        Corner position=null;

        for (Corner c:Corner.values())
        {
            if(cpTrans[transform.ordinal()][c.ordinal()]== corner)
            {
                position=c;
                break;
            }
        }
        return position;
    }

    public Edge whereIs(Edge toFind, int[] orientation)
    {
        Edge position=null;
        toFind=map(toFind);
        for (Edge e:Edge.values())
        {
            if(cube.ep[e.ordinal()]== toFind)
            {
                position=e;
                break;
            }
        }

        if(isMiddleLayer(cube.ep[position.ordinal()]))
        {
            if(transform==Transformations.LEFT||transform==Transformations.RIGHT)
            {
                orientation[0]=1;
            }
        }

        orientation[0]+=cube.epo[position.ordinal()]+epoTrans[transform.ordinal()][position.ordinal()];
        orientation[0]%=2;

        position=unMap(position);
        return position;        
    }

    public Corner whereIs(Corner toFind, int[] orientation) 
    {
        Corner position=null;
        toFind=map(toFind);
        for (Corner c:Corner.values())
        {
            if(cube.cp[c.ordinal()]== toFind)
            {
                position=c;
                break;
            }
        }
        orientation[0]=cube.cpo[position.ordinal()];        

        position=unMap(position);
        return position;
    }

    private boolean isMiddleLayer(Edge e)
    {
        if(e==Edge.BR || e==Edge.FL || e==Edge.BL||e==Edge.FR)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Edge whatIsAt(Edge position) 
    {
        Edge realPosition=map(position);
        return unMap(cube.ep[realPosition.ordinal()]);
    }

    public int getOrientation(Edge position)
    {
        return mapO(position);
    }

    public Corner whatIsAt(Corner position)
    {
        Corner realPosition=map(position);
        return unMap(cube.cp[realPosition.ordinal()]);
    }

    public int getOrientation(Corner position)
    {
        Corner realPosition=map(position);
        return cube.cpo[realPosition.ordinal()];
    }

    public String returnMoves(String moves)
    {
        String transformedMoves="";
        for(int i=0;i<moves.length();i++)
        {
            transformedMoves+=transformMove(moves.charAt(i));
        }
       return transformedMoves;
    }

   
}

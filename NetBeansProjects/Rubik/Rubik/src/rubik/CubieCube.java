/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rubik;

import static rubik.Corner.*;
import static rubik.Edge.*;

 class CubieCube {

    // initialize to Id-Cube
    
    // permutations and orientations.
    Corner[] cp = { URF, UFL, ULB, UBR, DFR, DLF, DBL, DRB };
    int[] cpo={0,0,0,0,0,0,0,0};
    
    Edge[] ep= { UF, UL, UB, UR, FL, BL, BR, FR, DF, DL, DB, DR };
    int[] epo= { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

    // ************************************** Moves on the cubie level ***************************************************
    
    // UP face clock-wise
    private static Corner[] cpU = { UBR, URF, UFL, ULB, DFR, DLF, DBL, DRB };
    private static int[] cpoU={0,0,0,0,0,0,0,0};
    
    private static Edge[] epU = { UR, UF, UL, UB, FL, BL, BR, FR, DF, DL, DB, DR };
    private static int[] epoU = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

    // RIGHT face clock-wise
    private static Corner[] cpR = { DFR, UFL, ULB, URF, DRB, DLF, DBL, UBR };
    private static int[] cpoR={2,0,0,1,1,0,0,2};

    private static Edge[] epR = { UF, UL, UB, FR, FL, BL, UR, DR, DF, DL, DB, BR };
    private static int[] epoR = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

    // FRONT face clock-wise
    private static Corner[] cpF = { UFL, DLF, ULB, UBR, URF, DFR, DBL, DRB };
    private static int[] cpoF={1,2,0,0,2,1,0,0};

    private static Edge[] epF = { FL, UL, UB, UR, DF, BL, BR, UF, FR, DL, DB, DR };
    private static int[] epoF = { 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0 };

    // DOWN face clock-wise
    private static Corner[] cpD = { URF, UFL, ULB, UBR, DLF, DBL, DRB, DFR };
    private static int[] cpoD={0,0,0,0,0,0,0,0};

    private static Edge[] epD = { UF, UL, UB, UR, FL, BL, BR, FR, DL, DB, DR, DF };
    private static int[] epoD = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

    // LEFT face clock-wise
    private static Corner[] cpL = { URF, ULB, DBL, UBR, DFR, UFL, DLF, DRB };
    private static int[] cpoL={0,1,2,0,0,2,1,0};

    private static Edge[] epL = { UF, BL, UB, UR, UL, DL, BR, FR, DF, FL, DB, DR };
    private static int[] epoL = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

    // BACK face clock-wise
    private static Corner[] cpB = { URF, UFL, UBR, DRB, DFR, DLF, ULB, DBL };
    private static int[] cpoB={0,0,1,2,0,0,2,1};

    private static Edge[] epB = { UF, UL, BR, UR, FL, UB, DB, FR, DF, DL, BL, DR};
    private static int[] epoB = { 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0 };

    //Arrays of turn arrays
	private static Corner[][] cpDirs={cpU, cpR, cpF, cpD, cpL, cpB};
    private static int[][] cpoDirs={cpoU, cpoR, cpoF, cpoD, cpoL, cpoB};

    private static Edge[][] epDirs = { epU, epR, epF, epD, epL, epB };
    private static int[][] epoDirs = { epoU, epoR, epoF, epoD, epoL, epoB };


    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // return cube in facelet representation
    FaceCube toFaceCube()
    {
        FaceCube fcRet = new FaceCube();
        for (Corner c : Corner.values())
        {
            int orientation=cpo[c.ordinal()];

            Facelet[] cornerPosition=FaceCube.cornerFacelet[c.ordinal()];
            Color[] colors=FaceCube.cornerColor[cp[c.ordinal()].ordinal()];

            fcRet.fColors[cornerPosition[0].ordinal()]=colors[(3-orientation)%3];
            fcRet.fColors[cornerPosition[1].ordinal()]=colors[(4-orientation)%3];
            fcRet.fColors[cornerPosition[2].ordinal()]=colors[(5-orientation)%3];
        }

        for(Edge e : Edge.values())
        {
            int orientation=epo[e.ordinal()];

            Facelet[] edgePosition=FaceCube.edgeFacelet[e.ordinal()];
            Color[] colors=FaceCube.edgeColor[ep[e.ordinal()].ordinal()];

            fcRet.fColors[edgePosition[0].ordinal()]=colors[(2-orientation)%2];
            fcRet.fColors[edgePosition[1].ordinal()]=colors[(1-orientation)%2];
        }
        
        return fcRet;
    }

    private void cwTurn(Move move)
    {
        int turn = move.ordinal();
        
        Corner[] cpRet = new Corner[8];
        int [] cpoRet=new int[8];

        for(Corner c: Corner.values())
        {
            cpRet[c.ordinal()]=cp[cpDirs[turn][c.ordinal()].ordinal()];
            cpoRet[c.ordinal()]=(cpo[cpDirs[turn][c.ordinal()].ordinal()]+cpoDirs[turn][c.ordinal()])%3;
        }
        cp = cpRet;
        cpo= cpoRet;

        Edge[] epRet = new Edge[12];
        int[] epoRet = new int[12];

        for(Edge e: Edge.values())
        {
            epRet[e.ordinal()]=ep[epDirs[turn][e.ordinal()].ordinal()];
            epoRet[e.ordinal()]=(epo[epDirs[turn][e.ordinal()].ordinal()]+epoDirs[turn][e.ordinal()])%2;
        }
        ep = epRet;
        epo = epoRet;
    }
    
    private void ccwTurn(Move move)
    {
        for (int i = 0; i < 3; i++)
        {
            cwTurn(move);
        }
    }

    void doMoves(String moves)
    {
        for (int i = 0; i < moves.length(); i++)
        {
            switch(moves.charAt(i))
            {
                case 'U': cwTurn(Move.U); break;
                case 'R': cwTurn(Move.R); break;
                case 'F': cwTurn(Move.F); break;
                case 'D': cwTurn(Move.D); break;
                case 'L': cwTurn(Move.L); break;
                case 'B': cwTurn(Move.B); break;

                case 'u': ccwTurn(Move.U); break;
                case 'r': ccwTurn(Move.R); break;
                case 'f': ccwTurn(Move.F); break;
                case 'd': ccwTurn(Move.D); break;
                case 'l': ccwTurn(Move.L); break;
                case 'b': ccwTurn(Move.B); break;
            }
        }
    }


























//    void ccwTurn(Move move)
//    {
//        int turn = move.ordinal();
//
//        Corner[] cpRet = new Corner[8];
//        int [] cpoRet=new int[8];
//
//        for(Corner c: Corner.values())
//        {
//            Corner dest= cp[cpDirs[turn][c.ordinal()].ordinal()];
//            cpRet[dest.ordinal()]=cp[c.ordinal()];
//            cpoRet[dest.ordinal()]=(3-((cpo[dest.ordinal()] + cpoDirs[turn][c.ordinal()])%3))%3;
//            cpoRet[dest.ordinal()]=(cpo[dest.ordinal()]+cpoDirs[turn][c.ordinal()])%3;
//        }
//        cp = cpRet;
//        cpo= cpoRet;
//
//        Edge[] epRet = new Edge[12];
//        int[] epoRet = new int[12];
//
//        for(Edge e: Edge.values())
//        {
//            Edge dest= ep[epDirs[turn][e.ordinal()].ordinal()];
//            epRet[dest.ordinal()]=ep[e.ordinal()];
//
//            epoRet[dest.ordinal()]=(epo[e.ordinal()]+epoDirs[turn][dest.ordinal()])%2;
//        }
//        ep = epRet;
//        epo = epoRet;
//    }


}

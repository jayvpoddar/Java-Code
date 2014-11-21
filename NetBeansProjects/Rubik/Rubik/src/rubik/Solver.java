/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rubik;


/**
 *
 * @author suketk
 */
class Solver
{
    Transform transform;
    CubieCube toSolve;
    StringBuilder solution= new StringBuilder();  

    public Solver(CubieCube cc)
    {
        toSolve=cc;
        transform = new Transform(toSolve);
    }

    public void solveCube()
    {
        firstLayerSolve();
        secondLayerSolve();
        thirdLayerSolve();
    }

    //Returns the solution to solve the cube.
    public String getSolution()
    {
        return solution.toString();
    }
   /* private void appendToSoln(String moves)
    {
        // the method atempts to remove some of the redundant moves in the
        //solution
        char chMoves;
        char chSoln;
        int i=0;
        int j = solution.length()-1;
        StringBuilder sbMoves = new StringBuilder(moves);
        for (int j2=0;j >-1; j--)
        {
            j2=j;
            for (;i < sbMoves.length()&&j2>-1;i++)
            {
                chMoves=sbMoves.charAt(i);
                chSoln=solution.charAt(j2);
                if((chMoves==(Character.toLowerCase(chSoln))||
                        chSoln==(Character.toLowerCase(chMoves)))
                        &&chSoln!=chMoves)
                {
                    if(onlyOppositeInMiddle(i,sbMoves,j2,solution))
                    {
                        sbMoves.deleteCharAt(i);
                        solution.deleteCharAt(j2--);

                        i=-1;
                        j=j2+1;
                    }
                }
            }
        }
        if(solution.length()>2&&sbMoves.length()>1)
        {
            if(solution.charAt((solution.length()-2))==sbMoves.charAt(0)
                    &&sbMoves.charAt(0)==solution.charAt((solution.length()-1)))
            {
               solution.deleteCharAt(solution.length()-1);
               solution.deleteCharAt(solution.length()-2);
               solution.append(reverseCase(sbMoves.charAt(0)));
               sbMoves.deleteCharAt(0);
            }
            else if(solution.charAt(solution.length()-1)==sbMoves.charAt(1)
                    &&sbMoves.charAt(0)==solution.charAt(solution.length()-1))
            {
               solution.deleteCharAt(solution.length()-1);
               sbMoves.deleteCharAt(1);
               solution.append(reverseCase(sbMoves.charAt(0)));
               sbMoves.deleteCharAt(0);
            }


            else if(solution.charAt(solution.length()-3)==sbMoves.charAt(0)
                    &&solution.charAt(solution.length()-1)==sbMoves.charAt(0)
                    &&sbMoves.charAt(0)==solution.charAt(solution.length()-2))
            {
               solution.deleteCharAt(solution.length()-1);
               solution.deleteCharAt(solution.length()-1);
               solution.deleteCharAt(solution.length()-1);
               sbMoves.deleteCharAt(0);
            }
            else if(solution.charAt(solution.length()-1)==sbMoves.charAt(1)
                    &&sbMoves.charAt(0)==solution.charAt(solution.length()-2)
                    &&sbMoves.charAt(1)==sbMoves.charAt(0))
            {
               solution.deleteCharAt(solution.length()-1);
               solution.deleteCharAt(solution.length()-1);
               sbMoves.deleteCharAt(1);
               sbMoves.deleteCharAt(0);
            }
        }
        solution.append(sbMoves);
    }*/

    private void firstLayerSolve()
    {
        firstLayerEdgeSolve();
        firstLayerCornerSolve();
    }

    private void firstLayerEdgeSolve()
    {
        for (Transformations t : Transformations.values())
        {
            transform.setTransform(t);
            int[] orientation = new int[1];
            Edge positionOfUF= transform.whereIs(Edge.UF, orientation);
            String moves="";
            if(orientation[0]==0)
            {
                switch(positionOfUF)
                {
                    case UF: break;
                    case UL: moves=transform.returnMoves("LUlu");break;
                    case UB: moves=transform.returnMoves("buuBuu");break;
                    case UR: moves=transform.returnMoves("ruRU");break;
                    case FL: moves=transform.returnMoves("Ulu");break;
                    case BL: moves=transform.returnMoves("ULu");break;
                    case BR: moves=transform.returnMoves("urU");break;
                    case FR: moves=transform.returnMoves("uRU");break;
                    case DF: moves=transform.returnMoves("FF");break;
                    case DL: moves=transform.returnMoves("DFF");break;
                    case DB: moves=transform.returnMoves("DDFF");break;
                    case DR: moves=transform.returnMoves("dFF");break;
                }
            }
            else
            {
                switch(positionOfUF)
                {
                    case UF: moves=transform.returnMoves("fUlu");break;
                    case UL: moves=transform.returnMoves("LF");break;
                    case UB: moves=transform.returnMoves("burU");break;
                    case UR: moves=transform.returnMoves("rf");break;
                    case FL: moves=transform.returnMoves("F");break;
                    case BL: moves=transform.returnMoves("LLFll");break;
                    case BR: moves=transform.returnMoves("RRfrr");break;
                    case FR: moves=transform.returnMoves("f");break;
                    case DF: moves=transform.returnMoves("DRfr");break;
                    case DL: moves=transform.returnMoves("lFL");break;
                    case DB: moves=transform.returnMoves("dRfr");break;
                    case DR: moves=transform.returnMoves("Rfr");break;
                }
            }
            solution.append(moves);
            toSolve.doMoves(moves);
        }
    }

    private void firstLayerCornerSolve()
    {
        for (Transformations t : Transformations.values())
        {
            transform.setTransform(t);
            int[] orientation = new int[1];
            Corner positionOfURF= transform.whereIs(Corner.URF, orientation);
            String moves="";
            if(orientation[0]==0)
            {
                switch(positionOfURF)
                {
                    case URF: break;
                    case UFL: moves=transform.returnMoves("LdlrDR");break;
                    case ULB: moves=transform.returnMoves("BdbrddR");break;
                    case UBR: moves=transform.returnMoves("bddBrDR");break;
                    case DFR: moves=transform.returnMoves("rDRFddf");break;
                    case DLF: moves=transform.returnMoves("DrDRFddf");break;
                    case DBL: moves=transform.returnMoves("ddrDRFddf");break;
                    case DRB: moves=transform.returnMoves("drDRFddf");break;
                }
            }
            else if(orientation[0]==1)
            {
                switch(positionOfURF)
                {
                    case URF: moves=transform.returnMoves("rddRFddf");break;
                    case UFL: moves=transform.returnMoves("fdFFddf");break;
                    case ULB: moves=transform.returnMoves("lFddfL");break;
                    case UBR: moves=transform.returnMoves("FbdfB");break;
                    case DFR: moves=transform.returnMoves("FDf");break;
                    case DLF: moves=transform.returnMoves("rDR");break;
                    case DBL: moves=transform.returnMoves("rddR");break;
                    case DRB: moves=transform.returnMoves("dFDf");break;
                }
            }
            else
            {
                switch(positionOfURF)
                {
                    case URF: moves=transform.returnMoves("FddfrddR");break;
                    case UFL: moves=transform.returnMoves("LrDRl");break;
                    case ULB: moves=transform.returnMoves("BrddRb");break;
                    case UBR: moves=transform.returnMoves("RDrrddR");break;
                    case DFR: moves=transform.returnMoves("rdR");break;
                    case DLF: moves=transform.returnMoves("DrdR");break;
                    case DBL: moves=transform.returnMoves("Fddf");break;
                    case DRB: moves=transform.returnMoves("Fdf");break;
                }
            }
            solution.append(moves);
            toSolve.doMoves(moves);
         
        }
    }

    private char oppositeSide(char ch1)
    {
        switch(Character.toUpperCase(ch1))
        {
            case 'U':return 'D';
            case 'D':return 'U';
            case 'R':return 'L';
            case 'L':return 'R';
            case 'F':return 'B';
            case 'B':return 'F';
        }
        return ' ';
    }

    private boolean onlyOppositeInMiddle(int i, StringBuilder sbMoves, int j, StringBuilder solution) 
    {
        if(i==(solution.length()-1-j))
        {
            return true;
        }
        char chMoves=sbMoves.charAt(i);

        char oppositeMovecw=oppositeSide(chMoves);

        char oppositeMoveccw=Character.toLowerCase(oppositeMovecw);

        for (int k = 0; k < i; k++)
        {
            if(sbMoves.charAt(k)!=oppositeMoveccw&&sbMoves.charAt(k)!=oppositeMovecw)
            {
                return false;
            }
        }
        for (int l=solution.length()-1;l>j;l--)
        {
            if(solution.charAt(l)!=oppositeMoveccw&&solution.charAt(l)!=oppositeMovecw)
            {
                return false;
            }
        }
        return true;
    }

    private String reverseCase(char ch)
    {
        if(Character.isLowerCase(ch))
        {
          return Character.toUpperCase(ch)+"";
        }
        return Character.toLowerCase(ch)+"";       
    }

    private void secondLayerSolve()
    {
        for (int i = 0; i < 2; i++)
        {
            for (Transformations t : Transformations.values())
            {
                transform.setTransform(t);
                int[] orientation = new int[1];
                Edge position= transform.whereIs(Edge.FR, orientation);
                String moves="";

                /*
                 * Puts FR at its correct position only if it is in the
                 * third layer presently.
                 */
                if(orientation[0]==0)
                {
                    switch(position)
                    {
                        case FR: break;
                        case DF: moves=transform.returnMoves("DDFdfdrDR");break;
                        case DR: moves=transform.returnMoves("DFdfdrDR");break;
                        case DB: moves=transform.returnMoves("FdfdrDR");break;
                        case DL: moves=transform.returnMoves("dFdfdrDR");break;
                    }
                }
                else
                {
                    switch(position)
                    {
                        case FR: moves=transform.returnMoves("rDRDFdfDrDRDFdf");break;
                        case DF: moves=transform.returnMoves("drDRDFdf");break;
                        case DR: moves=transform.returnMoves("DDrDRDFdf");break;
                        case DB: moves=transform.returnMoves("DrDRDFdf");break;
                        case DL: moves=transform.returnMoves("rDRDFdf");break;
                    }
                }
                solution.append(moves);;
                toSolve.doMoves(moves);

                /*
                * If a middle layer cubie is in FR, it is removed and replaced
                * with a third layer cubie.
                */
                Edge atEdgeFR = transform.whatIsAt(Edge.FR);
                if(isMiddleLayerCubie(atEdgeFR))
                {
                    for (int j = 0; j < 4; j++)
                    {
                        Edge atEdgeDF = transform.whatIsAt(Edge.DF);
                        //Replacing with a third layer cubie.
                        if(!isMiddleLayerCubie(atEdgeDF))
                        {
                            moves=transform.returnMoves("drDRDFdf");
                            solution.append(moves);
                            toSolve.doMoves(moves);
                            break;
                        }
                        //There must be a third layer cubie in the third layer.
                        //So it does Move 'D' until a third layer cubie comes to DF.
                        else
                        {
                            solution.append("D");
                            toSolve.doMoves("D");
                        }
                    }
                }
            }
        }
    }

    //Returns true if Edge "e" belongs in the middle layer.
    private boolean isMiddleLayerCubie(Edge e)
    {
        //The case of e being FR is excluded because it is already handled.
        if(e==Edge.BR || e==Edge.FL || e==Edge.BL)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private final Edge[] thirdLayerEdges= {Edge.DF, Edge.DL, Edge.DB, Edge.DR};

    //Edge orientations for the third layer edges.
    //eoThirdLayer[i] is orientation of edge at thirdLayerEdges[i].
    private int[] eoThirdLayer= new int[4];

    private void thirdLayerSolve()
    {
        thirdLayerCrossOrientationsSolve();
        thirdLayerCrossPermutationsSolve();
        thirdLayerCornerPermutationSolve();
        thirdLayerCornerOrientationSolve();
    }

    private void thirdLayerCrossOrientationsSolve()
    {
        setEdgeOrientations();

        if(!isCross())
        {
            String moves="";
            if(isLine())
            {
                moves=fixLineMoves();
            }
            else if(isLshaped())
            {
                moves=fixLshapeMoves();
            }
            //If it is not a L, line, or a cross, it must be a dot.
            else
            {
                moves=fixDotMoves();
            }

            solution.append(moves);
            toSolve.doMoves(moves);
        }
    }

    private String fixLineMoves()
    {
        return transform.returnMoves("FLDldf");
    }

    private String fixLshapeMoves()
    {
        return transform.returnMoves("FDLdlf");
    }

    private String fixDotMoves()
    {
        String moves="FLDldf";
        transform.setTransform(Transformations.BACK);
        return moves+=fixLshapeMoves();
    }

    //Sets eoThirdLayer to correct values.
    private void setEdgeOrientations()
    {
        transform.setTransform(Transformations.FRONT);
        for (int i = 0; i < 4; i++)
        {
            eoThirdLayer[i]= transform.getOrientation(thirdLayerEdges[i]);
        }
    }

    private boolean isCross()
    {
        for (int i = 0; i < 4; i++)
        {
            if(eoThirdLayer[i]==1)
            {
                return false;
            }
        }
        return true;
    }

    /*
     * Returns true if third layer edges make a line.
     * It also changes it into the correct transformation to fix it.
     */
    private boolean isLine()
    {
        int[] line1 = { 0, 1, 0, 1};
        int[] line2 = { 1, 0, 1, 0}; 

        if(areEqual(eoThirdLayer, line1))
        {
            transform.setTransform(Transformations.RIGHT);
            return true;
        }
        if(areEqual(eoThirdLayer, line2))
        {
            return true;
        }
        return false;
    }

    /*
     * Returns true if third layer edges make a L-shape.
     * It also changes it into the correct transformation to fix it.
     */
    private boolean isLshaped()
    {
        int[] l1 = { 1, 1, 0, 0};
        int[] l2 = { 0, 1, 1, 0};
        int[] l3 = { 0, 0, 1, 1};
        int[] l4 = { 1, 0, 0, 1};

        if(areEqual(eoThirdLayer, l1))
        {
            return true;
        }
        if(areEqual(eoThirdLayer, l2))
        {
            transform.setTransform(Transformations.LEFT);
            return true;
        }
        if(areEqual(eoThirdLayer, l3))
        {
            transform.setTransform(Transformations.BACK);
            return true;
        }
        if(areEqual(eoThirdLayer, l4))
        {
            transform.setTransform(Transformations.RIGHT);
            return true;
        }
        return false;
    }

    //Assumes both arrays are of equal size.
    private boolean areEqual(int[] arrOne, int[] arrTwo)
    {
        for (int i = 0; i < arrTwo.length; i++)
        {
            if(arrOne[i] != arrTwo[i])
            {
                return false;
            }
        }
        return true;
    }

    //Edge permutations for the third layer edges.
    //epThirdLayer[i] is what is at thirdLayerEdges[i].
    private Edge[] epThirdLayer = new Edge[4];

    private void thirdLayerCrossPermutationsSolve()
    {
        setEdgePermutations();
        
        if(!edgesAreCorrect())
        {
            String moves="";
            
            for (int i = 0; i < 4; i++)
            {
                //Two edges must necessarily be correct.

                //If edges are opposite, they are made adjacent in this conditional.
                if(edgesAreOpposite())
                {
                    moves=transform.returnMoves("LDlDLDDl");
                    solution.append(moves);
                    toSolve.doMoves(moves);
                }

                //Adjacent edges are corrected.
                if(edgesAreAdjacent())
                {
                    moves=transform.returnMoves("LDlDLDDlD");
                    solution.append(moves);
                    toSolve.doMoves(moves);
                    break;
                }

                //"D" moves may be required to make the egdes adjacent or opposite.
                solution.append("D");
                toSolve.doMoves("D");
            }
        }
    }

    //Sets epThirdLayer to correct values.
    private void setEdgePermutations()
    {
        transform.setTransform(Transformations.FRONT);
        for (int i = 0; i < 4; i++)
        {
            epThirdLayer[i] = transform.whatIsAt(thirdLayerEdges[i]);
        }
    }

    //Returns true if the edges are in the correct places.
    private boolean edgesAreCorrect()
    {
        boolean areCorrect=false;
        String moves="";
        for (int i = 0; i < 4; i++)
        {
            setEdgePermutations();
            if(areEqual(epThirdLayer, thirdLayerEdges))
            {
                areCorrect=true;
                solution.append(moves);
                break;
            }
            else
            {
                moves += "D";
                toSolve.doMoves("D");
            }
        }
        return areCorrect;
    }

    private boolean edgesAreOpposite()
    {
        setEdgePermutations();

        Edge[] oppEdges1 = {Edge.DF, Edge.DR, Edge.DB, Edge.DL};
        Edge[] oppEdges2 = {Edge.DB, Edge.DL, Edge.DF, Edge.DR};

        if(areEqual(epThirdLayer, oppEdges1))
        {
            return true;
        }
        if(areEqual(epThirdLayer, oppEdges2))
        {
            transform.setTransform(Transformations.RIGHT);
            return true;
        }
        return false;
    }

    private boolean edgesAreAdjacent()
    {
        setEdgePermutations();

        Edge[] adjEdges1 = {Edge.DF, Edge.DL, Edge.DR, Edge.DB};
        Edge[] adjEdges2 = {Edge.DF, Edge.DB, Edge.DL, Edge.DR};
        Edge[] adjEdges3 = {Edge.DL, Edge.DF, Edge.DB, Edge.DR};
        Edge[] adjEdges4 = {Edge.DR, Edge.DL, Edge.DB, Edge.DF};

        if(areEqual(epThirdLayer,adjEdges1))
        {
            transform.setTransform(Transformations.RIGHT);
            return true;
        }
        if(areEqual(epThirdLayer,adjEdges2))
        {
            transform.setTransform(Transformations.BACK);
            return true;
        }
        if(areEqual(epThirdLayer,adjEdges3))
        {
            transform.setTransform(Transformations.LEFT);
            return true;
        }
        if(areEqual(epThirdLayer,adjEdges4))
        {
            return true;
        }
        return false;
    }

    //Assumes both arrays are of equal size.
    private boolean areEqual(Edge[] arrOne, Edge[] arrTwo)
    {
        for (int i = 0; i < arrTwo.length; i++)
        {
            if(arrOne[i] != arrTwo[i])
            {
                return false;
            }
        }
        return true;
    }

    private final Corner[] thirdLayerCorners= {Corner.DLF, Corner.DBL, Corner.DRB, Corner.DFR};

    //Corner permutations for the third layer corners.
    //cpThirdLayer[i] is what is at thirdLayerCorners[i].
    private Corner[] cpThirdLayer = new Corner[4];

    private void thirdLayerCornerPermutationSolve()
    {
        //correctCorners are number of corners that are in the correct
        //place. Their orientation may not be 0.
        int correctCorners = noOfCorrectCorners();

        //There can only be 0, 1, or 4 correct corners.
        if(correctCorners!=4)
        {
            String moves="";

            //After this conditional, there will definitely be one correct corner.
            if(correctCorners==0)
            {
                moves="DLdrDldR";
                solution.append(moves);
                toSolve.doMoves(moves);
            }

            while(noOfCorrectCorners()!=4)
            {
                changeTransformation();
                moves=transform.returnMoves("DLdrDldR");
                solution.append(moves);
                toSolve.doMoves(moves);
            }
        }
    }

    //Returns the number of corners which are in the correct place.
    private int noOfCorrectCorners()
    {
        setCornerPermutations();

        int noOfCorrectCorners=0;
        for (int i = 0; i < 4; i++)
        {
            if(cpThirdLayer[i]==thirdLayerCorners[i])
            {
                noOfCorrectCorners++;
            }
        }
        return noOfCorrectCorners;
    }

    //Changes transformation so third layer corners can be fixed.
    private void changeTransformation()
    {
        Transformations[] t = Transformations.values();
        for (int i = 0; i < 4; i++)
        {
            if(cpThirdLayer[i]==thirdLayerCorners[i])
            {
                transform.setTransform(t[i]);
            }
        }
    }

    //Sets cpThirdLayer to correct values.
    private void setCornerPermutations()
    {
        transform.setTransform(Transformations.FRONT);
        for (int i = 0; i < 4; i++)
        {
            cpThirdLayer[i] = transform.whatIsAt(thirdLayerCorners[i]);
        }
    }

    private void thirdLayerCornerOrientationSolve()
    {
        transform.setTransform(Transformations.FRONT);
        for (int i = 0; i < 4; i++)
        {
            String moves="";
            int orientation = transform.getOrientation(Corner.DLF);

            //If orientation is 0, there is nothing to be done.
            if(orientation==1)
            {
                moves="luLUluLU";
                solution.append(moves);
                toSolve.doMoves(moves);
            }
            else if(orientation==2)
            {
                moves="luLUluLUluLUluLU";
                solution.append(moves);
                toSolve.doMoves(moves);
            }

            solution.append("D");
            toSolve.doMoves("D");
        }
    }
   

}

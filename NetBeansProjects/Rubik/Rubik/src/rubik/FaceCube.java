/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rubik;

import static rubik.Facelet.*;
import static rubik.Color.*;
import static rubik.Corner.*;

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//Cube on the facelet level
class FaceCube {
	public Color[] fColors = new Color[54];

	 
	// Map the corner positions to facelet positions. 
        // cornerFacelet[URF.ordinal()][0] e.g. gives the position of the
	// facelet in the URF corner position, which defines the orientation.
        // <br>
	// cornerFacelet[URF.ordinal()][1] and cornerFacelet[URF.ordinal()][2] 
        // give the position of the other two facelets
	// of the URF corner (clockwise).
	final static Facelet[][] cornerFacelet = {
                                                   { Facelet.U9, Facelet.R1, Facelet.F3 },
                                                   { Facelet.U7, Facelet.F1, Facelet.L3 }, 
                                                   { Facelet.U1, Facelet.L1, Facelet.B3 }, 
                                                   { Facelet.U3, Facelet.B1, Facelet.R3 },
                                                   { Facelet.D3, Facelet.F9, Facelet.R7 }, 
                                                   { Facelet.D1, Facelet.L9, Facelet.F7 }, 
                                                   { Facelet.D7, Facelet.B9, Facelet.L7 }, 
                                                   { Facelet.D9, Facelet.R9, Facelet.B7 }
                                             };


	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// Map the corner positions to facelet colors.
	final static Color[][] cornerColor = {
                                               { U, R, F },
                                               { U, F, L }, 
                                               { U, L, B }, 
                                               { U, B, R }, 
                                               { D, F, R }, 
                                               { D, L, F }, 
                                               { D, B, L }, 
                                               { D, R, B }
                                         };

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	final static Facelet[][] edgeFacelet = {
                                                {Facelet.U8, Facelet.F2},
                                                {Facelet.U4, Facelet.L2},
                                                {Facelet.U2, Facelet.B2},
                                                {Facelet.U6, Facelet.R2},
                                                {Facelet.F4, Facelet.L6},
                                                {Facelet.B6, Facelet.L4},
                                                {Facelet.B4, Facelet.R6},
                                                {Facelet.F6, Facelet.R4},
                                                {Facelet.D2, Facelet.F8},
                                                {Facelet.D4, Facelet.L8},
                                                {Facelet.D8, Facelet.B8},
                                                {Facelet.D6, Facelet.R8},
                                            };
    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    final static Color[][] edgeColor = {
                                               { U, F },
                                               { U, L },
                                               { U, B },
                                               { U, R },
                                               { F, L },
                                               { B, L },
                                               { B, R },
                                               { F, R },
                                               { D, F },
                                               { D, L },
                                               { D, B },
                                               { D, R },
                                         };

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    FaceCube()
    {
		String s = "UUUUUUUUURRRRRRRRRFFFFFFFFFDDDDDDDDDLLLLLLLLLBBBBBBBBB";
		for (int i = 0; i < 54; i++)
                {
            fColors[i] = Color.valueOf("" + s.charAt(i));
        }

	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// Construct a facelet cube from a string
	FaceCube(String cubeString)
        {
		for (int i = 0; i < cubeString.length(); i++)
                {
                fColors[i] = Color.valueOf("" + cubeString.charAt(i));
                }
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// Gives string representation of a facelet cube
    @Override
    public String toString() 
    {
        String s = "";
        for (int i = 0; i < fColors.length; i++)
        {
            switch(fColors[i])
            {
                case U: s += "U"; break;
                case R: s += "R"; break;
                case F: s += "F"; break;
                case D: s += "D"; break;
                case L: s += "L"; break;
                case B: s += "B"; break;
            }
        }
        return s;
    }

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//	 Gives CubieCube representation of a faceletcube
    CubieCube toCubieCube()
    {
        CubieCube ccRet = new CubieCube();
        Corner[] cpRet=new Corner[8];
        int[] cpoRet=new int[8];
        for (Corner c : Corner.values())
        {
            Facelet[] corner=cornerFacelet[c.ordinal()];
            Color [] colorsAtCorner={
                                        fColors[corner[0].ordinal()],
                                        fColors[corner[1].ordinal()],
                                        fColors[corner[2].ordinal()]
                                    };

            cpRet[c.ordinal()]=findCorner(colorsAtCorner, ccRet);
            cpoRet[c.ordinal()]=getCornerOrientation(cpRet[c.ordinal()],colorsAtCorner);
        }
        ccRet.cp=cpRet;
        ccRet.cpo=cpoRet;
        Edge[] epRet=new Edge[12];
        int[] epoRet=new int[12];

		for (Edge e : Edge.values())
        {
            Facelet[] edge=edgeFacelet[e.ordinal()];
            Color [] colorsAtEdge={
                                        fColors[edge[0].ordinal()],
                                        fColors[edge[1].ordinal()],                                        
                                  };

            epRet[e.ordinal()]=findEdge(colorsAtEdge, ccRet);
            epoRet[e.ordinal()]=getEdgeOrientation(epRet[e.ordinal()],colorsAtEdge);
        }
        ccRet.ep=epRet;
        ccRet.epo=epoRet;
        return ccRet;
	}
    private Corner findCorner(Color[] colors,CubieCube cube)
    {
        int cornerOrdinal=0;
        for(Color[] cs:cornerColor)
        {

            if(isColorPresent(colors[0], cs)&&isColorPresent(colors[1], cs)
                    &&isColorPresent(colors[2], cs))
            {
                return cube.cp[cornerOrdinal];
            }
            cornerOrdinal++;
        }
        return null;
    }

    private Edge findEdge(Color[] colors, CubieCube cube)
    {

        int edgeOrdinal=0;
        for(Color[] cs:edgeColor)
        {

            if(isColorPresent(colors[0], cs)&&isColorPresent(colors[1], cs))
            {
                return cube.ep[edgeOrdinal];
            }
            edgeOrdinal++;
        }
        return null;
    }
// checks forn the corner orientation of the input
    private int getCornerOrientation(Corner corner,Color[] colorsAtCorner)
    {
        int orientation=0;
        Color[] defaultColorsOfCorner=cornerColor[corner.ordinal()];

        if(colorsAtCorner[1]==defaultColorsOfCorner[0])
        {
            orientation=1;
        }
        else if(colorsAtCorner[2]==defaultColorsOfCorner[0])
        {
            orientation=2;
        }
        return orientation;
    }

    private int getEdgeOrientation(Edge edge, Color[] colorsAtEdge) 
    {
        Color[] defaultColorsOfEdge=edgeColor[edge.ordinal()];
        if(defaultColorsOfEdge[0]==colorsAtEdge[0])
        {
            return 0;
        }
        return 1;
    }
    private boolean isColorPresent(Color color,Color[] colors)
    {
        for(Color c:colors)
        {
            if(c==color)
            {
                return true;
            }
        }
        return false;
    }
}

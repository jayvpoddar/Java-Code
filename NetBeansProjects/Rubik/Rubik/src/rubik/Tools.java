/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rubik;

import java.util.Random;

/**
 *
 * @author jayp
 */
class Tools 
{
    public static CubieCube randomCube()
    {
        CubieCube cube = new CubieCube();   
        char[] moves={'U','R','F','D','L','B',
                      'u','r','f','d', 'l','b'};
        String ranMoves="";
        Random random=new Random();

        for (int i = 0; i < 10; i++)
        {
            ranMoves+=moves[random.nextInt(12)];
        }
         cube.doMoves(ranMoves);
        
        return cube;
    }
//    String changeCaseReverse(String s)
//    {
//        String result="";
//        for (int i = 0; i < s.length(); i++)
//        {
//            char ch=s.charAt(i);
//            if(Character.isLowerCase(ch))
//            {
//                result+=Character.toUpperCase(ch);
//            }
//            else
//            {
//                result+=Character.toLowerCase(ch);
//            }
//        }
//        StringBuffer sb=new StringBuffer(result);
//        return sb.reverse().toString();
//    }
    static CubieCube resetCube()
    {
        return new CubieCube();
    }

}
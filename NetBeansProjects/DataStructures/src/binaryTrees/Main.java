/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package binaryTrees;

//import Interfaces.IList;


/**
 *
 * @author jayp
 */
public class Main
{
    public static void main(String[] args)
    {
        AVLTree tree=new AVLTree();

        int[] input={3,6,2,989,76,89,65,0,43,676,95,93,45,/*79,7897,877,993,9493,9434,943,779,90,*/788,66,293,98};
        
        for(int i:input)
        {           
            tree.put(i);
        }
//        for(int i:input)
//        {
//            System.out.println(i+":\n");
//            System.out.println(" "+tree.predecessor(i));
//            System.out.println(" "+tree.successor(i));
//        }

//        IList list=tree.inOrderTraversal();
//        for(int i=0;i<list.size();i++)
//        {
//            System.out.println(list.get(i));
//        }
        for(int i:input)
        {
            System.out.println(i);
            tree.delete(i);
        }
//
//        IList list2=tree.inOrderTraversal();
//        if(list2.size()==0)
//        {
//            System.out.println("no element present");
//        }
//        for(int i=0;i<list2.size();i++)
//        {
//            int value=list2.get(i);
//            System.out.println(value);
//        }
        System.out.println(tree.height());
        
    }


}

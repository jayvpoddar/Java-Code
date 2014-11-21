/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package maps;

import interfaces.IList;

/**
 *
 * @author jayp
 */
public class Main
{    
    public static void main(String[] args)
    {
        testHashMap();
    }
    public static void testHashMap()
    {
        int [] input = {15, 13, 2, 7, 13, 14, 10};

        HashMap array =new HashMap(10);

        String[] namesToPut={"Ujwal","Rahul","Aakash","Dinesh","Jay","Rahul","Nitin"};

        int j=0;
        for(int i :input)
        {            
            array.put(i,namesToPut[j]);
            j++;
        }
        IList keys=array.keySet();
        String[] names= array.valueSet();

       for(int i=0;i<keys.size();i++)
        {
           System.out.println(names[i]+" : \n");
           System.out.println(keys.get(i)+"\n");
        }

//        for(int i: input)
//        {
//            System.out.println("\n"+array.get(i));
//        }
        
//        printList(keys);
    }

    public static void testStudentmap()
    {
         int [] input = {12, 34, 5,4883,3049,439,4034,30,40};

        StudentMap array =new StudentMap(100);
        char ch='a';

        for(int i :input)
        {
            array.put(i,""+ch++);
        }        

        for(int i: input)
        {
            System.out.println("\n"+array.get(i));
        }

        for(int i: input)
        {
            System.out.println(i+":");
            System.out.println(array.get(i)+"\n");
        }
    }

    public static void printList(IList list)
    {
        for(int i=0;i<list.size();i++)
        {
            System.out.println(list.get(i));
        }
    }

}

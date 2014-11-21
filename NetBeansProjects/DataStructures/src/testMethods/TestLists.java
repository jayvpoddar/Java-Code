package testMethods;

import interfaces.IList;
import arrayLists.*;
import linkedList.*;


/**
 *
 * @author ujwals
 */
public class TestLists
{
    public static void main (String[] args) 
    {
        System.out.println("testSimpleArrayList");
        testSimpleArrayList();
//
//        System.out.println("testExtendedArrayList");
//        testExtendedArrayList();

//        System.out.println("testLinkedList");
//        testLinkedList();

//        System.out.println("testDoubleLinkedList");
//        testDoubleLinkedList();
   }

    public static void testSimpleArrayList() 
    {
        IList list = new SimpleArrayList();
        testHarness(list);
    }

    public static void testExtendedArrayList() 
    {
        int increment = 10;
        IList list = new ExtendedArrayList(increment);
        testHarness(list);
    }

    public static void testLinkedList() 
    {
        IList list = new LinkedList();
        testHarness(list);
    }

    public static void testDoubleLinkedList() 
    {
        IList list = new DoubleLinkedList();
        testHarness(list);
    }

    public static void testHarness(IList list) 
    {
        int size = 10;
        for (int i = 0;i < size; i++)
        {
            list.put(i);
        }
        list.insertAt(13,11);
        printList(list);
        System.out.println();
    }

    public static void printList (IList list)
    {
        if (list.size() == 0)
        {
            System.out.println("0 elements");
        }

        for (int i = 0; i < list.size(); i++)
        {
            System.out.println (list.get(i));
        }
    }
}

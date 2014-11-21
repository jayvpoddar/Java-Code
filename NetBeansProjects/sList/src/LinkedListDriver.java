/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jaypoddar
 */
public class LinkedListDriver
{
    public static void main(String args[])
    {
        SLinkedList<Integer> s = new SLinkedList<>();
        int a= 2;
        int b=3;
        int c=4;
        s.addElement(a);
        s.addElement(b);
        s.addElement(c);
        s.printList();		
    }
}
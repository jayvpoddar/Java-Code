package testMethods;

import interfaces.*;
import stack.*;


/**
 *
 * @author ujwals
 */
public class TestStack
{
    public static  void main(String[] args)
    {
        System.out.println("testSimpleStack");
        testSimpleStack();

        System.out.println("testStack");
        testStack();
    }

    public static void testSimpleStack()
    {
        IStack stack = new SimpleStack(10);
        testHarness(stack);
    }

    public static void testStack()
    {
        IStack stack = new Stack();
        testHarness(stack);
    }

    public static void testHarness(IStack stack)
    {
        int size =10;

        for (int i = 0; i < size; i++)
        {
            stack.push(i);
        }
        printStack(stack);
        System.out.println();

        for (int i = 0; i < size; i++)
        {
            stack.pop();
        }
        printStack(stack);
        System.out.println();
    }

    public static void printStack (IStack s)
    {
        if (s.size() == 0)
        {
            System.out.println("O elements");
        }

        for (int i = 0; i < s.size(); i++)
        {
            System.out.println(s.peek(i));
        }
    }
}

package testMethods;

import interfaces.IQueue;
import queues.*;

/**
 *
 * @author ujwals
 */
public class TestQueues
{

    public static void main(String[] args)
    {
        System.out.println("testQueue");
        testQueue();
    }

    public static void testQueue()
    {
        int maxSize = 10;
        IQueue q = new Queue(maxSize);

        testHarness(q, maxSize);
    }

    public static void testHarness(IQueue q, int maxSize)
    {
        for (int i = 0; i < maxSize; i++)
        {
            q.enQueue(i);
        }
        printQueue(q);
        System.out.println();

        q.deQueue();
        printQueue(q);
        System.out.println();

        q.deQueue();
        printQueue(q);
        System.out.println();

        q.enQueue(12);
        printQueue(q);
        System.out.println();

        q.enQueue(13);
        printQueue(q);
        System.out.println();

//        q.enQueue(14);
//        printQueue(q);
//        System.out.println();
//
//        q.clear();
//        q.deQueue();
    }

    public static void printQueue(IQueue q)
    {
        if(q.size() == 0)
        {
            System.out.println("O Elements");
        }

        for (int i = 0; i < q.size(); i++)
        {
            System.out.println(q.peek(i));
        }
    }
}

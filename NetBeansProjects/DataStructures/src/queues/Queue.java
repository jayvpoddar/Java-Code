package queues;

import interfaces.IQueue;

/**
 *
 * @author jayp
 */
public class Queue implements IQueue
{
    private int[] _queue;
    private int _popIndex = 0;
    private int _pushIndex = 0;

    public Queue(int maxSize)
    {
        _queue = new int[maxSize];
    }

    public boolean isFull()
    {
        return (size() == _queue.length);
    }

    public void enQueue(int value)
    {
        assert(!isFull()) : "Memory OverFlow";

        int index = _pushIndex%_queue.length;
        _queue[index] = value;
        _pushIndex++;
    }

    public boolean isEmpty()
    {
        return (size() == 0);
    }

    public int deQueue()
    {
        assert (!isEmpty()) : "No Elements";

        int index = _popIndex%_queue.length;
        int value = _queue[index];
        _popIndex++;

        return value;
    }

    public int peek(int index)
    {
        assert (index < size() && index >= 0) : "Out Of Bounds";

        int actualIndex = (_popIndex + index)%_queue.length;
        return _queue[actualIndex];
    }

    public int size()
    {
        return (_pushIndex - _popIndex);
    }

    public void clear()
    {
        _popIndex = 0;
        _pushIndex =0;
    }
}

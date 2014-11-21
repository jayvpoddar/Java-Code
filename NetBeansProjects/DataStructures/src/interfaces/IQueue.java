package interfaces;

/**
 *
 * @author jayp
 */
public interface IQueue
{
    boolean isFull();
    void enQueue(int value);
    boolean isEmpty();
    int deQueue();
    int peek(int index);
    int size();
    void clear();
}

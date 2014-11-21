package interfaces;

/**
 *
 * @author jayp
 */
public interface IStack
{
    void push(int value);
    boolean isEmpty();
    boolean isFull();
    int pop ();
    int peek (int index);
    int size ();
}

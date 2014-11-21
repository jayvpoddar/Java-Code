public class Node<E> {
	private E element;
	protected Node<E> next;
	
	public Node(E element)
	{
		this.element= element;
		this.next= null;
	}

	public Node(E element, Node<E> next)
	{
		this.element= element;
		this.next= next;
	}
	
	public E getElement()
	{
		return element;
	}
	
	public Node<E> next()
	{
		return next;
	}
	
	public void setElement(E element)
	{
		this.element= element;
	}
	
	public void setNext(Node<E> newNext)
	{
		next= newNext;
	}
}

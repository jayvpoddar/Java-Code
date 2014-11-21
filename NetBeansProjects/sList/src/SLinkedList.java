public class SLinkedList<E> {
	protected Node<E> head;
	protected int size;
	
	public SLinkedList()
	{
		head=null;
		size=0;
	}
	
	public void addElement(E element)
	{
		Node<E> n= new Node<E>(element);
		if (head==null)
		{
			head= n;
			head.next= null;
		}
		else
		{
			Node<E> temp= head;
			while(temp.next() != null)
			{
				temp=temp.next();
				
			}
			temp.setNext(n);
		}
	}
	
	public void printList()
	{
		Node<E> temp = head;
		while(temp != null)
		{
			System.out.println(temp.getElement());
                        temp=temp.next;
		}
	}
				
}
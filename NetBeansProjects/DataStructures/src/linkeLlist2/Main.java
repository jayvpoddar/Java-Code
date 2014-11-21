

package linkeLlist2;


/**
 *
 * @author jayp
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        LinkedList list=new LinkedList();
        int[] nums={1,2,3,5,7};
        for(int i:nums)
        {
            list.put(i);
        }
        list.insertSorted(4);
        Node temp=list._root;

        while(temp!=null)
        {
            System.out.println(temp._value);
            temp=temp._next;
        }

    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package maps;

/**
 *
 * @author jayp
 */
public class StudentMap
{
    private Student[] _array;
    private int _index=0;
    
    private class Student
    {
        int _rollNo;
        String _name;

        public Student(int rollNo, String name) {
            _rollNo = rollNo;
            _name = name;
        }

    }

    public StudentMap(int size)
    {
         _array=new Student[size] ;
         _index=0;
    }
    public void put(int rollNo,String name)
    {
        int i=0;
        for(;i<_index;i++)
        {
            if(rollNo >_array[i]._rollNo)
            {
                continue;
            }
            break;
        }
        insertAt(i,rollNo,name);
    }

    private void insertAt(int index ,int rollNo,String name)
    {
        assert index <= _index || index >= 0:"index is out of range";

        if(index == _index)
        {
            _array[_index++]=new Student(rollNo, name);

            return;
		}
		else
		{
			for(int i=_index; i > index; --i )
			{
				_array[i] = _array[i-1];
			}

			_array[index] = new Student(rollNo, name);
		}
		_index++;

    }

    public String get(int rollNo)
    {
        int index=binarySearchByIteration(rollNo,0,_index);
        return _array[index]._name;
    }

    private int binarySearchByIteration(int rollNo,int low,int high)
    {
        for(;;)
        {
            int mid =(low +high)/2;
            if(low > high)
            {
                return -1;
            }
            else if(_array[mid]._rollNo==rollNo)
            {
                return mid;
            }
            else if(_array[mid]._rollNo < rollNo)
            {
                low =mid+1;                
            }
            else
            {
                high=mid-1;                
            }
        }
    }
    private int binarySearchByRecursion(int rollNo,int low,int high)
    {
        
        int mid =(low +high)/2;
        if(low > high)
        {
            return -1;
        }
        else if(_array[mid]._rollNo==rollNo)
        {
            return mid;
        }
        else if(_array[mid]._rollNo< rollNo)
        {
           low =mid+1;
           return binarySearchByRecursion(rollNo,low,high);
        }
        else
        {
          high=mid-1;
          return binarySearchByRecursion(rollNo,low,high);
        }
       
    }
}

package arrayLists;


import interfaces.IList;
/**
 *
 * @author jayp
 */

public class SimpleArrayList  implements IList
{
	private int[] _data;
	
	public SimpleArrayList()
	{		
	}
	
	public void put(int value)
	{
		if(_data == null)
		{
			_data = new int[1];
			_data[0] = value;
			return;
		}
				
		int[] temp = new int[_data.length +1];
		for (int i = 0; i < _data.length; i++)
        {
            temp[i] = _data[i];
        }
        
        temp[_data.length] = value;
		_data = temp;
	}

    public void insertAt(int index, int value) 
	{
		if(index > _data.length || index < 0)
		{
                    //throw exception
		}
		if(_data == null)
		{
			_data = new int[1];
			_data[0] = value;
			return;
		}
		if(index == _data.length)
		{
            put (value);
		}
		else
		{
            int[] temp = new int[_data.length +1];
            for (int i = 0; i < _data.length; i++)
            {
                temp[i] = _data[i];
            }

			for(int i = _data.length; i > index; --i)
			{
				temp[i] = _data[i-1];
			}

			temp[index] = value;
            _data = temp;
		}
	}

    public void removeAt (int index)
    {
        if(index >= _data.length || index < 0)
		{
			//throw exception
            return;
		}

        int[] temp = new int[_data.length - 1];

        for (int i = 0; i < index; i++)
        {
            temp[i] = _data[i];
        }
        
        for (int i = index; i < _data.length-1; i++)
        {
            temp[i] = _data[i+1];
        }
        _data = temp;
    }

	public int size()
	{
		return _data.length;
	}
	
	public void clear()
	{
		_data = null;
	}
	
	public int get(int index)
	{
		if(_data == null || index >= size())
		{			
			//throw exception
		}
		
		return _data[index];
	}
	
	public int[] toArray()
	{
		return _data;
	}
}

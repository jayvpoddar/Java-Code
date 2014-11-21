package arrayLists;




import interfaces.IList;
/**
 *
 * @author jayp
 */

public class ExtendedArrayList implements IList
{
	private int[] _data;
	private int _increment = 10;
	private int _size = 0;

	public ExtendedArrayList (int increment)
	{
		this._increment = increment;
	}
    
    private void increaseSize ()
    {
        int[] temp = new int[_size + _increment];
        for (int i = 0; i < _size; i++)
        {
            temp[i] = _data[i];
        }
        _data = temp;
    }

	public void put(int value)
	{
		if(_data == null)
		{
			_data = new int[_increment];
			_data[_size] = value;
            _size++;
			return;
		}

		if(_size == _data.length )
		{
            increaseSize ();
		}

		_data[_size] = value;
        _size++;
	}

	public void insertAt(int index, int value) 
	{

		if(index > _size || index < 0)
		{
                    //	throw exception		
                }

		if(index == _size)
		{
            put (value);
            return;
		}
		else
		{
            if (_size == _data.length)
            {
                increaseSize ();
            }
            
			for(int i=_size; i > index; --i )
			{
				_data[i] = _data[i-1];
			}

			_data[index] = value;
		}
		_size++;

	}

    public void removeAt (int index)
    {
        if(index >= _size || index < 0)
		{
			//throw exception
            return;
		}
        
        for (int i = index; i < _size-1; i++)
        {
            _data[i] = _data[i+1];
        }
        _size--;
    }

	public int size()
	{
		return _size;
	}

	public void clear()
	{
        _data = null;
        _size = 0;
	}

	public int get(int index)
	{
		if(_data == null || index >= _size)
		{
			//throw exception
		}
		return _data[index];
	}

	public int[] toArray()
    {
        int[] temp = new int[_size];
        for(int i = 0; i < _size; i++)
        {
            temp[i] = _data[i];
        }
        return temp;
	}
}

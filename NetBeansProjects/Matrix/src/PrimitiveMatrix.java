/**
 * Implementation of Homework 2
 * 
 * @author Jay Poddar (jpoddar3)
 */
public class PrimitiveMatrix implements Matrix 
{
	private int _rows;
	private int _cols;
	private double[] _matrix;
	/**
	 * @param rows
	 *            number of rows in matrix
	 * @param cols
	 *            number of cols in matrix
	 */
	public PrimitiveMatrix(final int rows, final int cols) 
	{
		_rows=rows;
		_cols=cols;
		_matrix=new double[_rows*_cols];
	}

	@Override
	public void put(final double value, final int row, final int col)throws IndexOutOfBoundsException {
		if((row<=_rows&&col<=_cols)&&(row>=0||col>=0))
		{
			_matrix[row*_cols+col]=value;
		}
		throw new IndexOutOfBoundsException();
		
		
	}

	@Override
	public double get(int row, int col) throws IndexOutOfBoundsException {
		if((row>_rows||col>_cols)||(row<0||col<0))
		{
			throw new IndexOutOfBoundsException();
		}
		return _matrix[row*_cols+col];
		
	}

	@Override
	public Matrix add(final Matrix rhs) throws IllegalArgumentException {
		if(_rows==rhs.getRowDimension()&&_cols==rhs.getColumnDimension()){
			Matrix sum=new PrimitiveMatrix(_rows, _cols);
			for (int i = 0; i < _rows; i++) {
				for (int j = 0; j < _cols; j++) {
					sum.put(this.get(i, j)+rhs.get(i, j), i, j);
				}

			}
			return sum;
		}
		throw new IllegalArgumentException();
	
	}

	@Override
	public Matrix sub(final Matrix rhs) throws IllegalArgumentException {
		if(_rows==rhs.getRowDimension()&&_cols==rhs.getColumnDimension()){
			Matrix diff=new PrimitiveMatrix(_rows, _cols);
			for (int i = 0; i < _rows; i++) {
				for (int j = 0; j < _cols; j++) {
					diff.put(this.get(i, j)-rhs.get(i, j), i, j);
				}

			}
			return diff;
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Matrix mult(final Matrix rhs) throws IllegalArgumentException {
		
		if(rhs.getRowDimension()==_cols)
		{
			Matrix product=new PrimitiveMatrix(_rows, _cols);
			double sum;
			for (int i = 0; i < _rows; i++) 
	            { 
	                for (int j = 0; j < rhs.getColumnDimension(); j++) 
	                {
	                    sum=0.0;
	                    for (int k = 0; k < _cols; k++)
	                    {
	                    	sum+=this.get(i, k) *rhs.get(k, j);
	                    }
	                    product.put(sum,i,j);
	                }
	            }
	           return product;

	        }
	
		throw new IllegalArgumentException();
	}

	@Override
	public int getRowDimension() {
		return _rows;
	}

	@Override
	public int getColumnDimension() {
		return _cols;
	}
}

package myMath;
/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */

public class Monom implements function{
	private double _coefficient; 
	private int _power; 
	/**
	 * the constructor of monom. 
	 * @param a- A double type variable that represents the coefficient
	 * @param b- An int type variable that represents the coefficient
	 */
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	/**
	 * copy constructor of monom.
	 * @param ot- a monom variable
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	/**The function calculates the value at the point 'x'
	 * @param x- An double type 
	 */
	public double f(double x) 
	{
		return (_coefficient*(Math.pow(x, _power)));
	}
	/**
	 * The function divides the polynomial found in the class
	 */
	public void derivative ()
	{
		if (_power==0)//when the power it zero that mean that it is a regular number And therefore the derivative is zero
			this.set_coefficient(0);
		else
		{
			this.set_coefficient(this.get_coefficient()*this.get_power());
			this.set_power(this.get_power()-1);
		}
	}
	/**
	 * The function adds one monom to the monom (if their power are equal)
	 * @param ot- Object of Monom
	 */
	public void add (Monom ot)
	{
		if(ot==null)//if there is no monom
		{
			throw new RuntimeException("that it is a wrong monom");
		}
		else if(ot.get_power()!=ot.get_power())//if the power are different
		{
			throw new RuntimeException("its cant be add because the power is different");
		}
		else {
			if (this.get_power()==ot.get_power())
			{
				this.set_coefficient(this.get_coefficient()+ot.get_coefficient());
			}
		}
	}
	/**
	 * the function multiply one monom with onther monom
	 * @param ot-Object of Monom
	 */
	public void multiply (Monom ot)
	{
		if(ot==null)//if there is no monom
		{
			throw new RuntimeException("that it is a wrong monom");
		}
		else {
			this.set_coefficient(this.get_coefficient()*ot.get_coefficient());
			this.set_power(ot.get_power()+this.get_power());
		}
	}
	/**
	 * the function return a monom as a string 
	 */
	public String toString ()
	{
		String str="";
		if (this.get_power()==0)//If it's just a digit without X
			return (this._coefficient+str);
		if(this.get_power()==1)//If the X is not strong
			return (this.get_coefficient()+"x");
		return (this._coefficient+"x^"+this._power);//השאר
	}


	//****************** Private Methods and Data *****************

	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		if (p<0)//if the power is less than zero
		{
			throw new RuntimeException("that it is a wrong power");
		}
		this._power = p;
	}
	public double get_coefficient() {
		return _coefficient;
	}
	public int get_power() {
		return _power;
	}
	/**
	 * @param str-string that represant a monom
	 * The function accepts a String object
	 * return Returns a Monoam object.
	 */
	public Monom (String str)
	{
		if(str==null) {
			throw new RuntimeException("Got null string");
		}
		double a=0; 
		int b= 0;
		String in= str.toLowerCase();
		int ind_x= in.indexOf("x");
		if(ind_x<0) {//If it is only a number without X
			try 
			{
				a=Double.parseDouble(in);
			}
			catch (Exception e)
			{
				throw new RuntimeException("Gota wrong string");
			}
		}
		else 
		{
			//If it's a number with X
			String the_coefficient= in.substring(0, ind_x);
			try 
			{
				if (ind_x==0)//If the coefficient is 1
					a=1;
				else if(ind_x==1&&in.charAt(0)=='-')//If the coefficient is 1
					a=-1;
				else
					a=Double.parseDouble(the_coefficient);//If the coefficient is normal number
			}
			catch (Exception e)
			{
				throw new RuntimeException("Got a wrong string");
			}
			int ind_power= in.indexOf("^");//If there is a power
			if (ind_power<0)//if the power is 1 
			{
				b=1;
			}
			else
			{
				String the_power= in.substring(ind_power+1, in.length());
				try {
					b=Integer.parseInt(the_power);//Turn the string into a power number
				}
				catch (Exception e)
				{
					throw new RuntimeException("Gota wrong string");
				}
			}
		}
		this._coefficient=a;
		this._power=b;


	}
}

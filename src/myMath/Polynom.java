package myMath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{
	ArrayList<Monom> pol;
	private final Monom_Comperator compare=new Monom_Comperator ();
	/**The function calculates the value at the point 'x'
	 * @param Variable type double
	 * @return The polynomial value in point X
	 */
	public double f(double x) { 
		double sum=0;
		Iterator <Monom> it=this.iteretor();
		while(it.hasNext())//Go through each monom and activate the X function
		{
			sum=sum+it.next().f(x);
		}
		return sum;
	}
	/**
	 * the function add one polynom to onther
	 * @param p1 polynomial object 
	 * 
	 */
	@Override
	public void add(Polynom_able p1) {

		Iterator <Monom> it=p1.iteretor();
		while(it.hasNext())
		{
			this.add(new Monom (it.next()));
		}

	}
	@Override
	/**
	 * the function return the polynom as string
	 * @returns string that implements the polynom
	 */
	public String toString() {
		String str = "";
		Iterator<Monom> it = this.iteretor();
		if(it.hasNext())
			str=it.next().toString();//printïing the first monom
		while(it.hasNext()) 
		{
			Monom m1=new Monom (it.next());
			if (m1.get_coefficient()<0)//if the n=monom is negetive 
			{
				str=str+m1;
			}
			else//if the monom is positive
				str = str+"+"+m1;
		}
		return str;
	}

	@Override
	/**the function get a monom and add it to the polynom
	 * @param m1 a monom object
	 */
	public void add(Monom m1) {
		boolean added = true;
		Iterator<Monom> it = this.iteretor();
		Monom m2=new Monom (m1);
		while(it.hasNext()) 
		{
			Monom p =  it.next();
			if (p.get_power()==m1.get_power())//if the power is the same, add the monoms
			{
				p.add(m2);
				added=false;
			}
		}
		if(added) //if there is no monom with the power like m1 add p1 to the end
		{
			pol.add(m2);
		}
		pol.sort(compare);
	}
	/**the function substract one polynom from anther
	 * @param p1 Polynom_able object
	 */
	@Override
	public void substract(Polynom_able p1) {
		Polynom_able p2=new Polynom();//éCreates an empty polynomial with only one minus
		p2.add(new Monom(-1,0));
		Polynom_able copy=p1.copy();//Creates a copied polynomial
		copy.multiply(p2);//Multiply the copied polynomial by one minus
		this.add(copy);//Adds the copied polynomial to the polynomial class
	}
	/**
	 * the function multiply one polynom to other 
	 * @param p1 Polynom_able
	 */
	@Override
	public void multiply(Polynom_able p1) {
		Iterator<Monom> it1 = this.iteretor();
		Iterator<Monom> it2 = p1.iteretor();
		Polynom_able help=new Polynom ();
		Polynom_able copy_polynom=this.copy();
		Iterator<Monom> it3 = copy_polynom.iteretor();
		boolean flag=false;
		while(it2.hasNext()) {
			Monom a= new Monom(it2.next());
			while(it1.hasNext()&&it3.hasNext()) 
			{
				if (flag==false)
					it1.next().multiply(a);//This means that we are in the first round and therefore we will double the monom itself
				else//If we finish the first round and the polynomial itself has changed we will create a polynomial which is a copy of the original. Let's take the monos in order and copy them to another monom.
					//We will multiply what we need and add to the auxiliary polynomial. In the end we add the final result to the original polynomial
				{
					Monom b=new Monom (it3.next());	
					Monom c=new Monom (b);
					c.multiply(a);
					help.add(c);
				}

			}
			flag=true;
			it3=copy_polynom.iteretor();
			it1 = this.iteretor();
		}
		this.add(help);
	}
	/**
	 * @param Polynom_able p1
	 * @return true if the polynoms have the same monoms else false
	 */
	@Override
	public boolean equals(Polynom_able p1) {
		// TODO Auto-generated method stub
		Iterator<Monom> it = p1.iteretor();
		Iterator<Monom> it2 = this.iteretor();
		while (this.iteretor().hasNext()&&it.hasNext())
		{
			Monom m1= new Monom (it2.next());
			System.out.println(m1);
			Monom m2=new Monom (it.next());
			System.out.println(m2);
			if(m1.get_coefficient()!=m2.get_coefficient())//checks if the coefficient the same
			{
				return false;
			}
			if(m2.get_power()!=m2.get_power())//checks if the power the same
			{
				return false;
			}
		}
		if (it2.hasNext()||it.hasNext())//If the start of the two polynomials is the same but one of them is longer than the other
			return false;
		return true;
	}
	/**
	 * @return true if all the coffient of the monoms are zero, else false
	 * 
	 */
	@Override
	public boolean isZero() {
		// TODO Auto-generated method stub
		if (pol.size()==0)
		{
			return true;
		}
		Iterator<Monom> it = this.iteretor();
		while(it.hasNext())
		{
			if(it.next().get_coefficient()!=0)
				return false;
		}
		return true;
	}
	/**
	 * @param x0 x1 eps
	 * @return Cut point with x-axis
	 */
	@Override
	public double root(double x0, double x1, double eps) 
	{
		double x2= x0;
		while(Math.abs(x1-x0) > eps) {
			x2 = (x1 + x0)/2;
			if(f(x1) * f(x2) > 0) 	
				x0 = x2;
			else	
				x1 = x2;
		}
		return x0;
	}

	/**
	 * A constructor that creates a copied polynomial
	 */
	@Override
	public Polynom_able copy() {
		String str=this.toString();
		Polynom_able copy1=new Polynom (str);
		return copy1;
	}
	@Override
	/**
	 * The function divides the polynomial
	 */
	public Polynom_able derivative() {
		// TODO Auto-generated method stub
		Iterator<Monom> it = this.iteretor();
		Polynom_able copy=new Polynom ();
		while(it.hasNext())
		{
			Monom copy1=new Monom (it.next());
			copy1.derivative();
			System.out.println(copy1);
			copy.add(copy1);
		}
		System.out.println(copy);
		return copy;
	}
	/**
	 * @papram x0, x1, eps-
	 * The function returns the area over the X axis between the two points x0 and x1
	 */
	@Override
	public double area(double x0, double x1, double eps) {
		// TODO Auto-generated method stub
		double length=(x1-x0)/eps;//The amount of squares
		int i=0;
		double sum=0;
		while (i<length)
		{
			if(f(x0+i*eps)>0)//Check if this is over the X axis
			{
				sum=f((x0+i*eps))*eps+sum;//Trigger the function to get the height double the length and the amount that went out to the flaps

			}
			i++;
		}
		return sum;
	}

	@Override
	public Iterator<Monom> iteretor() {
		// TODO Auto-generated method stub

		return pol.iterator();
	}

	// ********** add your code below ***********
	/**
	 * An empty constructor that initializes a polynomial
	 */
	public Polynom () 
	{
		pol=new ArrayList<Monom>();
	}
	/**
	 * A constructor action that gets a string and turns it into a polynomial object
	 * @param str- 
	 */
	public Polynom (String str)
	{
		pol=new ArrayList<Monom>();
		String strlow=str.toLowerCase();
		int i;
		strlow= strlow.replaceAll("-", "+-");//Each instance of '-' is replaced with '+ -'
		String [] arr_monom=strlow.split("\\+");
		if(arr_monom[0].equals(""))//if the string started with minus the first place will be empty
		{
			i=1;
		}
		else
			i=0;
		for (int j=i;j<arr_monom.length;j++)
		{
			Monom a=new Monom(arr_monom[j]);//Using a monom auxiliary function
			this.add(a);
		}
	}
	/**
	 * the function get x0 and x1 and calculate the area under the X axis
	 * @param x0
	 * @param x1
	 * @param eps
	 * @return
	 */
	public double area2(double x0, double x1, double eps) {
		// TODO Auto-generated method stub
		double length=(x1-x0)/eps;//The amount of squares
		int i=0;
		double sum=0;
		while (i<length)
		{
			if(f(x0+i*eps)<0)//Check if this is over the X axis
			{
				sum=f(1*(x0+i*eps))*eps+sum;//Trigger the function to get the height double the length and the amount that went out to the flaps
			}
			i++;
		}
		return -1*sum;
	}
}

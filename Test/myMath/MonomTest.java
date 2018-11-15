package myMath;
//
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MonomTest {

	@Test
	void testMonomDoubleInt() {
		Monom a= new Monom(2,3);
		assertEquals(2,a.get_coefficient());
		assertEquals(3,a.get_power());
		boolean didThrew= false;//checking if it built a monom with negative power
		try {
			a= new  Monom(2,-1); 
		}
		catch (RuntimeException e)
		{
			didThrew= true;	 
		}
		if(!didThrew) {//if it built its not good
			fail("exepted a negativ power");
		}
	}
	@Test
	void testMonomMonom() {
		Monom a= new Monom(5,3);
		Monom b= new Monom(a);
		assertEquals(5,b.get_coefficient());//checking the value 
		assertEquals(3,b.get_power());
		assertNotSame(a,b);//checking that it is not just the same reference
	}
	@Test
	void testF() {
		Monom a= new Monom(5,3);
		double ans=a.f(5);//checking if it is calculate it right
		assertEquals(625,ans);
	}

	@Test
	void testDerivative() {
		Monom a= new Monom(5,3);
		a.derivative();
		assertEquals(15,a.get_coefficient());//checking if the derative is right
		assertEquals(2,a.get_power());
	}

	@Test
	void testAdd() {
		Monom a= new Monom(2,3);
		Monom b= new Monom(5,3);
		a.add(b);
		assertEquals(7,a.get_coefficient());
		assertEquals(3,a.get_power());
		boolean didThrew= false;
		Monom c= new Monom(5,6);
		Monom d=null;
		try {//it try to add a monom with different power
			a.add(c);
			b.add(d);
		}
		catch(RuntimeException e)//if it didnt Succeeded thats good
		{
			didThrew= true;
		}
		if(!didThrew) {//if it Succeeded it is not good and the test will fail
			fail("can't added monoms with difrent powers");
		}
	}

	@Test
	void testMultiply() {
		Monom a= new Monom(2,3);
		Monom b= new Monom(5,3);
		a.multiply(b);
		assertEquals(10,a.get_coefficient());
		assertEquals(6,a.get_power());
		b=null;
		boolean didThrew=false;//it try to multiply a null monom
		try {
			a.multiply(b);
		}
		catch(RuntimeException e)//if it didnt Succeeded that good
		{
			didThrew= true;
		}
		if(!didThrew) {//if it build a monom with multiply null monom its wrong
			fail("can't added monoms with difrent powers");
		}
	}

	@Test
	void testToString() {
		String test=null;
		boolean didThrew=false;
		try {
			Monom a= new Monom(test);//it tryto build  a monom with a null string
		}
		catch(RuntimeException e)
		{
			didThrew= true;//its good if its didnt Succeeded
		}
		if(!didThrew) {
			fail("can't added monoms with difrent powers");//if it build it the test will fail
		}
		Monom b= new Monom("3x^2");
		assertEquals(3,b.get_coefficient());
		assertEquals(2,b.get_power());

	}

	
}

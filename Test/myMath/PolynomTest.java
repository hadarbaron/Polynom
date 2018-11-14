package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PolynomTest {
	static Polynom_able p1=null;
	static Polynom_able p2=null;
	static Polynom_able p3=null;
	static Polynom_able p4=null;
	static Polynom_able p5=null;
	static Polynom_able p6=null;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		p1=new Polynom ("3x+6-x^2+8x^5");
		p2=new Polynom();
		p2.add (new Monom (-2,5));
		p2.add(new Monom (-3,8));
		p2.add(new Monom (1,2));
		p2.add((new Monom (-7,0)));
		p3=p2.copy();
		p4=new Polynom();
		p5=new Polynom();
		p6=new Polynom ("5x-6");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testF() {
		assertEquals(1950,p1.f(3) );//בודק על פונקציה שעשויהי מסטרינג
		assertEquals(-20167,p2.f(3) );//בודק על פונקציה שעשוייה מהוספת מונומים
		assertEquals(-20167,p3.f(3) );//בודק על פונקציה שמעתיקה פולינום אחר
		assertEquals(0,p4.f(3) );
	}

	@Test
	void testAddPolynom_able() {
		Polynom_able tester=new Polynom ();
		tester.add(p6);
		assertEquals(p6.toString(),tester.toString());


	}

	@Test
	void testToString() {
		assertEquals("-3.0x^8-2.0x^5+1.0x^2-7.0",p2.toString() );
		assertEquals(p5.toString(),p4.toString() );//בוודקת האם כשבונים פולינום ריק הוא מדפיס בטעות משהו

	}

	@Test
	void testAddMonom() {
		Polynom_able tester=new Polynom();
		tester.add(new Monom (5,1));
		tester.add(new Monom (-6,0));
		assertEquals(p6.toString(),tester.toString());
		boolean flag=true;//בדיקה אם הוספנ מונום שלא אפשרי להוספה 
		try
		{
			tester.add(new Monom (4,-8));
		}
		catch (Exception e)
		{
			flag=false;
		}
		if (flag==true)
		{
			fail("Not yet implemented");
		}
	}

	@Test
	void testSubstract() {
		Polynom_able tester = new Polynom("2x-3x^2+x^3");
		Polynom_able tester2 = new Polynom("-0.2+2.1x-x^3");
		tester.substract(tester2);
		assertEquals("2.0x^3-3.0x^2-0.10000000000000009x+0.2",tester.toString());
	}

	@Test
	void testMultiply() {
		Polynom_able tester3 = new Polynom("2x-3x^2+x^3");
		Polynom_able tester4 = new Polynom("-0.2+2.1x-x^3");
		tester3.multiply(tester4);
		Polynom_able empty=new Polynom ();
		Polynom_able empty2=new Polynom ("x");
		tester3.multiply(empty);
		empty2.multiply(empty);
		assertEquals("1.0x",empty2.toString());
		assertEquals("-1.0x^6+3.0x^5+0.10000000000000009x^4-6.500000000000001x^3+4.800000000000001x^2-0.4x",tester3.toString());
	}

	@Test
	void testEqualsPolynom_able() {
		assertEquals(p3.toString(),p2.toString());//בדיקה על הפולינומים שבנינו בתחילת הטסט אם הערכים שווים
		Polynom_able a=new Polynom ("3X");
		Polynom_able b=new Polynom ("3x");//אם יש בעיות כשהאותיות קטנות
		assertEquals(a.toString(),b.toString());
		assertNotSame(a,b);//האיקוול בודק ערכים ולכן אנו בודקים שהוא לא בודק מצביעים אלא רק ערכים
	}

	@Test
	void testIsZero() {
		Polynom_able zero=new Polynom("0");
		assertEquals("0.0",zero.toString());
		assertEquals(p4.toString(),p5.toString());//אם הפולינום ריק
	}

	@Test
	void testRoot() { 

		assertEquals(99.99923706054688,p1.root(-100, 100, 0.001));


	}

	@Test
	void testCopy() {
		assertEquals(p2.toString(),p3.toString());//לבדוק אם העתיק 
		assertNotSame(p2,p3);//לבדוק שלא העתיק העתקה של רפרנס

	}

	@Test
	void testDerivative() {
		Polynom_able a=p1.derivative();
		assertEquals("40.0x^4-2.0x+3.0",a.toString());
	}

	@Test
	void testArea() {
		assertEquals(20734.424916499993,p1.area(0, 5, 0.01));
	}
	@Test
	void testPolynom() {

		fail("Not yet implemented");
	}

	@Test
	void testPolynomString() {
		boolean flag=true;
		try {
			Polynom_able tester=new Polynom ("-3x+x^4-x^-2");//the power is negetive
		}
		catch(Exception e )
		{
			flag=false;
		}
		if(flag==true)
			fail("Not yet implemented");

	}

}

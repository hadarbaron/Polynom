package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MonomTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
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
	void testMonomDoubleInt() {
		Monom a=new Monom (5,2);
		assertEquals (5,a.get_coefficient());
		assertEquals (2,a.get_power());
		Monom b=new Monom (4,-2);
		assertNotEquals(-2,b.get_power());
		
	}


@Test
void testMonomMonom() {
	fail("Not yet implemented");
}

@Test
void testF() {
	fail("Not yet implemented");
}

@Test
void testDerivative() {
	fail("Not yet implemented");
}

@Test
void testAdd() {
	fail("Not yet implemented");
}

@Test
void testMultiply() {
	fail("Not yet implemented");
}

@Test
void testToString() {

	fail("Not yet implemented");
}

@Test
void testGet_coefficient() {
	fail("Not yet implemented");
}

@Test
void testGet_power() {
	fail("Not yet implemented");
}

@Test
void testMonomString() {
	fail("Not yet implemented");
}

}

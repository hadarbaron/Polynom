package myMath;
import java.util.Comparator;
/**
 * The function get tow Monoms and compare between the power of them.
 * @return -1 if obj1 power is bigger
 * @return 1 if obj2 power is bigger
 * @return 0 if the powers the same
 * @author shira and hadar.
 *
 */
public class Monom_Comperator implements Comparator <Monom>{
	public int compare(Monom obj1, Monom obj2)
	{
		if (obj1.get_power()>obj2.get_power())
			return -1;
		else if (obj1.get_power()<obj2.get_power())
			return 1;
		else return 0;
	}
}

package comcast_rmgYantra_framework_genericUtility;

import java.util.Random;

public class JavaUtility {
	public int getRandonNumber() {
		Random random = new Random();
		int number = random.nextInt(5000);
		return number;
	}
}

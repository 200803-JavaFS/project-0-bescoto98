import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.AccountDAO;
import com.revature.models.Account;
import com.revature.services.BasicServices;

public class BasicServicesTests {
	public static BasicServices basic;
	public static Account testingAccount;
	public static AccountDAO aDAO;
	public static double originalBalance;
	public static String originalStatus;
	
	@BeforeClass
	public static void setBasics() {
		basic = new BasicServices();
		aDAO = new AccountDAO();
		
		testingAccount = aDAO.findByAcctID(1);
		
		originalBalance = testingAccount.getBalance();
		originalStatus = testingAccount.getStatus();
		
		testingAccount.setBalance(500);
		
		aDAO.updateAccount(testingAccount);
		
	}
	
	@Before
	public void setValues() {
		
		testingAccount.setBalance(500);
		aDAO.updateAccount(testingAccount);
	}
	
	@Test
	public void testRealLogin() {
		System.out.println("Testing login with these credentials\n"
				+ "username: ruiz68 \npassword: root");
		boolean loggedIn = basic.login(0,"ruiz68","root");
		assertTrue(loggedIn);
	}
	
	@Test
	public void testInvalidLogin() {
		System.out.println("Testing login with these credentials\n"
				+ "username: hacker \npassword: root");
		boolean loggedIn = basic.login(1,"gosteve","root");
		assertFalse(loggedIn);
		
	}
	
	@Test
	public void testDeposit() {
		System.out.println("Testing deposit.");
		basic.changeBalance(false,testingAccount,50);
		assertTrue(testingAccount.getBalance()==550);
		
	}
	
	@Test
	public void testWithdraw() {
		
		System.out.println("Testing withdraw where the value requested is greater than the balance.");
		basic.changeBalance(true,testingAccount,1000);
		assertTrue(testingAccount.getBalance()==500);
		
	}
	
	@AfterClass
	public static void clearBasics() {
		basic = null;
		
		testingAccount.setBalance(originalBalance);
		testingAccount.setStatus(originalStatus);
		
		aDAO.updateAccount(testingAccount);
		
		aDAO = null;
		testingAccount = null;
	}
}

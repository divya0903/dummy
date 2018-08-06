package com.cg.account.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;

import com.cg.account.bean.Account;
import com.cg.account.exception.AccountException;
import com.cg.account.service.AccountService;
import com.cg.account.service.IAccountService;

public class TestCases {

	Account acc = new Account();
	IAccountService service = new AccountService();

	/*
	 * @Before public void init() { // TODO Auto-generated method stub service = new
	 * AccountService(); acc = new Account(); }
	 */

	@Test
	// Given wrong mobile number.
	public void testAccountMobile() {

		acc.setName("Deepraj");
		acc.setMobile("111111");
		acc.setEmail("deepraj@gmail.com");
		acc.setBalance(500.0);
		acc.setModDate("" + new Date());

		try {
			if (service.validateAccount(acc))
				service.createAccount(acc);
		} catch (AccountException e) {
			// TODO: handle exception
			// System.out.println("wrong mobile:"+e.getMessage());
			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}
	}

	@Test
	// Given wrong name i.e. contains numbers
	public void testAccountName() {

		acc.setName("1232456");
		acc.setMobile("1234567890");
		acc.setEmail("deepraj@gmail.com");
		acc.setBalance(500.0);
		acc.setModDate("" + new Date());

		try {
			if (service.validateAccount(acc))
				service.createAccount(acc);
		} catch (AccountException e) {
			// TODO: handle exception
			// System.out.println("name with numbers:"+e.getMessage());
			assertEquals("Name must contain only alphabets", e.getMessage());
		}
	}

	@Test
	// given an empty name string
	public void testAccountName1() {

		acc.setName("");
		acc.setMobile("1234567890");
		acc.setEmail("deepraj@gmail.com");
		acc.setBalance(500.0);
		acc.setModDate("" + new Date());

		try {
			if (service.validateAccount(acc))
				service.createAccount(acc);
		} catch (AccountException e) {
			// TODO: handle exception
			// System.out.println("empty name:"+e.getMessage());
			assertEquals("Name cannot be empty", e.getMessage());
		}
	}

	@Test
	public void testAccountEmail() {

		acc.setName("Deepraj");
		acc.setMobile("1234567890");
		acc.setEmail("deeprajgmail.com");
		acc.setBalance(500.0);
		acc.setModDate("" + new Date());

		try {
			if (service.validateAccount(acc))
				service.createAccount(acc);
		} catch (AccountException e) {
			// TODO: handle exception
			// System.out.println("wrong id:"+e.getMessage());
			assertEquals("Invalid Email ID", e.getMessage());
		}
	}

	@Test
	public void testAccountBalance() {

		acc.setName("Deepraj");
		acc.setMobile("1234567890");
		acc.setEmail("dee@cg.com");
		acc.setBalance(-500);
		acc.setModDate("" + new Date());
		// System.out.println(acc.getBalance());
		try {

			if (service.validateAccount(acc))
				service.createAccount(acc);
		} catch (AccountException e) {
			// TODO: handle exception
			// System.out.println("negative balance:"+e.getMessage());
			assertEquals("Balance must be a number greater than zero", e.getMessage());
		}
	}

	@Test
	public void testCreateAccount() {

		acc.setName("Deepraj");
		acc.setMobile("1111111111");
		acc.setEmail("dee@cg.com");
		acc.setBalance(500);
		acc.setModDate("" + new Date());

		try {
			String mobile = service.createAccount(acc);
			assertNotNull(mobile);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			// System.out.println(e.getMessage());
		}

	}

	@Test
	public void testCreateAccount1() {

		acc.setName("Hari");
		acc.setMobile("2222222222");
		acc.setEmail("haha@cg.com");
		acc.setBalance(15000);
		acc.setModDate("" + new Date());

		try {
			String mobile = service.createAccount(acc);
			assertNotNull(mobile);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			// System.out.println(e.getMessage());
		}

	}

	@Test
	public void testCreateAccount2() {

		acc.setName("Radha");
		acc.setMobile("3333333333");
		acc.setEmail("radha@cg.com");
		acc.setBalance(2000);
		acc.setModDate("" + new Date());

		try {
			String mobile = service.createAccount(acc);
			assertNotNull(mobile);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			// System.out.println(e.getMessage());
		}

	}

	@Test
	public void testExistingAccount() {
		acc.setName("Deepraj");
		acc.setMobile("1111111111");
		acc.setEmail("dee@cg.com");
		acc.setBalance(500);
		acc.setModDate("" + new Date());
		try {
			service.createAccount(acc);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			assertEquals("", e.getMessage());
		}

	}

	@Test
	public void testShowBalanceNonExistingAccount() {
		String mobile = "1212121212";
		try {
			service.showBalance(mobile);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			assertEquals("Account does not exist", e.getMessage());
		}
	}

	@Test
	public void testShowBalanceExistingAccountWrongNumber() {
		String mobile = "11111111";
		try {
			service.showBalance(mobile);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}

	}

	@Test
	public void testShowBalanceExistingName() {
		String mobile = "1111111111";
		try {
			acc = service.printTransaction(mobile);
			service.showBalance(acc.getMobile());
			assertEquals("Deepraj", acc.getName());
		} catch (AccountException e) {
			// TODO Auto-generated catch block

		}

	}

	@Test
	public void testShowBalanceExisting() {
		String mobile = "1111111111";
		try {
			// acc = service.getAccountDetails(acc.getMobile());
			// System.out.println(service.showBalance(acc.getMobile()));
			assertEquals(2150.0, service.showBalance(mobile), 0.5);
		} catch (AccountException e) {
			// TODO Auto-generated catch block

		}

	}

	@Test
	public void testDepositCheckMobile() {
		String mobile = "1111111111";
		double depositAmt = 1500.0;
		try {
			if (service.validateMobile(mobile)) {
				// acc = service.getAccountDetails(acc.getMobile());
				double bal = service.deposit(mobile, depositAmt);
				assertNotNull(bal);
			}
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}

	}

	@Test
	public void testDepositCheckBalance() {
		String mobile = "1111111111";
		double depositAmt = -1500.0;
		try {
			if (service.validateBalance(depositAmt)) {
				// acc = service.getAccountDetails(acc.getMobile());
				double bal = service.deposit(mobile, depositAmt);
				assertNotEquals(service.printTransaction(mobile).getBalance(), bal);
			}
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			assertEquals("Balance must be a number greater than zero", e.getMessage());
		}

	}

	@Test
	public void testDepositCorrect() {
		String mobile = "1111111111";
		double depositAmt = 1500.0;
		try {
			if (service.validateBalance(depositAmt)) {
				double bal = service.deposit(mobile, depositAmt);
				assertNotNull(bal);
			}
		} catch (AccountException e) {
			// TODO Auto-generated catch block

		}
	} // Account balance is Low

	@Test
	public void testWithdrawCheckMobile() {
		String mobile = "1111111111";
		double withdrawAmt = 1500.0;
		try {
			if (service.validateMobile(mobile)) {
				double bal = service.withdraw(mobile, withdrawAmt);
				assertNotNull(bal);
			}
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}

	}

	@Test
	public void testWithdrawCheckBalance() {
		String mobile = "1111111111";
		double amt = -1500.0;
		try {
			if (service.validateBalance(amt)) {
				// acc = service.getAccountDetails(acc.getMobile());
				double bal = service.withdraw(mobile, amt);
				assertNotEquals(service.printTransaction(mobile).getBalance(), bal);
			}
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			assertEquals("Balance must be a number greater than zero", e.getMessage());
		}

	}

	@Test
	public void testWithdrawMoreAmount() {
		String mobile = "1111111111";
		double withdrawAmt = 1500.0;
		try {
			double bal = service.withdraw(mobile, withdrawAmt);
			assertNotEquals(service.printTransaction(mobile).getBalance(), bal);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			assertEquals("Account balance is Low", e.getMessage());

		}
	}

	@Test
	public void testWithdrawCorrect() {
		String mobile = "1111111111";
		double withdrawAmt = 20000.0;
		try {
			double bal = service.withdraw(mobile, withdrawAmt);
			assertNotEquals(service.printTransaction(mobile).getBalance(), bal);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			assertEquals("Account balance is Low", e.getMessage());

		}
	}

	@Test
	public void testFundTransferMobile1Valid() {
		Account acc1 = new Account();
		Account acc2 = new Account();
		acc1.setMobile("333333");
		acc2.setMobile("2222222222");
		double amount = 100;
		try {
			service.fundTransfer(acc1.getMobile(), acc2.getMobile(), amount);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}
	}

	@Test
	public void testFundTransferMobile2Valid() {
		Account acc1 = new Account();
		Account acc2 = new Account();
		acc1.setMobile("3333333333");
		acc2.setMobile("222222");
		double amount = 100;
		try {
			service.fundTransfer(acc1.getMobile(), acc2.getMobile(), amount);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}
	}

	@Test
	public void testFundTransferAmountValidation() {
		Account acc1 = new Account();
		Account acc2 = new Account();
		acc1.setMobile("3333333333");
		acc2.setMobile("2222222222");
		double amount = -100;
		try {
			service.fundTransfer(acc1.getMobile(), acc2.getMobile(), amount);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			assertEquals("Amount must be a number greater than zero", e.getMessage());
		}
	}

	@Test
	public void testFundTransferMoreAmount() {
		Account acc1 = new Account();
		Account acc2 = new Account();
		acc1.setMobile("3333333333");
		acc2.setMobile("2222222222");
		double amount = 1500;
		try {
			service.fundTransfer(acc1.getMobile(), acc2.getMobile(), amount);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			assertEquals("Account does not exist", e.getMessage());
		}
	}

	@Test
	public void testFundTransferNonExistingAmount() {
		Account acc1 = new Account();
		Account acc2 = new Account();
		acc1.setMobile("3333333333");
		acc2.setMobile("1111111111");
		double amount = 150;
		try {
			service.fundTransfer(acc1.getMobile(), acc2.getMobile(), amount);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			assertEquals("Account does not exist", e.getMessage());
		}
	}

	@Test
	public void testFundTransferExistingAmount() {
		Account acc1 = new Account();
		Account acc2 = new Account();
		acc1.setMobile("3333333333");
		acc2.setMobile("2222222222");
		double amount = 150;
		try {
			service.fundTransfer(acc1.getMobile(), acc2.getMobile(), amount);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testPrintTransaction() {
		acc = new Account();
		acc.setMobile("1111111111");
		try {
			Account ac = service.printTransaction(acc.getMobile());
			assertNotNull(ac);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

}

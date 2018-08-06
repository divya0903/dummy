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

	@Test

	public void testAccountMobile() {

		acc.setName("Divya");
		acc.setMobile("111111");
		acc.setEmail("divya@gmail.com");
		acc.setBalance(500.0);
		acc.setModDate("" + new Date());

		try {
			if (service.validateAccount(acc))
				service.createAccount(acc);
		} catch (AccountException e) {

			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}
	}

	@Test

	public void testAccountName() {

		acc.setName("1232456");
		acc.setMobile("1234567890");
		acc.setEmail("divya@gmail.com");
		acc.setBalance(500.0);
		acc.setModDate("" + new Date());

		try {
			if (service.validateAccount(acc))
				service.createAccount(acc);
		} catch (AccountException e) {

			assertEquals("Name must contain only alphabets", e.getMessage());
		}
	}

	@Test

	public void testAccountName1() {

		acc.setName("");
		acc.setMobile("1234567890");
		acc.setEmail("divya@gmail.com");
		acc.setBalance(500.0);
		acc.setModDate("" + new Date());

		try {
			if (service.validateAccount(acc))
				service.createAccount(acc);
		} catch (AccountException e) {

			assertEquals("Name cannot be empty", e.getMessage());
		}
	}

	@Test
	public void testAccountEmail() {

		acc.setName("Divya");
		acc.setMobile("1234567890");
		acc.setEmail("divyagmail.com");
		acc.setBalance(500.0);
		acc.setModDate("" + new Date());

		try {
			if (service.validateAccount(acc))
				service.createAccount(acc);
		} catch (AccountException e) {

			assertEquals("Invalid Email ID", e.getMessage());
		}
	}

	@Test
	public void testAccountBalance() {

		acc.setName("Divya");
		acc.setMobile("1234567890");
		acc.setEmail("div@cg.com");
		acc.setBalance(-500);
		acc.setModDate("" + new Date());

		try {

			if (service.validateAccount(acc))
				service.createAccount(acc);
		} catch (AccountException e) {

			assertEquals("Balance must be a number greater than zero", e.getMessage());
		}
	}

	@Test
	public void testCreateAccount() {

		acc.setName("Divya");
		acc.setMobile("8121414689");
		acc.setEmail("div@cg.com");
		acc.setBalance(500);
		acc.setModDate("" + new Date());

		try {
			String mobile = service.createAccount(acc);
			assertNotNull(mobile);
		} catch (AccountException e) {

			// System.out.println(e.getMessage());
		}

	}

	@Test
	public void testCreateAccount1() {

		acc.setName("Preethi");
		acc.setMobile("2222222222");
		acc.setEmail("preethi@cg.com");
		acc.setBalance(15000);
		acc.setModDate("" + new Date());

		try {
			String mobile = service.createAccount(acc);
			assertNotNull(mobile);
		} catch (AccountException e) {

			// System.out.println(e.getMessage());
		}

	}

	@Test
	public void testCreateAccount2() {

		acc.setName("Jeevana");
		acc.setMobile("3333333333");
		acc.setEmail("jeevana@cg.com");
		acc.setBalance(2000);
		acc.setModDate("" + new Date());

		try {
			String mobile = service.createAccount(acc);
			assertNotNull(mobile);
		} catch (AccountException e) {

			// System.out.println(e.getMessage());
		}

	}

	@Test
	public void testExistingAccount() {
		acc.setName("Divya");
		acc.setMobile("1111111111");
		acc.setEmail("dee@cg.com");
		acc.setBalance(500);
		acc.setModDate("" + new Date());
		try {
			service.createAccount(acc);
		} catch (AccountException e) {

			assertEquals("", e.getMessage());
		}

	}

	@Test
	public void testShowBalanceNonExistingAccount() {
		String mobile = "1212121212";
		try {
			service.showBalance(mobile);
		} catch (AccountException e) {

			assertEquals("Account does not exist", e.getMessage());
		}
	}

	@Test
	public void testShowBalanceExistingAccountWrongNumber() {
		String mobile = "11111111";
		try {
			service.showBalance(mobile);
		} catch (AccountException e) {

			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}

	}

	@Test
	public void testShowBalanceExistingName() {
		String mobile = "1111111111";
		try {
			acc = service.printTransaction(mobile);
			service.showBalance(acc.getMobile());
			assertEquals("Divya", acc.getName());
		} catch (AccountException e) {

		}

	}

	@Test
	public void testDepositCheckMobile() {
		String mobile = "81214146";
		double depositAmt = 1500.0;
		try {
			if (service.validateMobile(mobile)) {

				double bal = service.deposit(mobile, depositAmt);
				assertNotNull(bal);
			}
		} catch (AccountException e) {

			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}

	}

	@Test
	public void testDepositCheckBalance() {
		String mobile = "1111111111";
		double depositAmt = -1500.0;
		try {
			if (service.validateBalance(depositAmt)) {

				double bal = service.deposit(mobile, depositAmt);
				assertNotEquals(service.printTransaction(mobile).getBalance(), bal);
			}
		} catch (AccountException e) {

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

		}
	}

	@Test
	public void testWithdrawCheckMobile() {
		String mobile = "81214146";
		double withdrawAmt = 1500.0;
		try {
			if (service.validateMobile(mobile)) {
				double bal = service.withdraw(mobile, withdrawAmt);
				assertNotNull(bal);
			}
		} catch (AccountException e) {

			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}

	}

	@Test
	public void testWithdrawCheckBalance() {
		String mobile = "1111111111";
		double amt = -1500.0;
		try {
			if (service.validateBalance(amt)) {

				double bal = service.withdraw(mobile, amt);
				assertNotEquals(service.printTransaction(mobile).getBalance(), bal);
			}
		} catch (AccountException e) {

			assertEquals("Balance must be a number greater than zero", e.getMessage());
		}

	}

	@Test
	public void testWithdrawMoreAmount() {
		String mobile = "8121414689";
		double withdrawAmt = 15000.0;
		try {
			double bal = service.withdraw(mobile, withdrawAmt);
			assertNotEquals(service.printTransaction(mobile).getBalance(), bal);
		} catch (AccountException e) {

			assertEquals("Account balance is Low", e.getMessage());

		}
	}

	@Test
	public void testWithdrawCorrect() {
		String mobile = "8121414689";
		double withdrawAmt = 500.0;
		try {
			double bal = service.withdraw(mobile, withdrawAmt);

		} catch (AccountException e) {

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

			System.out.println(e.getMessage());
		}

	}

}

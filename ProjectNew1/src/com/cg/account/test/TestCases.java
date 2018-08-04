package com.cg.account.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cg.account.bean.Account;
import com.cg.account.exception.AccountException;
import com.cg.account.service.AccountService;
import com.cg.account.service.IAccountService;

public class TestCases {
	
	Account acc=new Account();
	
	IAccountService service;
	
	
	@Before
	public void init()
	{
		service = new AccountService();
	}

	@Test
	public void testNullName()
	{
		acc.setName(" ");
		acc.setMobile("9848746997");
		acc.setEmail("preethi@gmail.com");
		acc.setBalance(42000);
		try {
			if(service.validateNullName(acc.getName()))
			{
				System.out.println("valid name");
			}
		} catch (AccountException e) {
			System.out.println(e.getMessage());
			assertEquals("name cannot be null", e.getMessage());
		}
	}
	
	@Test
	public void testName() {
		
		acc.setName("preethi");
		acc.setMobile("9848746997");
		acc.setEmail("preethi@gmail.com");
		acc.setBalance(42000);
		
		try {
			if(service.validateName(acc.getName()))
			{
				System.out.println("valid name");
			}
		} catch (AccountException e) {
			System.out.println(e.getMessage());
			assertEquals("name should start with capital letter and should contain only alphabets ",e.getMessage());
		}
	}
	@Test
	public void testMobile() {
		acc=new Account();
		acc.setName("preethi");
		acc.setMobile("9848746");
		acc.setEmail("preethi@gmail.com");
		acc.setBalance(42000);
		
		try {
			if(service.validateMobile(acc.getMobile()))
			{
				System.out.println("valid phone number");
			}
		} catch (AccountException e) {
			System.out.println(e.getMessage());
			assertEquals("Mobile Number Should Contain 10 Digits ",e.getMessage());
		}
	}
	
	
	@Test
	public void testEmail() {
		
		acc.setName("Preethi");
		acc.setMobile("9848746997");
		acc.setEmail("preethigmail.com");
		acc.setBalance(42000);
		try {
			if(service.validateEmail(acc.getEmail()))
			{
				System.out.println("valid email id");
			}
		} catch (AccountException e) {
			System.out.println(e.getMessage());
			assertEquals("in-appropriate email id ",e.getMessage());
		}
	}
	@Test
	public void createAccount() {
		
		acc.setName("Preethi");
		acc.setMobile("9848746997");
		acc.setEmail("preethi@gmail.com");
		acc.setBalance(42000);
		try {
			if(service.validateAccount(acc))
			{
				String m=service.createAccount(acc);
				System.out.println("Account Created for mobile id "+m);
			}
		} catch (AccountException e) {
			System.err.println(e.getMessage());
			
		}
	}
	
	@Test
		public void testExistingAccount(){
			
			acc.setMobile("8121414689");
			try {
				service.createAccount(acc);
			} catch (AccountException e) {
				System.out.println(e.getMessage());
				assertEquals("Account already exists in the Database", e.getMessage());
			}
			
		}
	
	
	@Test
	public void showBalanceExistingAccount(){
		
		acc.setMobile("8121414689");
		
		try {
			double res= service.showBalance(acc.getMobile());
			if(res>0)
			{
				System.out.println("balance is "+res);
				
			}
		} catch (AccountException e) {
			System.out.println(e.getMessage());
			
		}	
	}
	@Test
	public void showBalanceWrongNumber(){
		
		acc.setMobile("812141");
		
		try {
			double res= service.showBalance(acc.getMobile());
			if(res>0)
			{
				System.out.println("balance is "+res);
				
			}
		} catch (AccountException e) {
			System.out.println(e.getMessage());
			assertEquals("Mobile Number Should Contain 10 Digits ",e.getMessage() );
		}	
	}
	
	@Test
	public void showBalanceNotExistingAccount(){
		
		acc.setMobile("1111111111");
		
		try {
			double res= service.showBalance(acc.getMobile());
			if(res>0)
			{
				System.out.println("balance is "+res);
				
			}
		} catch (AccountException e) {
			System.out.println(e.getMessage());
			assertEquals("Mobile number does not exist", e.getMessage());
			
		}	
	}
	
	@Test
		public void testShowBalanceExistingName(){
			acc.setMobile("8121414689");
			try {
				acc = service.getAccountDetails(acc.getMobile());
				service.showBalance(acc.getMobile());
				assertEquals("divya",acc.getName() );
			} catch (AccountException e) {
				
				System.out.println(e.getMessage());
			}
			
		}
	
	@Test
		public void testDepositCheckMobile(){
			acc.setMobile("1111111");
			double amount = 1500.0;
			try {
				if(service.validateMobile(acc.getMobile())){
					
					double bal = service.deposit(acc.getMobile(), amount);
					assertNotEquals(acc.getBalance(), bal);
				}
			} catch (AccountException e) {
				System.out.println(e.getMessage());
				assertEquals("Mobile Number Should Contain 10 Digits ", e.getMessage());
			}
			
		}
	
	@Test
		public void testDepositCheckBalance(){
			acc.setMobile("8121414689");
			double depositAmt = -1500.0;
			try {
				if(service.validateBalance(acc.getBalance())){
					
					double bal = service.deposit(acc.getMobile(), depositAmt);
					assertNotEquals(acc.getBalance(), bal);
				}
			} catch (AccountException e) {
				
				System.out.println(e.getMessage());
				assertEquals("Balance must be greater than zero", e.getMessage());
			}
			
		}

	
	@Test
	public void depositBalance()
	{
		acc.setMobile("8121414689");
		double amount=3000;
		try {
			double res=service.deposit(acc.getMobile(),amount);
			if(res>0)
			{
				System.out.println("balance after deposition: "+res);
			}
		} catch (AccountException e) {
			System.out.println(e.getMessage());
			
		}
		
	}
	
	@Test
		public void testWithdrawCheckMobile(){
			acc.setMobile("11111111");
			double withdrawAmt = 1500.0;
			try {
				if(service.validateMobile(acc.getMobile())){
					double bal = service.withdraw(acc.getMobile(), withdrawAmt);
					assertNotEquals(acc.getBalance(), bal);
				}
			} catch (AccountException e) {
				
				System.out.println(e.getMessage());
				assertEquals("Mobile Number Should Contain 10 Digits ", e.getMessage());
			}
			
		}
	
	@Test
		public void testWithdrawCheckBalance(){
			acc.setMobile("8121414689");
			double amt = -1500.0;
			try {
				if(service.validateBalance(acc.getBalance())){
					
					double bal = service.withdraw(acc.getMobile(), amt);
					assertNotEquals(acc.getBalance(), bal);
				}
			} catch (AccountException e) {
				
				System.out.println(e.getMessage());
				assertEquals("Balance must be greater than zero", e.getMessage());
			}
			
		}
	
	@Test
		public void testWithdrawMoreAmount(){
			acc.setMobile("8121414689");
			double withdrawAmt = 90000.0;
			try {
				double bal = service.withdraw(acc.getMobile(), withdrawAmt);
				assertNotEquals(acc.getBalance(), bal);
			} catch (AccountException e) {
				
				System.out.println(e.getMessage());
				assertEquals("Insufficient balance", e.getMessage());
				
			}
		}
	
	
	@Test
	public void withdrawAmount()
	{
		acc.setMobile("8121414689");
		double amount=90000;
		try {
			double res=service.withdraw(acc.getMobile(), amount);
			if(res>=0)
			{
				System.out.println("balance after withdraw "+res);
			}
		} catch (AccountException e) {
			System.out.println(e.getMessage());
			assertEquals("Insufficient balance",e.getMessage());
		}
	}
	
	@Test
		public void testFundTransferMobile1Valid(){
			Account acc1 = new Account();
			Account acc2 = new Account();
			acc1.setMobile("333333");
			acc2.setMobile("8121414689");
			double amount = 9000;
			try {
				service.fundTransfer(acc1.getMobile(), acc2.getMobile(), amount);
			} catch (AccountException e) {
				
				System.out.println(e.getMessage());
				assertEquals("Mobile Number Should Contain 10 Digits ", e.getMessage());
			}
		}
		
	
	@Test
		public void testFundTransferMobile2Valid()
	{
		Account acc1 = new Account();
		Account acc2 = new Account();
			acc1.setMobile("8121414689");
			acc2.setMobile("222222");
			double amount = 100;
			try {
				service.fundTransfer(acc1.getMobile(), acc2.getMobile(), amount);
			} catch (AccountException e) {
				
				System.out.println(e.getMessage());
				assertEquals("Mobile Number Should Contain 10 Digits ", e.getMessage());
			}
		}
	
	@Test
		public void testFundTransferAmountValidation(){
			Account acc1 = new Account();
			Account acc2 = new Account();
			acc1.setMobile("3333333333");
			acc2.setMobile("2222222222");
			double amount = -100;
			try {
				service.fundTransfer(acc1.getMobile(), acc2.getMobile(), amount);
			} catch (AccountException e) {
				
				System.out.println(e.getMessage());
				assertEquals("Amount must be a number greater than zero", e.getMessage());
			}
		}
	
	@Test
		public void testFundTransferMoreAmount(){
			Account acc1 = new Account();
			Account acc2 = new Account();
			acc1.setMobile("8121414689");
			acc2.setMobile("9666621846");
			double amount = 90000;
			try {
				service.fundTransfer(acc1.getMobile(), acc2.getMobile(), amount);
			} catch (AccountException e) {
				
				System.out.println(e.getMessage());
				assertEquals("Insufficient balance", e.getMessage());
			}
		}	
	
	@Test
		public void testFundTransferNonExistingAccount(){
			Account acc1 = new Account();
			Account acc2 = new Account();
			acc1.setMobile("8121414689");
			acc2.setMobile("1111111111");
			double amount = 1500;
			try {
				service.fundTransfer(acc1.getMobile(), acc2.getMobile(), amount);
			} catch (AccountException e) {
			
				System.out.println(e.getMessage());
				assertEquals("Account doesnot exist. Amount can't be transferred", e.getMessage());
			}
		}
	@Test
		public void testFundTransfer(){
			Account acc1 = new Account();
			Account acc2 = new Account();
			acc1.setMobile("8121414689");
			acc2.setMobile("9666621846");
			double amount = 150;
			try {
			service.fundTransfer(acc1.getMobile(), acc2.getMobile(), amount);
			System.out.println("Amount transfered sucessfully");
			} catch (AccountException e) {
				
				System.out.println(e.getMessage());
			}
		}

	@Test
		public void PrintTransactionNotExistingAccount(){
			acc = new Account();
			acc.setMobile("1234567895");
			try {
				Account ac = service.getAccountDetails(acc.getMobile());
				assertNotNull(ac);
			} catch (AccountException e) {
				
				System.out.println(e.getMessage());
				assertEquals("Account does not exist", e.getMessage());
			}
			
		}
	
	@Test
	public void PrintTransactionExistingAccount(){
		acc = new Account();
		acc.setMobile("8121414689");
		try {
			Account ac = service.getAccountDetails(acc.getMobile());
			assertNotNull(ac);
			System.out.println("Transactions are printed succesfully");
		} catch (AccountException e) {
			
		}
		
	}
}









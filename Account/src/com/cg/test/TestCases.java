package com.cg.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cg.wallet.bean.Wallet;
import com.cg.wallet.exception.WalletException;
import com.cg.wallet.service.IWalletService;
import com.cg.wallet.service.WalletService;

public class TestCases {

	Wallet wallet=new Wallet();;
	IWalletService service;
		
		@Before
		public void init() {
			// TODO Auto-generated method stub
			service = new WalletService();
			
		}
		@Test
			public void testPhone(){
					
					wallet.setName("Jeevana");
					wallet.setPhone("12223334");
					wallet.setEmail("jeevana@gmail.com");
					wallet.setBalance(500.0);
					try {
						if(service.validateWallet(wallet))
							service.createWallet(wallet);
		
					} catch (WalletException e) {
						
						System.err.println(e.getMessage());
						assertEquals("phone number should contain 10 digits",e.getMessage());
				
					
				} 
}
			@Test
			
			public void testName(){
					
					wallet.setName("nikitha");
					wallet.setEmail("nikitha@gmail.com");
					wallet.setPhone("8876543218");
					wallet.setBalance(500);
					try {
						if(service.validateWallet(wallet))
											service.createWallet(wallet);
								
					}
					catch (WalletException e) 
					{
									System.err.println(e.getMessage());
						assertEquals("name should start with uppercase and should contain only alphabets",e.getMessage());
					}
				
			}
			
			@Test 
			public void testName1(){
					
					wallet.setName("");
					wallet.setEmail("nikitha@gmail.com");
					wallet.setPhone("8876543219");
					wallet.setBalance(500);
					try {
						if(service.validateWallet(wallet))
											service.createWallet(wallet);
								
					}
					catch (WalletException e) 
					{
									System.err.println(e.getMessage());
						assertEquals(" name cannot be empty",e.getMessage());
					}
				
			} 
			@Test 
			public void testEmail(){
					
					wallet.setName("Jeevana");
					wallet.setEmail("jeevanagmail.com");
					wallet.setPhone("8876543269");
					wallet.setBalance(500);
					try {
						if(service.validateWallet(wallet))
											service.createWallet(wallet);
								
					}
					catch (WalletException e) 
					{
									System.err.println(e.getMessage());
						assertEquals("email id not appropriate",e.getMessage());
					}
				
			}	
			@Test 
			public void testBalance(){
					
					wallet.setName("Jeevana");
					wallet.setEmail("jeevanagmail.com");
					wallet.setPhone("8876548269");
					wallet.setBalance(0);
					try {
						if(service.validateWallet(wallet))
											service.createWallet(wallet);
								
					}
					catch (WalletException e) 
					{
									System.err.println(e.getMessage());
						assertEquals("Balance should be greater than or equal to zero",e.getMessage());
					}
				
			} 
			@Test
				public void testCreateAccount(){
				
					wallet.setName("Jeevana");
					wallet.setPhone("9640647403");
					wallet.setEmail("jeevana@gmail.com");
					wallet.setBalance(5000);
					
					try {
						if(service.validateWallet(wallet))
						{
							String m=service.createWallet(wallet);
							System.out.println("account created for mobile number "+m);
						}
					} catch (WalletException e) 
					{
									System.err.println(e.getMessage());
						
				}
			} 
				
			@Test
			
				public void testExistingAccount(){
					
					wallet.setPhone("9640647403");
					try {
						service.createWallet(wallet);
					} catch (WalletException e) {
						
						System.err.println(e.getMessage());
						assertNotNull("Given account already exists",e.getMessage());
					}
					
				} 
			
			@Test
				public void testShowBalanceNonExistingAccount(){
					wallet.setPhone("1212121212");
					try {
						service.showBalance(wallet.getPhone());
					} catch (WalletException e) {
						
						System.err.println(e.getMessage());

						assertEquals("the mobile number does not exist", e.getMessage());
					}
				} 
			@Test
			public void testShowBalanceNonExistingAccount1(){
				wallet.setPhone("12121212");
				try {
					service.showBalance(wallet.getPhone());
				} catch (WalletException e) {
					
					System.err.println(e.getMessage());

					assertEquals("phone number should contain 10 digits", e.getMessage());
				}
			} 
			@Test
				public void testShowBalanceExistingName(){
					wallet.setPhone("9640647403");
					try {
						double res=service.showBalance(wallet.getPhone());
						if(res>0)
						{
							System.out.println("balance is" +res);
						}
					} catch (WalletException e) {
						
						System.err.println(e.getMessage());
					}
					
				}	
			@Test
				public void testShowBalanceExisting(){
					wallet.setPhone("9640647403");
				try {
							double res=service.showBalance(wallet.getPhone());
							if (res>0)
							{
								System.out.println("balance is"  +res);
							}
							
				}
				catch(WalletException e)
				{
					System.out.println(e.getMessage());
				}
			} 
			@Test
				public void testDepositCheckMobile(){
					wallet.setPhone("1111111");
					double depositAmt = 1500.0;
					try {
						if(service.validatePhone(wallet.getPhone())){
							
						double bal = service.deposit(wallet.getPhone(), depositAmt);
							assertNotEquals(wallet.getBalance(), bal);
						}
				} catch (WalletException e) {
						// TODO Auto-generated catch block
					System.err.println(e.getMessage());
						assertEquals("phone number should contain 10 digits", e.getMessage());
					}
					
				} 
			@Test
				public void testDepositCheckBalance(){
					wallet.setPhone("9640647403");
					double depositAmt = -1500.0;
					try {
						if(service.validateBalance(wallet.getBalance())){
							
							double bal = service.deposit(wallet.getPhone(), depositAmt);
							assertNotEquals(wallet.getBalance(), bal);
						}
				} catch (WalletException e) {
						
					System.err.println(e.getMessage());
						assertEquals("Balance should be greater than or equal to zero", e.getMessage());
					}
				
				} 
			@Test
				public void testDepositCorrect()
			{
					wallet.setPhone("9640647403");
					double depositAmt = 1500.0;
					
					try
					{
						double res=service.deposit(wallet.getPhone(), depositAmt);
								if(res>0)
								{
									System.out.println("balance after deposit is " +res);
								}
					}
						catch(WalletException e)
						{
					System.out.println(e.getMessage());
						}
				
				} 
			@Test
				public void testWithdrawCheckMobile(){
					wallet.setPhone("5698412573");
					double withdrawAmt = 1500.0;
					try {
						if(service.validatePhone(wallet.getPhone())){
							double bal = service.withdraw(wallet.getPhone(), withdrawAmt);
							assertNotEquals(wallet.getBalance(), bal);
						}
					} catch (WalletException e) {
						
					System.err.println(e.getMessage());
						assertEquals("the mobile number does not exist", e.getMessage());
					}
					
				} 
			@Test
			public void testWithdrawCheckMobile1(){
				wallet.setPhone("9640647403");
				double withdrawAmt = -1500.0;
				try {
					if(service.validatePhone(wallet.getPhone())){
						double bal = service.withdraw(wallet.getPhone(), withdrawAmt);
						assertNotEquals(wallet.getBalance(), bal);
					}
				} catch (WalletException e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
					assertEquals("Balance should be greater than or equal to zero", e.getMessage());
				}
				
			} 
			@Test
				public void testFundTransferMobile1Valid(){
					Wallet acc1 = new Wallet();
					Wallet acc2 = new Wallet();
					acc1.setPhone("333333");
					acc2.setPhone("9640647403");
					double amount = 100;
					try {
						service.fundTransfer(acc1.getPhone(), acc2.getPhone(), amount);
					} catch (WalletException e) {
					// TODO Auto-generated catch block
						System.err.println(e.getMessage());
					assertEquals("phone number should contain 10 digits", e.getMessage());
					}
				} 
			@Test
			public void testFundTransferMobile2Valid(){
				Wallet acc1 = new Wallet();
				Wallet acc2 = new Wallet();
				acc1.setPhone("9640647403");
				acc2.setPhone("96406474");
				double amount = 100;
				try {
					service.fundTransfer(acc1.getPhone(), acc2.getPhone(), amount);
				} catch (WalletException e) {
				// TODO Auto-generated catch block
					System.err.println(e.getMessage());
				assertEquals("phone number should contain 10 digits", e.getMessage());
				}
			} 
		@Test
				public void testFundTransferAmountValidation(){
					Wallet acc1 = new Wallet();
					Wallet acc2 = new Wallet();
					acc1.setPhone("9640647403");
					acc2.setPhone("8121414689");
					double amount = -100;
					try {
						service.fundTransfer(acc1.getPhone(), acc2.getPhone(), amount);
					} catch (WalletException e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
						assertEquals("Amount must be a number greater than zero", e.getMessage());
					}
				} 
				
			@Test
				public void testFundTransferMoreAmount(){
					Wallet acc1 = new Wallet();
					Wallet acc2 = new Wallet();
					acc1.setPhone("9640647403");
					acc2.setPhone("8121414689");
					double amount = 50000;
					try {
						service.fundTransfer(acc1.getPhone(), acc2.getPhone(), amount);
					} catch (WalletException e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
						assertEquals("Account balance is low", e.getMessage());
					}
				}	
			@Test
				public void testFundTransferNonExistingAccount(){
					Wallet acc1 = new Wallet();
					Wallet acc2 = new Wallet();
				acc1.setPhone("3333333333");
					acc2.setPhone("1234567890");
					double amount = 150;
					try {
						service.fundTransfer(acc1.getPhone(), acc2.getPhone(), amount);
					} catch (WalletException e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
						assertEquals("Account doesnot exist. Amount can't be transferred", e.getMessage());
					}
				} 
				
			@Test
				public void testFundTransferExistingAmount(){
							Wallet acc1 = new Wallet();
					Wallet acc2 = new Wallet();
					acc1.setPhone("9640647403");
					acc2.setPhone("8121414689");
					double amount = 150;
					try {
						service.fundTransfer(acc1.getPhone(), acc2.getPhone(), amount);
						System.out.println("amount transferred succesfully");
					} catch (WalletException e) {
						
						System.out.println(e.getMessage());
						
						
					}
				}	
			@Test
				public void testPrintTransaction(){
					wallet = new Wallet();
					wallet.setPhone("9640647403");
					try {
						Wallet ac = service.getAccountDetails(wallet.getPhone());
						assertNotNull(ac);
						System.out.println("Transaction printed successfully");
					} catch (WalletException e) {
						// TODO Auto-generated catch block
						//System.out.println(e.getMessage());
											}
					
				} 
				
						
}

package com.cg.account.service;

import com.cg.account.bean.Account;
import com.cg.account.dao.AccountDao;
import com.cg.account.dao.IAccountDao;
import com.cg.account.exception.AccountException;




public class AccountService implements IAccountService {
	Account acc=new Account();
	IAccountDao dao= new AccountDao();

	
	@Override
	public String createAccount(Account account) throws AccountException {
		
		return dao.createAccount(account);
	}


	@Override
	public boolean validateMobile(String mobile) throws AccountException {
		if(!mobile.matches("\\d{10}"))
		{
			throw new AccountException("Mobile Number Should Contain 10 Digits ");
		}
		return true;
	}

	@Override
	public boolean validateNullName(String name) throws AccountException {
		if(name.matches(" "))
		{
			throw new AccountException("name cannot be null");
		}
		return true;
	}
	
	@Override
	public boolean validateName(String name) throws AccountException {
		
			if(!name.matches("[A-Z][A-Za-z]{3,}"))
			{
				throw new AccountException("name should start with capital letter and should contain only alphabets ");
			}
		
		return true;
		
	}

	@Override
	public boolean validateEmail(String email) throws AccountException {
		if(!email.matches("[a-z0-9_\\.]+@[a-z]+\\.com"))
		{
			throw new AccountException("in-appropriate email id ");
		}
		return true;
	}


	@Override
	public boolean validateBalance(double balance) throws AccountException {
		if(balance<=0)
		{
			throw new AccountException("Balance must be greater than zero");
		}
		return true;
	}


	@Override
	public boolean validateAccount(Account acc) throws AccountException {
		if(!(validateName(acc.getName()) && validateMobile(acc.getMobile()) && validateEmail(acc.getEmail()) && 
				validateBalance(acc.getBalance())))
		{
			return false;
		}
		return true;
	}
	
	
	@Override
	public double deposit(String mobile, double amount) throws AccountException {
		
		return dao.deposit(mobile,amount);
	}


	@Override
	public double withdraw(String mobile, double amount)
			throws AccountException {
		
		return dao.withdraw(mobile, amount);
	}


	@Override
	public Account getAccountDetails(String mobile) throws AccountException {
		
		return dao.getAccountDetails(mobile);
	}


	@Override
	public boolean fundTransfer(String mobile1, String mobile2, double amount)
			throws AccountException {
		
	if(!(validateMobile(mobile1) && validateMobile(mobile2)))
	{
			throw new AccountException("Mobile number should contain 10 digits");
	}
	if(amount < 0)
	{
			throw new AccountException("Amount must be a number greater than zero");
	}	
		return dao.fundTransfer(mobile1, mobile2, amount);
	}


	@Override
	public double showBalance(String mobile) throws AccountException {
		if(!mobile.matches("\\d{10}"))
		{
			throw new AccountException("Mobile Number Should Contain 10 Digits ");
		}
		return dao.showBalance(mobile);
	}


	@Override
	public Account printTransactions(String mobile) throws AccountException {
		
		return dao.printTransaction(mobile);
	}




	

}


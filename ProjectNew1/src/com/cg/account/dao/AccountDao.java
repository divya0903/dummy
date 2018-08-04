package com.cg.account.dao;

import java.util.HashMap;

import com.cg.account.bean.Account;
import com.cg.account.db.AccountDb;
import com.cg.account.exception.AccountException;

public class AccountDao implements IAccountDao {
	static HashMap<String, Account> accMap =AccountDb.getAccountDb();
	

	@Override
	public String createAccount(Account account) throws AccountException {
		if(accMap.containsKey(account.getMobile()))
		{
			throw new AccountException("Account already exists in the Database");
		}
		accMap.put(account.getMobile(), account);
		
		return account.getMobile();
	}

	
	@Override
	public double deposit(String mobile,double amount) throws AccountException {
		Account acc=accMap.get(mobile);
		if(!accMap.containsKey(mobile))
		{
			throw new AccountException("Mobile number does not exist");
		}
		double bal=acc.getBalance()+amount;
		acc.setBalance(bal);
		
		return bal;
	}

	@Override
	public double withdraw(String mobile, double amount)
			throws AccountException {
		Account acc=accMap.get(mobile);
		if(!accMap.containsKey(mobile))
		{
			throw new AccountException("Mobile number does not exist");
		}
		if(!mobile.matches("\\d{10}"))
		{
			throw new AccountException("Mobile Number Should Contain 10 Digits ");
		}
		if(amount<=0)
		{
			throw new AccountException("Balance must be greater than zero");
		}
		if(amount>acc.getBalance())
		{
			throw new AccountException("Insufficient balance");
		}
		double bal=acc.getBalance()-amount;
		acc.setBalance(bal);
		
		return bal;
		
	}

	@Override
	public Account getAccountDetails(String mobile) throws AccountException {
		Account acc=accMap.get(mobile);
		if(!accMap.containsKey(mobile))
		{
			throw new AccountException("Account does not exist");
		}
		return acc;
	}

	@Override
	public boolean fundTransfer(String mobile1, String mobile2, double amount) 
			throws AccountException {
			
		Account acc1 = accMap.get(mobile1);
		Account acc2 = accMap.get(mobile2);
				
		if(acc1 == null || acc2 == null)
		{
			throw new AccountException("Account doesnot exist. Amount can't be transferred");
		}	
		if(amount > acc1.getBalance())
		{
			throw new AccountException("Insufficient balance");
		}
		double bal = acc1.getBalance()-amount;			//withdraw from mobile1
		acc1.setBalance(bal);	
		acc2.setBalance(acc2.getBalance()+amount);		//deposit in mobile2
		return true;
		
	}

	@Override
	public double showBalance(String mobile) throws AccountException {
		Account acc=accMap.get(mobile);
		if(!accMap.containsKey(mobile))
		{
			throw new AccountException("Mobile number does not exist");
		}
		
		
		return acc.getBalance();
		
	}


}


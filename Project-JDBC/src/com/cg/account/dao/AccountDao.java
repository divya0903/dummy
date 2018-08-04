package com.cg.account.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.cg.account.bean.Account;
import com.cg.account.connection.DbConnect;
import com.cg.account.db.AccountDb;
import com.cg.account.exception.AccountException;

public class AccountDao implements IAccountDao {
	
	

	@Override
	public String createAccount(Account account) throws AccountException {
		
		Connection connect=DbConnect.getConnection();
		
		try {
			PreparedStatement statement=connect.prepareStatement("INSERT INTO ACCOUNT VALUES(?, ?, ?, ?)");
			
			statement.setString(1,account.getName());
			statement.setString(2, account.getMobile());
			statement.setString(3, account.getEmail());
			statement.setDouble(4, account.getBalance());
			
			int result = statement.executeUpdate();
			
			if(result==1){
				Statement stat = connect.createStatement();
				ResultSet rs = stat.executeQuery("SELECT mobile FROM Account");
				if(rs!=null)
					rs.next();
				String id = rs.getString(1);
				return id;
			}
			else
				throw new AccountException("Cannot create account");
		} catch (SQLException e) {
						
			throw new AccountException(e.getMessage());
		}
		
		
	}

	
	@Override
	public double deposit(String mobile,double amount) throws AccountException {
		
				Account acc = getAccountDetails(mobile);
				double finalBal = acc.getBalance() + amount; 
				acc.setBalance(finalBal);
				
				
				Connection connection = DbConnect.getConnection();
				try {
					PreparedStatement stat = connection.prepareStatement("update account set balance=? where mobile=?");
					stat.setDouble(1, acc.getBalance());
					stat.setString(2, mobile);
					int res = stat.executeUpdate();
					if(res>=0)
					 return acc.getBalance();
					else
						throw new AccountException("Cannot update balance amount");
				} catch (SQLException e) {
					
					throw new AccountException(e.getMessage());
				}
	}

	@Override
	public double withdraw(String mobile, double amount)
			throws AccountException {
		
		Account acc = getAccountDetails(mobile);
		
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
		
		Connection connection = DbConnect.getConnection();
		try {
			PreparedStatement stat = connection.prepareStatement("update account set balance=? where mobile=?");
			stat.setDouble(1, acc.getBalance());
			stat.setString(2, mobile);
			int res = stat.executeUpdate();
			if(res>=0)
				 return acc.getBalance();
				else
					throw new AccountException("Cannot update balance amount");
			
		} catch (SQLException e) {
			
			throw new AccountException(e.getMessage());
		}
		
	}

	@Override
	public Account getAccountDetails(String mobile) throws AccountException {
		//Account acc= getAccountDetails(mobile);
		Connection connection = DbConnect.getConnection();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from account");
		
			
			while(resultSet.next()){
				
				if(resultSet.getString(2).equals(mobile)){
					Account acc = new Account();
					acc.setName(resultSet.getString(1));
					acc.setMobile(resultSet.getString(2));
					acc.setEmail(resultSet.getString(3));
					acc.setBalance(resultSet.getDouble(4));
					return acc;
				}
			}
			throw new AccountException("Account Does not exist");
			
		} catch (Exception e) {
			
			throw new AccountException(e.getMessage());
		}
	}
	

	@Override
	public boolean fundTransfer(String mobile1, String mobile2, double amount) 
			throws AccountException {
			
		Account acc1 = getAccountDetails(mobile1);
		Account acc2 = getAccountDetails(mobile2);
				
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
		double bal2=acc2.getBalance()+amount;
		acc2.setBalance(bal2);							//deposit in mobile2
		
		Connection connection = DbConnect.getConnection();
		try {
			PreparedStatement stat1 = connection.prepareStatement("update account set balance=? where mobile=?");
			stat1.setDouble(1, acc1.getBalance());
			stat1.setString(2, mobile1);
			int res1 = stat1.executeUpdate();
			
			PreparedStatement stat2 = connection.prepareStatement("update account set balance=? where mobile=?");
			stat2.setDouble(1, acc2.getBalance());
			stat2.setString(2, mobile2);
			int res2 = stat2.executeUpdate();
			
			if(res1>=0 && res2>=0)
				return true;
			else
				throw new AccountException("Updation fail");
		} catch (SQLException e) {
			
			throw new AccountException(e.getMessage());
		}
		
	}

	@Override
	public double showBalance(String mobile) throws AccountException {
		Account acc=getAccountDetails(mobile);
		if(acc.getMobile()==mobile)
		{
			throw new AccountException("Mobile number does not exist");
		}
		
		return acc.getBalance();
		
	}


	@Override
	public Account printTransaction(String mobile) throws AccountException {
		
		Account acc=getAccountDetails(mobile);
		return acc;
	}

}


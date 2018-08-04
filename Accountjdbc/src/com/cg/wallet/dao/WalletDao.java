

	package com.cg.wallet.dao;


	import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.cg.util.Util;
import com.cg.wallet.bean.Wallet;
import com.cg.wallet.exception.WalletException;

	public class WalletDao implements IWalletDao {
	


	@Override
	public String createWallet(Wallet wallet) throws WalletException {
			
		Connection connection =Util.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(IDaoMapping.query);
			statement.setString(1, wallet.getName());
			statement.setString(2, wallet.getPhone());
			statement.setString(3, wallet.getEmail());
			statement.setDouble(4, wallet.getBalance());
			
			
			int res = statement.executeUpdate();
			if(res==1){
				/*Statement stat = connection.createStatement();
				ResultSet rs = stat.executeQuery(IDaoMapping.mobileNo);
				if(rs!=null)
				rs.next();*/
				String id = wallet.getPhone();
				return id;
			}
			else
				throw new WalletException("Unable to insert Account details");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new WalletException(e.getMessage());
	}
	}


	@Override
	public double showBalance(String phone) throws WalletException {

		Wallet wal = getAccountDetails(phone);
		return wal.getBalance();
	}


	@Override
	public double deposit(String phone, double amount) throws WalletException {
		{

			Wallet wal = getAccountDetails(phone);
			double finalBal = wal.getBalance() + amount; 
			wal.setBalance(finalBal);
			
			
			Connection connection = Util.getConnection();
			try {
				PreparedStatement stat = connection.prepareStatement(IDaoMapping.updateBal);
				stat.setDouble(1, wal.getBalance());
				stat.setString(2, phone);
				int res = stat.executeUpdate();
				 return wal.getBalance();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new WalletException(e.getMessage());
			}
		}
		
	}


	@Override
	public double withdraw(String phone, double amount) throws WalletException {
		
		
		Wallet wal = getAccountDetails(phone);
		double finalBal = wal.getBalance() - amount; 
		wal.setBalance(finalBal);
		
		
		Connection connection = Util.getConnection();
		try {
			PreparedStatement stat = connection.prepareStatement(IDaoMapping.updateBal);
			stat.setDouble(1, wal.getBalance());
			
			stat.setString(2, phone);
			int res = stat.executeUpdate();
			 return wal.getBalance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new WalletException(e.getMessage());
		}
		}


	@Override
	public boolean fundTransfer(String phone1, String phone2, double amount)
			throws WalletException {
		Wallet wal1= getAccountDetails(phone1);
		Wallet wal2 = getAccountDetails(phone2);
		
		if(wal1 == null || wal2 == null)
			throw new WalletException("Account doesnot exist. Amount can't be transferred");
		
		if(amount > wal1.getBalance())
		{
			throw new WalletException("Account balance is low");
		}
		double bal1 = wal1.getBalance()-amount;			//withdraw from mobile1
		wal1.setBalance(bal1);
		double bal2 = wal2.getBalance()+amount;
		wal2.setBalance(bal2);		//deposit in mobile2
		
		Connection connection = Util.getConnection();
		try {
			PreparedStatement stat1 = connection.prepareStatement(IDaoMapping.updateBal);
			stat1.setDouble(1, wal1.getBalance());
		
			stat1.setString(2, phone1);
			int res1 = stat1.executeUpdate();
			
			PreparedStatement stat2 = connection.prepareStatement(IDaoMapping.updateBal);
			stat2.setDouble(1, wal2.getBalance());
	
			stat2.setString(2, phone2);
			int res2 = stat2.executeUpdate();
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new WalletException(e.getMessage());
		}
		return true;

		
	}


	@Override
	public Wallet getAccountDetails(String phone) throws WalletException {
		Connection connection = Util.getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(IDaoMapping.accInfo);
			Wallet wal = null;
			while(resultSet.next()){
				//System.out.println("Val: "+resultSet.getString(3));
				if(resultSet.getString(2).equals(phone)){
					wal = new Wallet();
					wal.setName(resultSet.getString(1));
					wal.setPhone(resultSet.getString(2));
					wal.setEmail(resultSet.getString(3));
					wal.setBalance(resultSet.getDouble(4));
					
					
					return wal;
				}
			}
			throw new WalletException("Account doesnot exist. Amount can't be transferred");
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new WalletException(e.getMessage());
		}
		
	}
	}

	



		





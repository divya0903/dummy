

	package com.cg.wallet.dao;


	import java.util.HashMap;









	import com.cg.wallet.bean.Wallet;
import com.cg.wallet.db.WalletDb;
import com.cg.wallet.exception.WalletException;

	public class WalletDao implements IWalletDao {
	static HashMap<String,Wallet> walMap=WalletDb.getwalDb();


	@Override
	public String createWallet(Wallet wallet) throws WalletException {
		// TODO Auto-generated method stub
		if (walMap.containsKey(wallet.getPhone()))
				{
			throw new WalletException("Given account already exists " +wallet.getPhone());
				}
		else
		{
			walMap.put(wallet.getPhone(), wallet);
			return wallet.getPhone();
	}
	}


	@Override
	public double showBalance(String phone) throws WalletException {

		Wallet acc=walMap.get(phone);
				if(!walMap.containsKey(phone))
				{
					throw new WalletException("the mobile number does not exist");
				}
				
				return acc.getBalance();
	}


	@Override
	public double deposit(String phone, double amount) throws WalletException {
		{
			/*Wallet acc=walMap.get(phone);
			if(!walMap.containsKey(phone))
			{
				throw new WalletException("the mobile number does not exist");
			}
			else if(!phone.matches("\\d{10}"))
			{new WalletException("phone number should contain 10 digits");
			}
				throw 
			else if(amount<=0)
			{
				throw new WalletException("deposit amount should be greater than 0");
			}
			double bal=(acc.getBalance()+amount);
	acc.setBalance(bal);
	return bal;
*/
			Wallet wal = walMap.get(phone);
					double finalBal = wal.getBalance() + amount; 
					wal.setBalance(finalBal);
					return finalBal;
		}
		
	}


	@Override
	public double withdraw(String phone, double amount) throws WalletException {
		
		Wallet acc=walMap.get(phone);
		if(!walMap.containsKey(phone))
		{
			throw new WalletException("the mobile number does not exist");
		}
		else if(!phone.matches("\\d{10}"))
		{
			throw new WalletException("phone number should contain 10 digits");
		}
		else if(amount<=0)
		{
			throw new WalletException("Balance should be greater than or equal to zero");
		}
		double bal=(acc.getBalance()- amount);
	acc.setBalance(bal);
	return bal;
	}


	@Override
	public boolean fundTransfer(String phone, String phone1, double amount)
			throws WalletException {
		Wallet wal1 = walMap.get(phone);
				Wallet wal2 = walMap.get(phone1);
				
				if(wal1 == null || wal2 == null)
					throw new WalletException("Account doesnot exist. Amount can't be transferred");
				
				if(amount > wal1.getBalance())
					throw new WalletException("Account balance is low");
				double bal = wal1.getBalance()-amount;			//withdraw from mobile1
				wal1.setBalance(bal);	
				wal2.setBalance(wal2.getBalance()+amount);		//deposit in mobile2
				return true;
		
	}


	@Override
	public Wallet getAccountDetails(String phone) throws WalletException {
		try {
			 			Wallet wal = walMap.get(phone);
			 			return wal;
			 		} catch (Exception e) {
			 			// TODO: handle exception
			 			throw new WalletException(e.getMessage());
			 		}
		
	}





	



		




}


	package com.cg.wallet.dao;

	import com.cg.wallet.bean.Wallet;
import com.cg.wallet.exception.WalletException;

	public interface IWalletDao {
		public String createWallet(Wallet wallet) throws WalletException;
		public double showBalance(String phone) throws WalletException;
		public double deposit(String phone,double amount) throws WalletException;
		public double withdraw(String phone,double amount) throws WalletException;
		public boolean fundTransfer(String phone, String phone1, double amount) throws WalletException;
		Wallet getAccountDetails (String phone) throws WalletException;
		
			
}



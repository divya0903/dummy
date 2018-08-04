
	package com.cg.wallet.service;


	import com.cg.wallet.bean.Wallet;
import com.cg.wallet.dao.IWalletDao;
import com.cg.wallet.dao.WalletDao;
import com.cg.wallet.exception.WalletException;

	public class WalletService implements IWalletService {
	IWalletDao dao=new WalletDao();
		@Override
		public boolean validatePhone(String phone) throws WalletException {
			if(!phone.matches("\\d{10}"))
			{
				throw new WalletException("phone number should contain 10 digits");
			}
			return true;
		}
		
		@Override
		public String createWallet(Wallet wallet) throws WalletException {
			// TODO Auto-generated method stub
			return dao.createWallet(wallet);
		}

		@Override
		public boolean validateName(String name) throws WalletException {
			if(name.isEmpty())
			
				throw new WalletException(" name cannot be empty");
			
			else if(!name.matches("[A-Z][A-Za-z]{3,}"))
				
					throw new WalletException("name should start with uppercase and should contain only alphabets");
				
			
			return true;
		}

		@Override
		public boolean validateEmail(String email) throws WalletException {
			if(!email.matches("[a-z0-9_\\.]+@[a-z]+\\.com"))
			{
				throw new WalletException("email id not appropriate");
			}
			return true;
		}

		@Override
		public boolean validateBalance(double balance) throws WalletException {
			if(balance<=0)
			{
				throw new WalletException("Balance should be greater than or equal to zero");
			}
			return true;
		}

		@Override
		public boolean validateWallet(Wallet wallet) throws WalletException {
			if(!(validateName(wallet.getName())&&validatePhone(wallet.getPhone())&&
					validateBalance(wallet.getBalance())&&validateEmail(wallet.getEmail())))
					{
				throw new WalletException("invalid details");
					}
					
			return true;
		}

		@Override
		public double showBalance(String phone) throws WalletException {
			// TODO Auto-generated method stub
			if(!validatePhone(phone))
						throw new WalletException();
				
			return dao.showBalance(phone);
		}

		@Override
		public double deposit(String phone, double amount) throws WalletException {
			// TODO Auto-generated method stub
			return dao.deposit(phone, amount);
		}

		@Override
		public double withdraw(String phone, double amount) throws WalletException {
			// TODO Auto-generated method stub
			return dao.withdraw(phone, amount);
		}

		@Override
		public boolean fundTransfer(String phone, String phone1, double amount)
				throws WalletException {
			if(!validatePhone(phone) || !validatePhone(phone1))
							throw new WalletException("phone number should contain 10 digits");
						if(amount < 0)
							throw new WalletException("Amount must be a number greater than zero");
						return dao.fundTransfer(phone, phone1, amount);
					}

		@Override
		public Wallet getAccountDetails(String phone) throws WalletException {
			
			return dao.getAccountDetails(phone);
		}
		}

		
		

		






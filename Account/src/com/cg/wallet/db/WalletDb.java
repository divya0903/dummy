

	package com.cg.wallet.db;


	import java.util.HashMap;
	import com.cg.wallet.bean.Wallet;



	public class WalletDb {
		private static HashMap<String,Wallet> walDb=new HashMap<String,Wallet>();
		public static HashMap<String, Wallet> getwalDb() 
		{
			return walDb;
		}
		static
		{
			walDb.put("8121414689", new Wallet("Divya","8121414689","divya@gmail.com",1000));
			walDb.put("9640647403", new Wallet("Jeevana","9640647403","jeevana@gmail.com",5000));
			walDb.put("1234567891", new Wallet("Pravalika","1234567891","pravalika@gmail.com",10000));
		}
	}





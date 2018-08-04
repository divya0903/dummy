
	package com.cg.account.db;

	import java.util.HashMap;

	import com.cg.account.bean.Account;



	public class AccountDb {
		
		private static HashMap<String, Account > accountDb = new HashMap<String, Account>();
		
		public static HashMap<String, Account> getAccountDb()
		{
			return accountDb;
		}
		
		static
		{
			accountDb.put("8121414689", new Account("divya","8121414689","divya@gmail.com",85000));
			accountDb.put("9666621846", new Account("jeevana", "9666621846", "jeevana@gmail.com", 52000));
			accountDb.put("7013886693", new Account("nikitha", "7013886693", "nikitha@gmail.com", 68000));
			accountDb.put("8096419697", new Account("pravallika", "8096419697", "pravalika@gmail.com", 36000));
		}

	}




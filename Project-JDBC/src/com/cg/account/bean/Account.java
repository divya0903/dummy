package com.cg.account.bean;


	public class Account {
		@Override
		public String toString() {
			return "Account [name=" + name + ", mobile=" + mobile + ", email="
					+ email + ", balance=" + balance + "]";
		}
		private String name;
		private String mobile;
		private String email;
		private double balance;
		public Account(String name, String mobile, String email, double balance) {
			super();
			this.name = name;
			this.mobile = mobile;
			this.email = email;
			this.balance = balance;
		}
		public Account() {
			
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public double getBalance() {
			return balance;
		}
		public void setBalance(double balance) {
			this.balance = balance;
		}

	}




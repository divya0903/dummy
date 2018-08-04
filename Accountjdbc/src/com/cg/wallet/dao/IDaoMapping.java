package com.cg.wallet.dao;

public interface IDaoMapping {

	public String query = "insert into wallet values(?, ?, ?, ?)";
	public String updateBal = "update wallet set balance=? where phone=?";
	public String accInfo = "select * from wallet";
	public String mobileNo = "select phone from wallet";
}

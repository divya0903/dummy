package com.cg.plp.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.cg.plp.bean.Account;
import com.cg.plp.exception.WalletException;
import com.cg.plp.util.EMUtil;

public class WalletDao implements IWalletDao {

	@Override
	public String createAccount(Account account) throws WalletException {
		EntityManager em = EMUtil.getEntityManager();

		em.getTransaction().begin();
		em.merge(account);
		em.getTransaction().commit();

		em.close();

		return account.getMobile();
	}

	@Override
	public double showBalance(String mobile) throws WalletException {

		Account acc = getAccountDetails(mobile);
		return acc.getBalance();
	}

	@Override
	public double deposit(String mobile, double amt) throws WalletException {
		// TODO Auto-generated method stub
		Account account = getAccountDetails(mobile);
		double newBal = account.getBalance() + amt;

		account.setBalance(newBal);
		account.setModDate("" + new Date());

		EntityManager em = EMUtil.getEntityManager();

		em.getTransaction().begin();
		em.merge(account);
		em.getTransaction().commit();

		em.close();
		return account.getBalance();
	}

	@Override
	public double withdraw(String mobile, double amt) throws WalletException {
		// TODO Auto-generated method stub
		Account account = getAccountDetails(mobile);
		double newBal = account.getBalance() - amt;

		account.setBalance(newBal);
		account.setModDate("" + new Date());

		EntityManager em = EMUtil.getEntityManager();

		em.getTransaction().begin();
		em.merge(account);
		em.getTransaction().commit();

		em.close();
		return account.getBalance();
	}

	@Override
	public boolean fundTransfer(String mobile1, String mobile2, double amount) throws WalletException {
		// TODO Auto-generated method stub

		withdraw(mobile1, amount);
		deposit(mobile2, amount);

		return true;
	}

	@Override
	public Account printTransaction(String mobile) throws WalletException {
		// TODO Auto-generated method stub
		Account account = getAccountDetails(mobile);

		return account;
	}

	private Account getAccountDetails(String mobile) throws WalletException { //
		// TODO Auto-generated method stub

		EntityManager em = EMUtil.getEntityManager();
		Account acc = null;
		try {
			TypedQuery<Account> accQuery = em.createQuery("SELECT a FROM Account a WHERE MOBILE=?", Account.class);
			accQuery.setParameter(1, mobile);

			acc = accQuery.getSingleResult();
			em.close();
		} catch (NoResultException e) {
			// TODO: handle exception
			throw new WalletException("Account does not exist");
		}
		return acc;
	}

}

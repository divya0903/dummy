package com.cg.account.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMUtil {

	public static EntityManager getEntityManager() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
		return emf.createEntityManager();
	}

}

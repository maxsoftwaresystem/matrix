package matrix.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static final EntityManagerFactory FABRICA_EM = Persistence.createEntityManagerFactory("matrixPU");

	public static EntityManager getEntityManager() {
		return FABRICA_EM.createEntityManager();
	}

}

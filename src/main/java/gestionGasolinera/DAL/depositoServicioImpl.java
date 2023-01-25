package gestionGasolinera.DAL;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

@Service
public class depositoServicioImpl implements depositoService {

	@PersistenceContext
	private EntityManager em;
	public void llenarDeposito(deposito dp) {
		// TODO Auto-generated method stub
		em.persist(dp);

	}

	public List<deposito> selectDepositos() {
		// TODO Auto-generated method stub
		return em.createQuery("SELECT depositos FROM deposito depositos").getResultList();
		
	}

	public void borrarUltimoLLenado(deposito dp) {
		// TODO Auto-generated method stub
		em.remove(em.contains(dp)? dp: em.merge(dp));
		
	}

}

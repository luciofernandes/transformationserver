package br.ufpe.cin.transformationserver.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuditingService {

	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Object[]> findRevisions(Class c, Long id){
		EntityManager em = this.entityManagerFactory.createEntityManager();
		AuditReader auditReader = AuditReaderFactory.get(em);
		AuditQuery query = auditReader.createQuery().forRevisionsOfEntity(c, false, true);
		query.add(AuditEntity.id().eq(id));
		List<Object[]> out = query.getResultList();
		em.close();
		return out;
	}
}

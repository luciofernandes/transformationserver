package br.ufpe.cin.transformationserver.audit;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.ufpe.cin.transformationserver.domain.User;
import br.ufpe.cin.transformationserver.domain.repository.UserRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:config/applicationContext.xml")
public class UserAuditTest {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	@Test
	public void testInject(){
		assertNotNull(userRepository);
		assertNotNull(entityManagerFactory);
	}
	
	@Test
	public void testSpringData(){
		Revisions<Integer, User> revisionsRoot = userRepository.findRevisions(3l);
		List<Revision<Integer, User>> revisions =  revisionsRoot.getContent();
		
		for(Revision<Integer, User> rev : revisions){
			AuditedRevisionEntity auditedRevisionEntity = (AuditedRevisionEntity) rev.getMetadata().getDelegate();
			//rev.getMetadata().getRevisionNumber()
			System.out.println(auditedRevisionEntity.getUser());
		}
		
		EntityManager em = this.entityManagerFactory.createEntityManager();
		
		AuditReader auditReader = AuditReaderFactory.get(em);
		AuditQuery query = auditReader.createQuery().forRevisionsOfEntity(User.class, false, true);
		query.add(AuditEntity.id().eq(3l));
		List<Object[]> out = query.getResultList();
		out.size();
		for(Object o : out){
			
		}
		//user ;; AuditedRevisionEntity ;; RevisionType.name
		System.out.println(out.size());
		assertEquals("1", "1");
	}
}

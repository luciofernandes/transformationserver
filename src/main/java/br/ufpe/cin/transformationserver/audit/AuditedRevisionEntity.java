package br.ufpe.cin.transformationserver.audit;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

@RevisionEntity(AuditingRevisionListener.class)
@Entity
@Table(name="revinfo")
public class AuditedRevisionEntity extends DefaultRevisionEntity {
	
	private static final long serialVersionUID = 1L;

	private String user;

	public String getUser() {
	
		return user;
	}

	public void setUser(String user) {
	
		this.user = user;
	}
}
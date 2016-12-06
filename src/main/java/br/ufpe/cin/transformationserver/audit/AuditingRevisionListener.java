package br.ufpe.cin.transformationserver.audit;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditingRevisionListener implements RevisionListener {


	public void newRevision(Object revisionEntity) {

		AuditedRevisionEntity auditedRevisionEntity = (AuditedRevisionEntity) revisionEntity;

		String userName = SecurityContextHolder.getContext().getAuthentication().getName();

		if(userName.equals(""))
			userName = null;
		auditedRevisionEntity.setUser(userName);
	}
}

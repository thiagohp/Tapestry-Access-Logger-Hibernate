// Copyright 2008 Thiago H. de Paula Figueiredo
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package br.com.arsmachina.accesslogger.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.arsmachina.accesslogger.Access;
import br.com.arsmachina.accesslogger.AccessLogger;

/**
 * {@link AccessLogger} implementation using Hibernate to persist {@link Access} instances in a
 * relationational database.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public class HibernateAccessLogger implements AccessLogger {
	
	private Logger logger = LoggerFactory.getLogger(HibernateAccessLogger.class);

	private SessionFactory sessionFactory;

	/**
	 * Single constructor of this class.
	 * 
	 * @param sessionFactory a {@link SessionFactory}. It cannot be null.
	 */
	public HibernateAccessLogger(SessionFactory sessionFactory) {

		if (sessionFactory == null) {
			throw new IllegalArgumentException("Parameter sessionFactory cannot be null");
		}

		this.sessionFactory = sessionFactory;

	}

	/**
	 * @see br.com.arsmachina.accesslogger.AccessLogger#log(br.com.arsmachina.accesslogger.Access)
	 */
	public void log(Access access) {
		
		final Session session = sessionFactory.openSession();
		Transaction transaction = null;

		if (access instanceof br.com.arsmachina.accesslogger.hibernate.Access == false) {
			access = new br.com.arsmachina.accesslogger.hibernate.Access(access);
		}
		
		try {
			transaction = session.beginTransaction();
			session.save(access);
			transaction.commit();
		}
		catch (HibernateException e) {
			
			if (logger.isErrorEnabled()) {
				logger.error("Exception while inserting access", e);
			}
			
			if (transaction != null) {
				transaction.rollback();
			}
			
		}
		finally {
			
			if (session != null) {
				session.close();
			}
			
		}

	}

}

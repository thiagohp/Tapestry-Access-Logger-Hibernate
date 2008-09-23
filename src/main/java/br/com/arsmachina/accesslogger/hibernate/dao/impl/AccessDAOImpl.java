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

package br.com.arsmachina.accesslogger.hibernate.dao.impl;


import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.arsmachina.accesslogger.hibernate.Access;
import br.com.arsmachina.accesslogger.hibernate.dao.AccessDAO;

/**
 * {@link AccessDAO} implementation using Hibernate.
 * 
 * @author Thiago H. de Paula Figueiredo (ThiagoHP)
 */
final public class AccessDAOImpl implements AccessDAO {

	private Logger logger = LoggerFactory.getLogger(AccessDAOImpl.class);
	
	private SessionFactory sessionFactory;

	/**
	 * Single constructor of this class.
	 * 
	 * @param sessionFactory a {@link SessionFactory}. It cannot be null.
	 */
	public AccessDAOImpl(SessionFactory sessionFactory) {

		if (sessionFactory == null) {
			throw new IllegalArgumentException("Parameter sessionfFactory cannot be null");
		}

		this.sessionFactory = sessionFactory;

	}

	/**
	 * @see br.com.arsmachina.accesslogger.hibernate.dao.AccessDAO#save(br.com.arsmachina.accesslogger.hibernate.Access)
	 */
	public void save(Access access) {
		
		final Session session = sessionFactory.openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(access);
			transaction.commit();
		}
		catch (HibernateException e) {
			if (logger.isErrorEnabled()) {
				logger.error("Exception while inserting access", e);
			}
		}
		finally {
			
			if (transaction != null) {
				transaction.rollback();
			}
			
			if (session != null) {
				session.close();
			}
			
		}
		
	}

}

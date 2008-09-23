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

import br.com.arsmachina.accesslogger.Access;
import br.com.arsmachina.accesslogger.AccessLogger;
import br.com.arsmachina.accesslogger.hibernate.controller.AccessController;

/**
 * {@link AccessLogger} implementation using Hibernate to persist {@link Access} instances in a
 * relationational database.
 * 
 * @author Thiago H. de Paula Figueiredo (ThiagoHP)
 */
public class HibernateAccessLogger implements AccessLogger {

	private AccessController controller;

	/**
	 * Single constructor of this class.
	 * 
	 * @param controller an {@link AccessController}.
	 */
	public HibernateAccessLogger(AccessController controller) {
		this.controller = controller;
	}

	/**
	 * @see br.com.arsmachina.accesslogger.AccessLogger#log(br.com.arsmachina.accesslogger.Access)
	 */
	public void log(Access access) {
		 controller.save(new br.com.arsmachina.accesslogger.hibernate.Access(access));
	}

}

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

package br.com.arsmachina.accesslogger.hibernate.controller.impl;

import br.com.arsmachina.accesslogger.hibernate.Access;
import br.com.arsmachina.accesslogger.hibernate.controller.AccessController;
import br.com.arsmachina.accesslogger.hibernate.dao.AccessDAO;

/**
 * {@link AccessController} for {@link Access}.
 * 
 * @author Thiago H. de Paula Figueiredo (ThiagoHP)
 */
public class AccessControllerImpl implements AccessController {

	private AccessDAO dao;

	/**
	 * Single constructor of this class.
	 * 
	 * @param dao an {@link AccessDAO}.
	 */
	public AccessControllerImpl(AccessDAO dao) {

		if (dao == null) {
			throw new IllegalArgumentException("Parameter dao cannot be null");
		}

		this.dao = dao;

	}

	/**
	 * Invokes <code>delegate.save()<code>.
	 * @param access
	 * @see br.com.arsmachina.accesslogger.hibernate.dao.AccessDAO#save(br.com.arsmachina.accesslogger.hibernate.Access)
	 */
	public void save(Access access) {
		dao.save(access);
	}

}

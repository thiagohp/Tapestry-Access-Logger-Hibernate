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

import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.hibernate.SessionFactory;

import br.com.arsmachina.accesslogger.AccessLogger;

/**
 * Tapestry-IoC module class.
 * 
 * @author Thiago H. de Paula Figueiredo
 */
public class TapestryAccessLoggerHibernateModule {
	
	/**
	 * Declares some objects to IoC.
	 * 
	 * @param binder a {@link ServiceBinder}.
	 */
	public static void bind(ServiceBinder binder) {
		binder.bind(HibernateAccessLogger.class);
	}

	public void contributeAccessLoggerHub(OrderedConfiguration<AccessLogger> configuration,
			SessionFactory sessionFactory, HibernateAccessLogger hibernateAccessLogger) {
		configuration.add("hibernate", hibernateAccessLogger);
	}

}

package org.rapidoid;

/*
 * #%L
 * rapidoid-commons
 * %%
 * Copyright (C) 2014 - 2016 Nikolche Mihajlovski and contributors
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.Since;
import org.rapidoid.cls.Cls;
import org.rapidoid.u.U;

import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.Set;

@Authors("Nikolche Mihajlovski")
@Since("5.3.0")
public class RapidoidModules extends RapidoidThing {

	public static Set<RapidoidModule> getAll() {
		return all(false);
	}

	public static Set<RapidoidModule> getAllAvailable() {
		return all(true);
	}

	public static Set<RapidoidModule> all(boolean availableOnly) {
		Set<RapidoidModule> modules = U.set();

		Iterator<RapidoidModule> it = ServiceLoader.load(RapidoidModule.class).iterator();

		while (it.hasNext()) {
			RapidoidModule mod;

			if (availableOnly) {
				try {
					mod = it.next();
				} catch (ServiceConfigurationError e) {
					mod = null;
					// ignore it
				}
			} else {
				mod = it.next();
			}

			if (mod != null) {
				modules.add(mod);
			}
		}

		for (String clsName : Cls.getRapidoidClasses()) {
			if (clsName.endsWith("Module")) {
				Class<?> cls = Cls.getClassIfExists(clsName);

				if (cls != null && RapidoidModule.class.isAssignableFrom(cls) && !cls.isInterface()) {
					modules.add((RapidoidModule) Cls.newInstance(cls));
				}
			}
		}

		return modules;
	}

}
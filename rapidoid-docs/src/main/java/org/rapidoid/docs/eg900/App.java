package org.rapidoid.docs.eg900;

import org.rapidoid.annotation.Order;
import org.rapidoid.app.Screen;
import org.rapidoid.quick.Quick;

/*
 * #%L
 * rapidoid-docs
 * %%
 * Copyright (C) 2014 - 2015 Nikolche Mihajlovski
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

// Ordering the screens :: Foo is second, Bar is first:

public class App {
	String title = "Custom screen order";
	String theme = "1";

	public static void main(String[] args) {
		Quick.run(args);
	}
}

@Order(2)
// here
class FooScreen extends Screen {}

@Order(1)
// here
class BarScreen extends Screen {}

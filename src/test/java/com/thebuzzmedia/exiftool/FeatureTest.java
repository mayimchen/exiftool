/**
 * Copyright 2011 The Buzz Media, LLC
 * Copyright 2015 Mickael Jeanroy <mickael.jeanroy@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thebuzzmedia.exiftool;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FeatureTest {

	@Test
	public void it_should_have_feature_stay_open_and_check_if_it_is_supported() {
		Feature feature = Feature.STAY_OPEN;
		assertThat(feature.toString()).isEqualTo("STAY_OPEN");

		assertThat(feature.isSupported(null)).isFalse();

		// Should be ok
		assertThat(feature.isSupported("8.36")).isTrue();
		assertThat(feature.isSupported("9.0.1")).isTrue();

		// Should be ko
		assertThat(feature.isSupported("8.35")).isFalse();
		assertThat(feature.isSupported("8.3.4")).isFalse();
	}
}
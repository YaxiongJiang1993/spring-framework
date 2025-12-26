/*
 * Copyright 2002-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.davih.test;

import com.davih.domain.Person;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ConversionServiceFactoryBean;

import java.beans.PropertyEditor;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class for circular dependency handling.
 *
 * @author Yaxio
 */
@Configuration
@ComponentScan(value = "com.davih.test",
		excludeFilters = {@ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, value = D.class)},
		includeFilters = {@ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, value = C.class)}
)
public class TestConfiguration {

	//	@Bean
	public ConversionServiceFactoryBean conversionServiceFactoryBean() {
		ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
		conversionServiceFactoryBean.setConverters(Collections.singleton(new StringToUserConverter()));

		return conversionServiceFactoryBean;
	}

	//	@Bean
	public ApplicationListener applicationListener() {
		return new ApplicationListener() {
			@Override
			public void onApplicationEvent(ApplicationEvent event) {
				System.out.println("接收到了一个事件" + event);
			}
		};
	}

	@Bean
	public CustomEditorConfigurer customEditorConfigurer() {

		CustomEditorConfigurer customEditorConfigurer = new CustomEditorConfigurer();

		Map<Class<?>, Class<? extends PropertyEditor>> propertyEditorMap = new HashMap<>();
		propertyEditorMap.put(Person.class, StringToUserPropertiesEditor.class);

		customEditorConfigurer.setCustomEditors(propertyEditorMap);

		return customEditorConfigurer;
	}
}

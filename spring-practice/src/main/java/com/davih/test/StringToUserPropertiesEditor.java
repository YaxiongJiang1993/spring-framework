package com.davih.test;

import com.davih.domain.Person;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

/**
 * from jdk
 */
public class StringToUserPropertiesEditor extends PropertyEditorSupport implements PropertyEditor {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Person person = new Person();
		person.setName(text);
		super.setValue(person);
	}
}

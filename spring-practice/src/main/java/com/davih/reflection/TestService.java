package com.davih.reflection;

public class TestService {

	private String hostName;

	private String address;

	private Integer age;

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPath() {
		return hostName.concat("/").concat(address);
	}
}

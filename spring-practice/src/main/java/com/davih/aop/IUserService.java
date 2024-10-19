package com.davih.aop;

public class IUserService implements IUser {

	@Override
	public void test() {
		System.out.println(" IUserService test ... ");
	}

	@Override
	public void a() {
		System.out.println(" IUserService a ... ");
	}
}

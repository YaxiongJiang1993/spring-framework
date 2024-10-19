package com.davih.aop;

import org.springframework.stereotype.Service;

@Service
public class IAccountImpl implements IAccount{

	@Override
	public void xxx() {
		System.out.println("aop add xxx ");
	}
}

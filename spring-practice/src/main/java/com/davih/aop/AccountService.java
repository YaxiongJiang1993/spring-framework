package com.davih.aop;

import com.davih.life.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yaxio
 */
@Service
public class AccountService {

//	@Autowired
	private IndexService indexService;

	public AccountService() {
		System.out.println("AccountService ... ");
	}

	public void test(){
		System.out.println(" account test ... ");
	}

	public void a(){
		System.out.println(" account a ... ");
	}
}

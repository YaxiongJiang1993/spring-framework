package com.davih.listen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yaxio
 */
@Service
public class AccountService {

	@Autowired
	private IndexService indexService;

	public AccountService() {
		System.out.println("AccountService ... ");
	}
}

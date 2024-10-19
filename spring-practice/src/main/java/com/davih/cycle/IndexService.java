package com.davih.cycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author Yaxio
 */
@Service
public class IndexService {

	@Autowired
	private AccountService accountService;

	public IndexService() {
		System.out.println("IndexService ... ");
	}

	@PostConstruct
	public void init(){
		System.out.println("indexService init ... ");
	}
}

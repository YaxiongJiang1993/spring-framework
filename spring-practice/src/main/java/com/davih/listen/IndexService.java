package com.davih.listen;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author Yaxio
 */
@Service
public class IndexService {


	public IndexService() {
		System.out.println("IndexService ... ");
	}

	@PostConstruct
	public void init(){
		System.out.println("indexService init ... ");
	}
}

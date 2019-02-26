/**
 *
 */
package com.yueny.example.elasticsearch.manager.impl;

import com.yueny.example.elasticsearch.manager.IUserLoginManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class UserLoginManagerImpl implements IUserLoginManager {

	@Override
	public boolean login(final String userName) {
		//
		return true;
	}

}

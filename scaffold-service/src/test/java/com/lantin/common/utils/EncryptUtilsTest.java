package com.lantin.common.utils;

import org.junit.Test;

import java.security.Provider;
import java.security.Security;



public class EncryptUtilsTest {


	@Test
	public void test(){
		Provider[] providers = Security.getProviders();

		for (Provider provider : providers) {
			System.out.println(provider.getName());
		}
	}
}
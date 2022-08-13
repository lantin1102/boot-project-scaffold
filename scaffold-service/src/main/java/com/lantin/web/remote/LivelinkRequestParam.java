package com.lantin.web.remote;


import lombok.Data;

import java.util.UUID;

@Data
public class LivelinkRequestParam {

	String apiName;
	String actId;
	String gameId;
	/**
	 * 平台用户登录态加密
	 */
	String code;

	String livePlatId = "bilibili";

	String logintype = "qq";

	String t = String.valueOf(System.currentTimeMillis() / 1000);

	String v = "2.0";

	String nonce = UUID.randomUUID().toString().substring(0, 8);


	public static LivelinkRequestParam create(){
		return new LivelinkRequestParam();
	}

	public LivelinkRequestParam apiName(String apiName){
		this.apiName = apiName;
		return this;
	}
	public LivelinkRequestParam actId(String actId){
		this.actId = actId;
		return this;
	}
	public LivelinkRequestParam gameId(String gameId){
		this.gameId = gameId;
		return this;
	}
	public LivelinkRequestParam code(String code){
		this.code = code;
		return this;
	}

}

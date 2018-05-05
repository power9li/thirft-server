package com.power.learn.thrift.thrift_server;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TException;

import com.power.learn.thrift.gen.Request;
import com.power.learn.thrift.gen.RequestException;
import com.power.learn.thrift.gen.RequestType;
import com.power.learn.thrift.gen.HelloWordService;

public class HelloWordServiceImpl implements HelloWordService.Iface {

	
	@Override
	public String doAction(Request request) throws RequestException, TException {
		System.out.println("Get request: " + request);
		if (StringUtils.isBlank(request.getName()) || request.getType() == null) {
			throw new RequestException();
		}
		String result = "Hello, " + request.getName();
		if (request.getType() == RequestType.SAY_HELLO) {
			result += ", Welcome!";
		} else {
			result += ", Now is " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		}
		return result;
	}

}

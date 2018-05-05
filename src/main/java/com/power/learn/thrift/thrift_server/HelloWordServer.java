package com.power.learn.thrift.thrift_server;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

import com.power.learn.thrift.gen.HelloWordService;
import com.power.learn.thrift.gen.HelloWordService.Processor;

public class HelloWordServer {

	private static HelloWordServiceImpl handler;
	private static HelloWordService.Processor<HelloWordServiceImpl> processor;

	public static void main(String[] args) {

		try {
			handler = new HelloWordServiceImpl();
			processor = new HelloWordService.Processor<HelloWordServiceImpl>(handler);

			Runnable simple = new Runnable() {
				public void run() {
					simple(processor);
				}
			};
			new Thread(simple).start();
		} catch (Exception x) {
			x.printStackTrace();
		}

	}

	private static void simple(Processor<HelloWordServiceImpl> processor) {
		try {
			TServerTransport serverTransport = new TServerSocket(9090);
			TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));

			// Use this for a multithreaded server
			// TServer server = new TThreadPoolServer(new
			// TThreadPoolServer.Args(serverTransport).processor(processor));

			System.out.println("Starting the simple server...");
			server.serve();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

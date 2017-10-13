package com.xuanwu.xtion.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
  
/** 
 * 单线程任务的线程池 
 * 好处：当池中的线程死掉了，会再创建一个线程出来继续执行，保证总是有一个线程在执行。 
 */  
public class SingleThreadPoolUtil{
	
	private SingleThreadPoolUtil(){}
	
    //定义一个单线程任务的线程池  
    static ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();  
      
    public static void putInPool(Runnable clazz){
		try{
			singleThreadPool.execute(clazz);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

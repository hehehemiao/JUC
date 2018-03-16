package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 
 * @Description: 
 * 现在两个线程，
 * 可以操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1，
 * 交替，来10轮，变量初始值为零。
 * @author zzyy
 * @date 2018年3月15日
 * 1 多线程编写套路------上
 * 		1.1	线程		操作(实例方法)		资源类
 * 		1.2  高内聚  低耦合
 * 
 * 2 多线程编写套路------下
 * 		2.1 判断
 * 		2.2 干活
 * 		2.3 通知
 */
class ShareData{
	private int number =0;
	private Lock lock =new ReentrantLock();
	private Condition condition=lock.newCondition();
	public  void increment() throws Exception{
		lock.lock();
		//1.判断
		//if(number!=0){
		try {
			while(number!=0){
				condition.await();
				//this.wait();
			}
			//2.干活
			++number;
			System.out.println("当前number值是:"+number);
			//3.通信
			
			condition.signalAll();
			//this.notifyAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	public synchronized void decrement() throws Exception{
		
		lock.lock();
		//1.判断
		//if(number!=0){
		try {
			while(number==0){
				condition.await();//this.await();
			}
			//2.干活
			--number;
			System.out.println("当前number值是:"+number);
			//3.通信
			
			condition.signalAll();//this.notifyAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
}

public class NotifyWaitDemo {
	public static void main(String[] args) {
		
	
		ShareData sd = new ShareData();
		new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				try {
					sd.increment();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "AA").start();
		
		new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				try {
					sd.decrement();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "BB").start();
		
		new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				try {
					sd.increment();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "CC").start();
		
		new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				try {
					sd.decrement();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "DD").start();
		
	}
}

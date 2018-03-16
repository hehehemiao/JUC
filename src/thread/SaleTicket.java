package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{//资源类 类=实例变量+实例方法
	private int number=30;
	private Lock lock=new ReentrantLock();
	public void sale(){
		lock.lock();
		try {
			if(number>0){
				System.out.println(Thread.currentThread().getName()+"卖出"+(--number)
						+"还剩"+number+"张票");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			lock.unlock();
		}
	}
}

/**
 * 第一个case:
 * 三个售票员卖出30张票
 * @author pc
 *1.多线程编写套路--------上
 *		1.1 线程    操作(实例方法)   资源类
 *		1.2 高内聚  低耦合
 */
public class SaleTicket {

	
	public static void main(String[] args) {
		
		Ticket ticket =new Ticket();
		
		
		new Thread(() -> {for (int i=1 ; i<=40 ; i++) ticket.sale();},"AA").start();
		new Thread(() -> {for (int i=1 ; i<=40 ; i++) ticket.sale();},"BB").start();
		new Thread(() -> {for (int i=1 ; i<=40 ; i++) ticket.sale();},"CC").start();
		new Thread(() -> {for (int i=1 ; i<=40 ; i++) {
			ticket.sale();
		}},"your thread name").start();
		
		/*
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i=1 ; i<=40 ; i++) {
					ticket.sale();
				}
			}
		},"AA").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				
			}
		},"your thread name").start();
		new Thread(new Runnable() {
		
			@Override
			public void run() {
				for (int i=1 ; i<=40 ; i++) {
					ticket.sale();
				}
			}
		},"CC").start();*/
		
	}
}

package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{//��Դ�� ��=ʵ������+ʵ������
	private int number=30;
	private Lock lock=new ReentrantLock();
	public void sale(){
		lock.lock();
		try {
			if(number>0){
				System.out.println(Thread.currentThread().getName()+"����"+(--number)
						+"��ʣ"+number+"��Ʊ");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			lock.unlock();
		}
	}
}

/**
 * ��һ��case:
 * ������ƱԱ����30��Ʊ
 * @author pc
 *1.���̱߳�д��·--------��
 *		1.1 �߳�    ����(ʵ������)   ��Դ��
 *		1.2 ���ھ�  �����
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

package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 
 * @Description: 
 * ���������̣߳�
 * ���Բ�����ʼֵΪ���һ��������
 * ʵ��һ���̶߳Ըñ�����1��һ���̶߳Ըñ�����1��
 * ���棬��10�֣�������ʼֵΪ�㡣
 * @author zzyy
 * @date 2018��3��15��
 * 1 ���̱߳�д��·------��
 * 		1.1	�߳�		����(ʵ������)		��Դ��
 * 		1.2  ���ھ�  �����
 * 
 * 2 ���̱߳�д��·------��
 * 		2.1 �ж�
 * 		2.2 �ɻ�
 * 		2.3 ֪ͨ
 */
class ShareData{
	private int number =0;
	private Lock lock =new ReentrantLock();
	private Condition condition=lock.newCondition();
	public  void increment() throws Exception{
		lock.lock();
		//1.�ж�
		//if(number!=0){
		try {
			while(number!=0){
				condition.await();
				//this.wait();
			}
			//2.�ɻ�
			++number;
			System.out.println("��ǰnumberֵ��:"+number);
			//3.ͨ��
			
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
		//1.�ж�
		//if(number!=0){
		try {
			while(number==0){
				condition.await();//this.await();
			}
			//2.�ɻ�
			--number;
			System.out.println("��ǰnumberֵ��:"+number);
			//3.ͨ��
			
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

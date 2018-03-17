package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 
 * @Description: 
 * ���߳�֮�䰴˳����ã�ʵ��A->B->C
 * �����߳�������Ҫ�����£�
 * 
 * AA��ӡ5�Σ�BB��ӡ10�Σ�CC��ӡ15��
 * ����
 * AA��ӡ5�Σ�BB��ӡ10�Σ�CC��ӡ15��
 * ......��10��  
 * @author hemiao
 * @date 2018��3��17��
 */
class Share{
	private int num=1;//1-->a   2-->b
	private Lock lock =new ReentrantLock();
	private Condition c1=lock.newCondition();
	private Condition c2=lock.newCondition();
	private Condition c3=lock.newCondition();
	public void print5(int Loop){
		lock.lock();
		try {
			//�ж�
			while(num!=1){
				c1.await();
			}
			//�ɻ�
			for (int i = 1; i <=5; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+"Loop:"+Loop);
			}
			//ͨ��
			num=2;
			c2.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	public void print10(int Loop){
		lock.lock();
		try {
			//�ж�
			while(num!=2){
				c2.await();
			}
			//�ɻ�
			for (int i = 1; i <=10; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+"Loop:"+Loop);
			}
			//ͨ��
			num=3;
			c3.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	public void print15(int Loop){
		lock.lock();
		try {
			//�ж�
			while(num!=3){
				c3.await();
			}
			//�ɻ�
			for (int i = 1; i <=15; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+"Loop:"+Loop);
			}
			//ͨ��
			num=1;
			c1.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
}
public class ThreadOrder {

	public static void main(String[] args) {
		Share s=new Share();
		new Thread(() -> {

			for (int i = 1; i <=10; i++) {
				s.print5(i);
			}
		}, "AA").start();
		new Thread(() -> {

			for (int i = 1; i <=10; i++) {
				s.print10(i);
			}
		}, "BB").start();
		new Thread(() -> {

			for (int i = 1; i <=10; i++) {
				s.print15(i);
			}
		}, "CC").start();
	}
	
}

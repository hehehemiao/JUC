package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AddNumAndLatter{
	private int num=0;
	private char latter='A'-1;
	private Lock lock=new ReentrantLock();
	Condition condition =lock.newCondition();
	public void addNum(){
		lock.lock();
		try {
			//1.panduan
			while(num!=52){
				
			
			//2.ganhuo
			++num;
			
			System.out.print(num);
			++num;
			System.out.print(num);
			//3.tongxin
			condition.signalAll();
			condition.await();
			}
		} catch (Exception e) {

		} finally {
			lock.unlock();
		}
	}
	public void addLatter(){
		lock.lock();
		try {
			//1.panduan
			while(latter!='Z'){
				
				System.out.println(++latter);
				condition.signalAll();
				if(latter=='Z'){
					return;
				}
				condition.await();
			}
			//2.ganhuo
//			System.out.println(++latter);
//			//3.tongxin
//			condition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
public class ThreadTest {

	public static void main(String[] args) {
		AddNumAndLatter ad=new AddNumAndLatter();
		new Thread(() -> {
			
				ad.addNum();
			
		}, "AA").start();
		new Thread(() -> {
			
				ad.addLatter();
			
		}, "BB").start();
	}
}

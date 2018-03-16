package thread;


@FunctionalInterface
interface Foo{
//	public void sayHello();
	
	public int add(int x , int y);  
	
	public static int  div(int x,int y){
		return x/y;
	}
	default int sub (int x, int y){
		return x-y;
	}
}
/**
 * 1.����������+д���Ҽ�ͷ+��ش�����
 * 2.һ���ӿ��������ҽ���һ�������Ľӿ�,�ſ���ʹ��Lamda Express
 * 3.@functionInterface
 * 4.defaultĬ�Ϸ���ʵ��
 * 5.��̬����ʵ��
 * @author pc
 *
 */
public class LambaDemo {
	public static void main(String[] args) {
		
//		Foo foo = new Foo(){
//
//			@Override
//			public void sayHello() {
//				System.out.println("*********hello 1018");
//			}
//			
//		};
//		foo.sayHello();
//		Foo foo = () -> {
//			System.out.println("*********hello 1018 lambda");
//		};
//		foo.sayHello();
		Foo foo=(x,y) ->{return x+y;};
		
		System.out.println(foo.add(5, 11));
		
		int result =foo.sub(10, 2);
		System.out.println(result);
		System.out.println("------------------------------");
		result=Foo.div(10, 2);
		System.out.println(result);
	}
}

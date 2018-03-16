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
 * 1.拷贝中括号+写死右箭头+落地大括号
 * 2.一个接口里面有且仅有一个方法的接口,才可以使用Lamda Express
 * 3.@functionInterface
 * 4.default默认方法实现
 * 5.静态方法实现
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

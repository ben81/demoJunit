package demo.junit;

import java.util.function.Consumer;
/**
 * Hello world!
 *
 */
public class App 
{
	
	public static class TestMe{
		
		
		public void run() {
			
		}
	}
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        TestMe t = new TestMe();
        test((Consumer<TestMe> ) ((t0) ->t0.run()) );
        
        
    }

	public static <T extends Object> void test(Consumer<T> object) {
		System.out.println(object.getClass());
		Class clazz= object.getClass();
		System.out.println( clazz.getDeclaredMethods());
		
	}
}

package Thinking;

public class ClassLoader {
	public static void main(String[] args)
    {
        System.out.println(SubClass.value);
    }
}
 class SSClass
{
    static
    {
        System.out.println("SSClass static." );
    }
    public SSClass() {
    	System.out.println("SSClass constructor.");
    }
}    
 class SuperClass extends SSClass
{
    static
    {
        System.out.println("SuperClass static.");
    }
 
    public static int value = 123;
 
    public SuperClass()
    {
    	System.out.println("SuperClass constructor.");
    }
}
 class SubClass extends SuperClass
{
    static
    {
        System.out.println("SubClass static");
    }
 
    static int a;
 
    public SubClass()
    {
    	System.out.println("SubClass constructor.");
    }
}


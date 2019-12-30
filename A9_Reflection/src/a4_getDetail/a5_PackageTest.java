package a4_getDetail;

import org.junit.Test;

public class a5_PackageTest {
    @Test
    public void testPackage(){
        Class<Person> personClass = Person.class;
        Package aPackage = personClass.getPackage();
        System.out.println(aPackage.getName());
    }
}

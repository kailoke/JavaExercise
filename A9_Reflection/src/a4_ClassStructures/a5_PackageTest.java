package a4_ClassStructures;

import org.junit.Test;

/** 获得运行时类的包名信息
 *  > Package getPackage() : 运行时类的包名
 */

public class a5_PackageTest {
    @Test
    public void testPackage(){
        Class<Person> personClass = Person.class;
        Package aPackage = personClass.getPackage();
        System.out.println(aPackage.getName());
    }
}

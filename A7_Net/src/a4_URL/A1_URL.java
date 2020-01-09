package a4_URL;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

/** URL Uniform Resource Locator 统一资源定位符
 * 一、URL:表示Internet上某一资源的地址
 *      > 它是一种具体的URL，可以用来标识资源，而且还指明了如何locate此资源
 *      > 通过 URL 我们可以访问 Internet 上的各种网络资源
 * 二、URL结构组成
 *  <传输协议> :// <主机名> : <端口号> / <文件名> # 片段名 ? 参数列表
 *  > #片段名：即锚点，例如看小说，直接定位到章节
 *  > 参数列表格式：参数名=参数值&参数名=参数值.... (Properties + &)
 *
 * 三、URL构造器，都声明抛出非运行时异常，需要try-catch处理
 *  > public URL (String spec)：通过一个表示URL地址的字符串可以构造一个URL对象
 *  > public URL(URL context, String spec)：通过基 URL 和相对 URL 构造一个 URL 对象
 *  > public URL(String protocol, String host, String file)
 *  > public URL(String protocol, String host, int port, String file)
 *
 * 四、常用方法
 * public String getProtocol( ) 获取该URL的协议名
 * public String getHost( ) 获取该URL的主机名
 * public String getPort( ) 获取该URL的端口号
 * public String getPath( ) 获取该URL的文件路径
 * public String getFile( ) 获取该URL的文件名
 * public String getQuery( ) 获取该URL的查询名
 *
 * 五、区别
 * > URI Identifier 统一资源标识符 标识资源                    抽象的，高层次
 * > URL Locator 统一资源定位符    标识资源，并指明如何定位资源  具体的资源标识方式，对URI的扩展
 * > URN Name 统一资源命名         通过名字标识资源            具体的资源标识方式，对URI的扩展
 */


public class A1_URL {
    @Test
    public void constructor() {
        URL url = null;
        try {
            url = new URL("Http://localhost:32123/main/index.jsp?username=fff&password=ddd");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        assert url != null;
        System.out.println("path: " + url.getPath());
        System.out.println("file: " + url.getFile());
        System.out.println("query: " + url.getQuery());
    }
}

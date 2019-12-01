package a4_URL;

import java.net.MalformedURLException;
import java.net.URL;

/** URL Uniform Resource Locator
 * 一、格式
 *  > 通信协议 :// 主机名(域名) : 端口号 / 资源地址 # 片段名 ? 参数列表
 *
 */
public class URLTest {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("Http://localhost:32123/main/index.jsp");
    }
}

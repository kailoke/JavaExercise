package a0_Bean;

import java.sql.Date;

/**
 * 一、ORM (Object Relation Map): 对象关系映射 编程思想
 *  > 一个数据表对应一个java类
 *  > 表中的一条记录对应java类的一个对象
 *  > 表中的一个字段对应java类的一个字段
 *
 * 二、JavaBean ：可被整个工程访问
 *  > public 类
 *  > public 无参构造器 --> 反射
 *  > public set()/get() + 对应属性
 */

// DB:test
public class Customers {
    private int id;
    private String name;
    private String email;
    private Date birth;

    public Customers() {
    }

    public Customers(String name, String email, Date birth) {
        this.name = name;
        this.email = email;
        this.birth = birth;
    }

    public Customers(int id, String name, String email, Date date) {
        this(name,email,date);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                '}';
    }
}

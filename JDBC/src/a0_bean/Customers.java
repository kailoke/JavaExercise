package a0_bean;

import java.sql.Date;

/*
    ORM (Object Relation Map): 对象关系映射 编程思想
    > 一个数据表对应一个java类
    > 表中的一条记录对应java类的一个对象
    > 表中的一哥字段对应java类的一个字段
 */
public class Customers {
    public int id;
    public String name;
    public String email;
    public java.sql.Date date;

    public Customers(int id, String name, String email, String birth) {
    }

    public Customers(int id, String name, String email, Date date) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", date=" + date +
                '}';
    }
}

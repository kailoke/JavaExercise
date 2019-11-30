package DAOTest;

import java.util.*;

public class DAO<T> {
    private Map<String,T> map = new HashMap<>();

    public void save(String id,T entity){
        map.put(id,entity);
    }
    public T get(String id){
        return map.get(id);
    }

    // 更新
    public void update(String id,T entity){
        if (map.containsKey(id)){
            map.put(id,entity);
        }
    }

    // 返回map中存放的所有T对象
    public List<T> getList(){
        Collection<T> values = map.values();
        // 错误的 父类对象不可强转为子类引用
//        return (List<T>) values;
        List<T> list = new ArrayList<>(values);
        return list;
    }

    // 删除指定id的对象
    public T delete(String id){
        return map.remove(id);
    }
}

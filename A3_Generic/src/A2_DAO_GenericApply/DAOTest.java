package A2_DAO_GenericApply;

import java.util.List;

public class DAOTest {
    public static void main(String[] args) {
        DAO<User> dao = new DAO<User>();

        dao.save("1",new User(1001,34,"周杰伦"));
        dao.save("2",new User(1002,40,"方文山"));
        dao.save("3",new User(1003,25,"昆凌"));

        List<User> list = dao.getList();
        System.out.println(list);

        dao.update("2",new User(1004,30,"蔡依林"));
    }
}

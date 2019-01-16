import com.syj.mybatis.dao.UserDao;
import com.syj.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * @author snailshen
 * @description test mybatis
 * @create 2019-01-15 14:52
 */
public class UserDaoTest {
    @Test
    public void findUserById() {
        SqlSession sqlSession = getSessionFactory().openSession();
        UserDao userMapper = sqlSession.getMapper(UserDao.class);
        User tmpUser = new User(2,"syj","123",30,1);
        insert(userMapper,tmpUser);
        sqlSession.commit();
        User user = userMapper.findUserById(1);
        Assert.assertNotNull("没找到数据", user);
    }

    private void insert(UserDao dao,User user) {
        dao.addUser(user);

    }
    //Mybatis 通过SqlSessionFactory获取SqlSession, 然后才能通过SqlSession与数据库进行交互
    private static SqlSessionFactory getSessionFactory() {
        SqlSessionFactory sessionFactory = null;
        String resource = "configuration.xml";
        try {
            sessionFactory = new SqlSessionFactoryBuilder().build(Resources
                    .getResourceAsReader(resource));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessionFactory;


    }
}

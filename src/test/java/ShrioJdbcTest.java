import com.yicj.shiro.hello.ShiroHelper;
import org.junit.Test;

public class ShrioJdbcTest {

    @Test
    public void testJdbc1(){
        ShiroHelper helper = new ShiroHelper("classpath:shiro_jdbc.ini") ;
        helper.checkLogin("yicj","123");
        helper.logout();
    }

    @Test
    public void testJdbc2(){
        ShiroHelper helper = new ShiroHelper("classpath:shiro_jdbc_sql.ini") ;
        helper.checkLogin("yicj","123");
        helper.logout();
    }

    @Test
    public void testJdbc3(){
        //https://www.cnblogs.com/zerotomax/p/7420100.html
        ShiroHelper helper = new ShiroHelper("classpath:shiro_jdbc_my_sql.ini") ;
        helper.checkLogin("yicj","123");
        helper.logout();
    }
}

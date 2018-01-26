import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

public class daotest {

	
	public static void main (String[] args) {
		UserVo userVo = new UserVo();
		userVo.setName("유재석");
		userVo.setPassword("1234");
		userVo.setEmail("유재석");
		userVo.setGender("유재석");
		
		UserDao userDao = new UserDao();
		userDao.insert(userVo);
		
	}
}

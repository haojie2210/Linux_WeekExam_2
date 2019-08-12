package lianghaojie_redis_test;
/**
 * 
 * @ClassName: Redis_JDK_Test 
 * @Description: TODO
 * @author:单击开始
 * @date: 2019年8月12日 上午8:58:05
 */

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.lianghaojie_redis_test.domain.Employee;
import com.yangchunbo.common.utils.DateUtil;
import com.yangchunbo.common.utils.RandomUitl;
import com.yangchunbo.common.utils.StringUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class Redis_HashJDK_Test {
	
	@Resource
	private RedisTemplate<String, Serializable> redisTemplate;
	@Test
	public void test_insert() {
		Map<String, Object> map = new HashMap<String, Object>();
		String sex[] = {"男","女"};
		String email[] = {"@qq.com","@163.com","@sian.com","@gmail.com","@sohu.com","@hotmail.com"};
		for (int p = 1; p < 100000; p++) {
			map.put("e1", new Employee(p, StringUtil.generateChineseName()+StringUtil.randomChineseString(1), 
					sex[RandomUitl.random(0, 1)],"13"+RandomUitl.random(0, 8),RandomUitl.randomString(RandomUitl.random(3, 20))+email[RandomUitl.random(0, 5)],
					DateUtil.randomDate(new Date(System.currentTimeMillis()))));
		}
		long startTimes = System.currentTimeMillis();
		//for (Employee e : map) {
			redisTemplate.opsForHash().putAll("employee", map);
		//}
		long endTimes = System.currentTimeMillis();
		System.out.println("采用了Hash,JDK序列化的方式存储了10万条数据一共耗时："+(endTimes-startTimes)+"mm");
	}
}

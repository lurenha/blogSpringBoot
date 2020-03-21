package com.peng.springboot_one;

import com.peng.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootOneApplicationTests {
    @Test
    public void test01() {
        Object[] arrs=new Object[]{"123","456",null};
        StringBuffer sb=new StringBuffer();
        for (Object ob : arrs) {
            sb.append(ob);
        }
        System.out.println(sb.toString());

    }

}

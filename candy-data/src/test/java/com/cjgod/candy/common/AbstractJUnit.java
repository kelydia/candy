package com.cjgod.candy.common;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/12/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/conf/spring.xml","classpath*:/conf/spring-mybatis.xml"})
//------------如果加入以下代码，所有继承该类的测试类都会遵循该配置，也可以不加，在测试类的方法上///控制事务，参见下一个实例
//这个非常关键，如果不加入这个注解配置，事务控制就会完全失效！
@Transactional(value = "transactionManager")
//------------
/*@TestExecutionListeners(listeners={
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})*/
public abstract class AbstractJUnit extends AbstractTransactionalJUnit4SpringContextTests{
}
package kr.co.coinis.webserver.framework.core.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.util.Assert;
/**
 * 
 * <pre>
 * 1.  기능		: Default Database 연결 
 * 2.  처리개요	: 
 * 3.  주의사항	:  
 * 4.  작성자/작성일	: kim/2016. 5. 10.
 * ====================================
 * 5.  수정사항
 * - 수정자/수정일 	: 
 * - 수정사유/내역 	:
 * ====================================
 * <p/>
 * @author		: kim
 * @version		: v1.0
 * @see			: 
 * @since		: J2EE 1.7
 * </pre>
 */
public class BaseSessionMapper extends SqlSessionDaoSupport {
	
	/**
	 * Annotation 형식으로 sqlSession(SqlSessionFactoryBean)을 받아와
	 * 이를 super(SqlSessionDaoSupport)의 setSqlSessionFactory 메서드를 호출하여 설정해 준다.
	 * <p>
	 * SqlSessionTemplate이 지정된 경우된 경우 이 SqlSessionFactory가 무시된다. (Batch 처리를 위해서는 SqlSessionTemplate가 필요)
	 * </p>
	 *
	 * @param sqlSession SqlSessionFactory로 MyBatis와의 연계를 위한 기본 클래스
	 */
	@Resource(name="sqlSession")
	public void setSqlSessionFactory(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	public SqlSessionTemplate getSqlSessionTemplate() {
		Assert.isInstanceOf(SqlSessionTemplate.class, getSqlSession(), "SqlSessionTemplate isn't instance of SqlSession");
		return (SqlSessionTemplate) getSqlSession();
	}

}

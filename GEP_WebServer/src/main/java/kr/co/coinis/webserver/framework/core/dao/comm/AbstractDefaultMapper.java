package kr.co.coinis.webserver.framework.core.dao.comm;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import kr.co.coinis.webserver.framework.core.dao.BaseSessionMapper;

/**
 * Spring의 MyBatis 연동 지원 공통 parent DAO 클래스.
 *
 * @author Vincent Han
 * @since 2.6
 *
 *<pre>
 *	수정일	수정자	수정내용	
 *  ------------   --------    ---------------------------
 *   2014.01.22		권윤정		ResultHandler이용을 위한 listToOutUsingResultHandler() 메서드 추가
 *   2014.07.11 	이기하  	selectByPk Deprecated 및 getSqlSession 메소드 추가
 *</pre>
 */
public abstract class AbstractDefaultMapper extends BaseSessionMapper {
	
	public abstract String getNamespace();
	
	/**
	 * 입력 처리 SQL mapping 을 실행한다.
	 *
	 * @param queryId -  입력 처리 SQL mapping 쿼리 ID
	 *
	 * @return DBMS가 지원하는 경우 insert 적용 결과 count
	 */
	public int insert(String queryId) {
		return this.getSqlSessionTemplate().insert(queryId);
	}

	/**
	 * 입력 처리 SQL mapping 을 실행한다.
	 *
	 * @param queryId -  입력 처리 SQL mapping 쿼리 ID
	 * @param parameterObject - 입력 처리 SQL mapping 입력 데이터를 세팅한 파라메터 객체(보통 VO 또는 Map)
	 *
	 * @return DBMS가 지원하는 경우 insert 적용 결과 count
	 */
	public int insert(String queryId, Object parameterObject) {
		return this.getSqlSessionTemplate().insert(queryId, parameterObject);
	}

	/**
	 * 수정 처리 SQL mapping 을 실행한다.
	 *
	 * @param queryId - 수정 처리 SQL mapping 쿼리 ID
	 *
	 * @return DBMS가 지원하는 경우 update 적용 결과 count
	 */
	public int update(String queryId) {
		return this.getSqlSessionTemplate().update(queryId);
	}

	/**
	 * 수정 처리 SQL mapping 을 실행한다.
	 *
	 * @param queryId - 수정 처리 SQL mapping 쿼리 ID
	 * @param parameterObject - 수정 처리 SQL mapping 입력 데이터(key 조건 및 변경 데이터)를 세팅한 파라메터 객체(보통 VO 또는 Map)
	 *
	 * @return DBMS가 지원하는 경우 update 적용 결과 count
	 */
	public int update(String queryId, Object parameterObject) {
		return this.getSqlSessionTemplate().update(queryId, parameterObject);
	}

	/**
	 * 삭제 처리 SQL mapping 을 실행한다.
	 *
	 * @param queryId - 삭제 처리 SQL mapping 쿼리 ID
	 *
	 * @return DBMS가 지원하는 경우 delete 적용 결과 count
	 */
	public int delete(String queryId) {
		return this.getSqlSessionTemplate().delete(queryId);
	}

	/**
	 * 삭제 처리 SQL mapping 을 실행한다.
	 *
	 * @param queryId - 삭제 처리 SQL mapping 쿼리 ID
	 * @param parameterObject - 삭제 처리 SQL mapping 입력 데이터(일반적으로 key 조건)를  세팅한 파라메터 객체(보통 VO 또는 Map)
	 *
	 * @return DBMS가 지원하는 경우 delete 적용 결과 count
	 */
	public int delete(String queryId, Object parameterObject) {
		return this.getSqlSessionTemplate().delete(queryId, parameterObject);
	}

	/**
	 * 단건조회 처리 SQL mapping 을 실행한다.
	 *
	 * @param queryId - 단건 조회 처리 SQL mapping 쿼리 ID
	 *
	 * @return 결과 객체 - SQL mapping 파일에서 지정한 resultType/resultMap 에 의한 단일 결과 객체(보통 VO 또는 Map)
	 */
	public <T> T selectOne(String queryId) {
		return this.getSqlSessionTemplate().selectOne(queryId);
	}

	/**
	 * 단건조회 처리 SQL mapping 을 실행한다.
	 *
	 * @param queryId - 단건 조회 처리 SQL mapping 쿼리 ID
	 * @param parameterObject - 단건 조회 처리 SQL mapping 입력 데이터(key)를 세팅한 파라메터 객체(보통 VO 또는 Map)
	 *
	 * @return 결과 객체 - SQL mapping 파일에서 지정한 resultType/resultMap 에 의한 단일 결과 객체(보통 VO 또는 Map)
	 */
	public <T> T selectOne(String queryId, Object parameterObject) {
		return this.getSqlSessionTemplate().selectOne(queryId, parameterObject);
	}

	/**
	 * 결과 목록을 Map 을 변환한다.
	 * 모든 구문이 파라미터를 필요로 하지는 않기 때문에, 파라미터 객체를 요구하지 않는 형태로 오버로드되었다.
	 *
	 * @param queryId - 단건 조회 처리 SQL mapping 쿼리 ID
	 * @param mapKey - 결과 객체의 프로퍼티 중 하나를 키로 사용
	 *
	 * @return 결과 객체 - SQL mapping 파일에서 지정한 resultType/resultMap 에 의한 단일 결과 객체(보통 VO 또는 Map)의 Map
	 */
	public <K, V> Map<K, V> selectMap(String queryId, String mapKey) {
		return this.getSqlSessionTemplate().selectMap(queryId, mapKey);
	}

	/**
	 * 결과 목록을 Map 을 변환한다.
	 * 모든 구문이 파라미터를 필요로 하지는 않기 때문에, 파라미터 객체를 요구하지 않는 형태로 오버로드되었다.
	 *
	 * @param queryId - 단건 조회 처리 SQL mapping 쿼리 ID
	 * @param parameterObject - 맵 조회 처리 SQL mapping 입력 데이터(조회 조건)를 세팅한 파라메터 객체(보통 VO 또는 Map)
	 * @param mapKey - 결과 객체의 프로퍼티 중 하나를 키로 사용
	 *
	 * @return 결과 객체 - SQL mapping 파일에서 지정한 resultType/resultMap 에 의한 단일 결과 객체(보통 VO 또는 Map)의 Map
	 */
	public <K, V> Map<K, V> selectMap(String queryId, Object parameterObject, String mapKey) {
		return this.getSqlSessionTemplate().selectMap(queryId, parameterObject, mapKey);
	}

	/**
	 * 결과 목록을 Map 을 변환한다.
	 * 모든 구문이 파라미터를 필요로 하지는 않기 때문에, 파라미터 객체를 요구하지 않는 형태로 오버로드되었다.
	 *
	 * @param queryId - 단건 조회 처리 SQL mapping 쿼리 ID
	 * @param parameterObject - 맵 조회 처리 SQL mapping 입력 데이터(조회 조건)를 세팅한 파라메터 객체(보통 VO 또는 Map)
	 * @param mapKey - 결과 객체의 프로퍼티 중 하나를 키로 사용
	 * @param rowBounds - 특정 개수 만큼의 레코드를 건너띄게 함
	 *
	 * @return 결과 객체 - SQL mapping 파일에서 지정한 resultType/resultMap 에 의한 단일 결과 객체(보통 VO 또는 Map)의 Map
	 */
	public <K, V> Map<K, V> selectMap(String queryId, Object parameterObject, String mapKey, RowBounds rowBounds) {
		return this.getSqlSessionTemplate().selectMap(queryId, parameterObject, mapKey, rowBounds);
	}

	/**
	 * 리스트 조회 처리 SQL mapping 을 실행한다.
	 *
	 * @param queryId - 리스트 조회 처리 SQL mapping 쿼리 ID
	 *
	 * @return 결과 List 객체 - SQL mapping 파일에서 지정한  resultType/resultMap 에 의한 결과 객체(보통 VO 또는 Map)의 List
	 */
	public <E> List<E> selectList(String queryId) {
		return this.getSqlSessionTemplate().selectList(queryId);
	}

	/**
	 * 리스트 조회 처리 SQL mapping 을 실행한다.
	 *
	 * @param queryId - 리스트 조회 처리 SQL mapping 쿼리 ID
	 * @param parameterObject - 리스트 조회 처리 SQL mapping 입력 데이터(조회 조건)를 세팅한 파라메터 객체(보통 VO 또는 Map)
	 *
	 * @return 결과 List 객체 - SQL mapping 파일에서 지정한  resultType/resultMap 에 의한 결과 객체(보통 VO 또는 Map)의 List
	 */
	public <E> List<E> selectList(String queryId, Object parameterObject) {
		return this.getSqlSessionTemplate().selectList(queryId, parameterObject);
	}

	/**
	 * 리스트 조회 처리 SQL mapping 을 실행한다.
	 *
	 * @param queryId - 리스트 조회 처리 SQL mapping 쿼리 ID
	 * @param parameterObject - 리스트 조회 처리 SQL mapping 입력 데이터(조회 조건)를 세팅한 파라메터 객체(보통 VO 또는 Map)
	 * @param rowBounds - 특정 개수 만큼의 레코드를 건너띄게 함
	 *
	 * @return 결과 List 객체 - SQL mapping 파일에서 지정한  resultType/resultMap 에 의한 결과 객체(보통 VO 또는 Map)의 List
	 */
	public <E> List<E> selectList(String queryId, Object parameterObject, RowBounds rowBounds) {
		return this.getSqlSessionTemplate().selectList(queryId, parameterObject, rowBounds);
	}

	/**
	 * 부분 범위 리스트 조회 처리 SQL mapping 을 실행한다.
	 * (부분 범위 - pageIndex 와 pageSize 기반으로 현재 부분 범위 조회를 위한 skipResults, maxResults 를 계산하여 ibatis 호출)
	 *
	 * @param queryId - 리스트 조회 처리 SQL mapping 쿼리 ID
	 * @param parameterObject - 리스트 조회 처리 SQL mapping 입력 데이터(조회 조건)를 세팅한 파라메터 객체(보통 VO 또는 Map)
	 * @param pageIndex - 현재 페이지 번호
	 * @param pageSize - 한 페이지 조회 수(pageSize)
	 *
	 * @return 부분 범위 결과 List 객체 - SQL mapping 파일에서 지정한 resultType/resultMap 에 의한 부분 범위 결과 객체(보통 VO 또는 Map) List
	 */
	public List<?> listWithPaging(String queryId, Object parameterObject, int pageIndex, int pageSize) {
		int skipResults = pageIndex * pageSize;
		//int maxResults = (pageIndex * pageSize) + pageSize;

		RowBounds rowBounds = new RowBounds(skipResults, pageSize);

		return this.getSqlSessionTemplate().selectList(queryId, parameterObject, rowBounds);
	}

	/**
	 * SQL 조회 결과를 ResultHandler를 이용해서 출력한다.
	 * ResultHandler를 상속해 구현한 커스텀 핸들러의 handleResult() 메서드에 따라 실행된다.
	 *
	 * @param queryId - 리스트 조회 처리 SQL mapping 쿼리 ID
	 * @param handler - 조회 결과를 제어하기 위해 구현한 ResultHandler
	 * @return
	 *
	 * @return 결과 List 객체 - SQL mapping 파일에서 지정한 resultType/resultMap 에 의한 결과 객체(보통 VO 또는 Map)의 List
	 */
	public void listToOutUsingResultHandler(String queryId, ResultHandler handler) {
		this.getSqlSessionTemplate().select(queryId, handler);
	}
	
	
}
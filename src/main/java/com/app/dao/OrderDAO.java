package com.app.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;

import com.app.dto.OrderDTO;
import com.app.mybatis.config.MyBatisConfig;
import com.app.vo.OrderVO;

public class OrderDAO {
	public SqlSession sqlSession;
	
	public OrderDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
//	상품 주문
	public void insert(OrderVO orderVO) {
		sqlSession.insert("order.insert", orderVO);
	}
	
//	구매자 주문 내역
	public List<OrderDTO> selectByMember(Long memberId) {
		return sqlSession.selectList("order.selectByMember", memberId);
	}
	
//	주문 수량 조회
	public int selectById(Long id) {
		return sqlSession.selectOne("order.selectById", id);
	}
	
//	주문 삭제
	public void delete(Long id) {
		sqlSession.delete("order.delete", id);
	}

//	판매자 주문 내역
	public List<OrderDTO> selectBySeller(Long sellerId){
		return sqlSession.selectList("order.selectBySeller", sellerId);
	}

//	오더 조회
	public Optional<OrderVO> select(Long id) {
		return Optional.ofNullable(sqlSession.selectOne("order.select", id));
	}
	
//	날짜별 주문 총내역
    public List<OrderDTO> selectByDate(Long sellerId, String startDate, String endDate) {
        Map<String, Object> params = new HashMap<>();
        params.put("sellerId", sellerId);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        
        return sqlSession.selectList("order.selectByDate", params);
    }
    
//    마이홈 주문 조회
    public List<OrderDTO> selectMyOrder(Long memberId) {
    	return sqlSession.selectList("order.selectMyOrder", memberId);
    }
}

	

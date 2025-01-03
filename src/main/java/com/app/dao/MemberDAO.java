package com.app.dao;


import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.app.dto.MemberDTO;
import com.app.mybatis.config.MyBatisConfig;
import com.app.vo.MemberVO;
import com.app.vo.SellerVO;



public class MemberDAO {
	public static SqlSession sqlSession;
	
	public MemberDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	
//	구매자 회원가입
	public void insertBuyer(MemberVO memberVO) {
		sqlSession.insert("member.insertBuyer", memberVO);
	}
		
//	구매자 로그인
	public String selectBuyer(MemberVO memberVO) {
		return sqlSession.selectOne("member.selectBuyer", memberVO);
	}

//	구매자 계정 삭제
	public void deleteBuyer(Long id) {
		sqlSession.delete("member.deleteBuyer", id);
	}
	
// 구매자 정보 수정
	public void updateBuyer(MemberVO memberVO) {
		sqlSession.update("member.updateBuyer", memberVO);
	}	
	
// 로그아웃
	public void logout(HttpSession session) {
		if (session != null) {
			session.invalidate(); 
		}
	}
	
//	판매자 회원가입
	public void insertSeller(SellerVO sellerVO) {
		sqlSession.insert("member.insertSeller", sellerVO);
	}
	
	
//	판매자 로그인
	public String selectSeller(SellerVO sellerVO) {
		return sqlSession.selectOne("member.selectSeller", sellerVO);
	}

	
// 판매자 정보 수정
	public void updateSeller(SellerVO sellerVO) {
		sqlSession.update("member.updateSeller", sellerVO);
	}	
	
	
//	판매자 계정 삭제
	public void deleteSeller(Long id) {
		sqlSession.delete("member.deleteSeller", id);
	}

	
	
//	구매자 이메일 찾기
	public Long findBuyerByEmail(String email) {
		return sqlSession.selectOne("member.findBuyerByEmail", email);
	}
	
//	아이디 찾기 페이지에서 구매자 이메일 찾기
	public String buyerFindEmail(String email) {
		return sqlSession.selectOne("member.buyerFindEmail", email);
	}

	
//	특정 멤버 정보 조회
	public Optional<MemberVO> select(Long id) {
		return Optional.ofNullable(sqlSession.selectOne("member.select", id));
	}
	
	// 일반회원 전체 조회
	public static List<MemberVO> selectAll() {
	    return sqlSession.selectList("member.selectAll");
	}

	// 검색 전체 조회
	public List<MemberDTO> selectSearch(MemberDTO memberDTO) {
	    return sqlSession.selectList("member.selectSearch", memberDTO);
	}
	
	// 수신 동의
	public void updateNotification(MemberVO memberVO) {
		sqlSession.selectList("member.updateNotification", memberVO);
	}
	
}


package kr.co.bit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.bit.member.vo.MemberVO;
import kr.co.bit.util.DBUtil;

/**
 * 
 * @author 임규철
 */
public class MemberDAO {

	/**
	 * 
	 * @return
	 */
	public List<MemberVO> selectAll() {
		List<MemberVO> members = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT ID, NAME, EMAIL_ID, EMAIL_DOMAIN, TEL1, TEL2, TEL3, POST, BASIC_ADDR, DETAIL_ADDR FROM T_MEMBER");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());
				ResultSet rs = pst.executeQuery();) {

			while (rs.next()) {
				MemberVO member = new MemberVO();
				member.setId(rs.getString("ID"));
				member.setName(rs.getString("NAME"));
				member.setEmailId(rs.getString("EMAIL_ID"));
				member.setEmailDomain(rs.getString("EMAIL_DOMAIN"));
				member.setTel1(rs.getString("TEL1"));
				member.setTel2(rs.getString("TEL2"));
				member.setTel3(rs.getString("TEL3"));
				member.setPost(rs.getString("POST"));
				member.setBasicAddr(rs.getString("BASIC_ADDR"));
				member.setDetailAddr(rs.getString("DETAIL_ADDR"));
				members.add(member);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return members;
	}

	/**
	 * 
	 * @param member
	 * @return
	 */
	public MemberVO login(MemberVO member) {
		MemberVO loginUser = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID, PASSWORD, NAME, EMAIL_ID, EMAIL_DOMAIN, TEL1, TEL2, TEL3, POST, BASIC_ADDR, DETAIL_ADDR, TYPE, REG_DATE ");
		sql.append(" FROM T_MEMBER WHERE ID = ? AND PASSWORD = ?");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {

			pst.setString(1, member.getId());
			pst.setString(2, member.getPassword());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				loginUser = new MemberVO();
				loginUser.setId(rs.getString("ID"));
				loginUser.setPassword(rs.getString("PASSWORD"));
				loginUser.setName(rs.getString("NAME"));
				loginUser.setEmailId(rs.getString("EMAIL_ID"));
				loginUser.setEmailDomain(rs.getString("EMAIL_DOMAIN"));
				loginUser.setTel1(rs.getString("TEL1"));
				loginUser.setTel2(rs.getString("TEL2"));
				loginUser.setTel3(rs.getString("TEL3"));
				loginUser.setPost(rs.getString("POST"));
				loginUser.setBasicAddr(rs.getString("BASIC_ADDR"));
				loginUser.setDetailAddr(rs.getString("DETAIL_ADDR"));
				loginUser.setRegDate(rs.getString("REG_DATE"));
				loginUser.setType(rs.getString("TYPE"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return loginUser;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public int isExistId(String id) {
		int exist = 0;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM T_MEMBER WHERE ID = ?");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {

			pst.setString(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				exist = rs.getInt(1);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return exist;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public MemberVO selectById(String id) {
		MemberVO member = null;
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT ID, NAME, EMAIL_ID, EMAIL_DOMAIN, TEL1, TEL2, TEL3, POST, BASIC_ADDR, DETAIL_ADDR FROM T_MEMBER WHERE ID = ?");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {

			pst.setString(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				member = new MemberVO();
				member.setId(rs.getString("ID"));
				member.setName(rs.getString("NAME"));
				member.setEmailId(rs.getString("EMAIL_ID"));
				member.setEmailDomain(rs.getString("EMAIL_DOMAIN"));
				member.setTel1(rs.getString("TEL1"));
				member.setTel2(rs.getString("TEL2"));
				member.setTel3(rs.getString("TEL3"));
				member.setPost(rs.getString("POST"));
				member.setBasicAddr(rs.getString("BASIC_ADDR"));
				member.setDetailAddr(rs.getString("DETAIL_ADDR"));

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return member;
	}

	/**
	 * 
	 * @param member
	 * @return
	 */
	public int insert(MemberVO member) {
		int result = 0;
		StringBuilder sql = new StringBuilder();
		sql.append(
				"insert into T_MEMBER(ID, NAME, PASSWORD, EMAIL_ID, EMAIL_DOMAIN, TEL1, TEL2, TEL3, POST, BASIC_ADDR, DETAIL_ADDR) ");
		sql.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {
			pst.setString(1, member.getId());
			pst.setString(2, member.getName());
			pst.setString(3, member.getPassword());
			pst.setString(4, member.getEmailId());
			pst.setString(5, member.getEmailDomain());
			pst.setString(6, member.getTel1());
			pst.setString(7, member.getTel2());
			pst.setString(8, member.getTel3());
			pst.setString(9, member.getPost());
			pst.setString(10, member.getBasicAddr());
			pst.setString(11, member.getDetailAddr());
			result = pst.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return result;
	}

}

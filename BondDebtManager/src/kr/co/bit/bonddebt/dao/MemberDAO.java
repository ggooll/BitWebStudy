package kr.co.bit.bonddebt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.bit.bonddebt.util.DBUtil;
import kr.co.bit.bonddebt.vo.Member;

public class MemberDAO {

	private static final int PAGE_CNT = 12;

	/**
	 * 
	 * @return List<Member>
	 */
	public List<Member> selectAllFriend(int memberNo) {
		List<Member> friendList = new ArrayList<>();

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT NO, ID, NAME, TEL, EMAIL ");
		sql.append(" FROM T_MEMBER ");
		sql.append(" WHERE NO IN(SELECT FRIEND_NO FROM T_FRIEND WHERE MEMBER_NO = ?) ");
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {

			pst.setInt(1, memberNo);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Member member = new Member();
				member.setNo(rs.getInt("NO"));
				member.setId(rs.getString("ID"));
				member.setName(rs.getString("NAME"));
				member.setTel(rs.getString("TEL"));
				member.setEmail(rs.getString("EMAIL"));
				System.out.println(member);
				friendList.add(member);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return friendList;
	}

	/**
	 * 
	 * @param id
	 *        ȸ�� ���̵�
	 * @return List<Member>
	 */
	public List<Member> selectFriends(int memberNo, int pageNum) {
		List<Member> friendList = new ArrayList<>();

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT NO, ID, NAME, TEL, EMAIL ");
		sql.append(
				" FROM (SELECT ROWNUM RN, A.* FROM ( SELECT * FROM T_MEMBER WHERE NO IN (SELECT FRIEND_NO FROM T_FRIEND WHERE MEMBER_NO = ?)) A) ");
		sql.append(" WHERE RN BETWEEN ? AND ? ");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {

			pst.setInt(1, memberNo);
			pst.setInt(2, PAGE_CNT * (pageNum - 1) + 1);
			pst.setInt(3, PAGE_CNT * (pageNum));
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Member member = new Member();
				member.setNo(rs.getInt("NO"));
				member.setId(rs.getString("ID"));
				member.setName(rs.getString("NAME"));
				member.setTel(rs.getString("TEL"));
				member.setEmail(rs.getString("EMAIL"));
				System.out.println(member);
				friendList.add(member);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return friendList;
	}

	/**
	 * 
	 * @param memberNo
	 * @return
	 */
	public int selectFriendMaxCount(int memberNo) {
		int count = 0;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) AS MAXCOUNT FROM T_FRIEND WHERE MEMBER_NO = ? ");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {
			pst.setInt(1, memberNo);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				count = rs.getInt("MAXCOUNT");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 
	 * @param no
	 * @return
	 */
	public Member selectByNo(int no) {
		Member member = null;
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT NO, ID, NAME, TEL, EMAIL, BALANCE ");
		sql.append(" FROM T_MEMBER WHERE NO = ? ");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {

			pst.setInt(1, no);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				member = new Member();
				member.setNo(rs.getInt("NO"));
				member.setId(rs.getString("ID"));
				member.setName(rs.getString("NAME"));
				member.setTel(rs.getString("TEL"));
				member.setEmail(rs.getString("EMAIL"));
				member.setBalance(rs.getInt("BALANCE"));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return member;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Member selectById(String id) {
		Member member = null;
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT NO, ID, NAME, TEL, EMAIL, BALANCE ");
		sql.append(" FROM T_MEMBER WHERE ID = ? ");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {

			pst.setString(1, id);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				member = new Member();
				member.setNo(rs.getInt("NO"));
				member.setId(rs.getString("ID"));
				member.setName(rs.getString("NAME"));
				member.setTel(rs.getString("TEL"));
				member.setEmail(rs.getString("EMAIL"));
				member.setBalance(rs.getInt("BALANCE"));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return member;
	}

	/**
	 * �α���
	 * 
	 * @param id
	 * @param password
	 * @return
	 */
	public Member login(String id, String password) {
		Member loginUser = null;

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT NO, ID, NAME, TEL, EMAIL ");
		sql.append(" FROM T_MEMBER ");
		sql.append(" WHERE ID = ? AND PASSWORD = ?");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {

			pst.setString(1, id);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				loginUser = new Member();
				loginUser.setNo(rs.getInt("NO"));
				loginUser.setId(rs.getString("ID"));
				loginUser.setName(rs.getString("NAME"));
				loginUser.setTel(rs.getString("TEL"));
				loginUser.setEmail(rs.getString("EMAIL"));
				System.out.println(loginUser);
			}

		} catch (Exception e) {
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
	public Member apiLogin(String id) {
		Member loginUser = null;

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT NO, ID, NAME, TEL, EMAIL ");
		sql.append(" FROM T_MEMBER ");
		sql.append(" WHERE ID = ? ");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {

			pst.setString(1, id);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				loginUser = new Member();
				loginUser.setNo(rs.getInt("NO"));
				loginUser.setId(rs.getString("ID"));
				loginUser.setName(rs.getString("NAME"));
				loginUser.setTel(rs.getString("TEL"));
				loginUser.setEmail(rs.getString("EMAIL"));
				System.out.println(loginUser);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return loginUser;
	}

	/**
	 * 
	 * @param memberNo
	 * @param friendNo
	 * @return
	 */
	public int insertFriend(int memberNo, int friendNo) {
		int result = 0;

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO T_FRIEND ");
		sql.append(" VALUES (?, ?)");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement st = conn.prepareStatement(sql.toString());) {
			int loc = 1;
			st.setInt(loc++, memberNo);
			st.setInt(loc++, friendNo);

			result = st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 
	 * @param member
	 * @return effected DB row 1/0
	 */
	public int insert(Member member) {
		int result = 0;
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO T_MEMBER(NO, ID, PASSWORD, NAME, TEL, EMAIL, BALANCE) ");
		sql.append(" VALUES(T_MEMBER_SEQ.nextval, ?, ?, ?, ?, ?, 0) ");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {

			pst.setString(1, member.getId());
			pst.setString(2, member.getPassword());
			pst.setString(3, member.getName());
			pst.setString(4, member.getTel());
			pst.setString(5, member.getEmail());

			result = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 
	 * @param member
	 * @return effected DB row 1/0
	 */
	public int update(String column, Member member) {

		return 0;
	}

	/**
	 * 
	 * @param flag
	 * @param no
	 * @param money
	 * @return
	 */
	public int updateMoney(boolean flag, int no, int money) {
		int result = 0;
		String check = flag ? "+" : "-";

		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE T_MEMBER SET BALANCE = BALANCE " + check + " ? ");
		sql.append(" WHERE NO = ? ");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {
			pst.setInt(1, money);
			pst.setInt(2, no);
			result = pst.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}

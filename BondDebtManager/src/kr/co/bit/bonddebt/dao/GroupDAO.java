package kr.co.bit.bonddebt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.bit.bonddebt.util.DBUtil;
import kr.co.bit.bonddebt.vo.Group;

public class GroupDAO {

	/**
	 * 
	 * @param no
	 * @return
	 */
	public Group selectGroupByNo(int no) {
		Group group = new Group();
		String selectFriendNo =
				"SELECT MEMBER_NO, NAME FROM T_G_MEMBER_LIST, T_GROUP WHERE GROUP_NO = ? AND NO = ?";
		String selectFriendName = "SELECT NAME FROM T_MEMBER WHERE NO = ?";

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(selectFriendNo);
				PreparedStatement fps = conn.prepareStatement(selectFriendName);) {
			ps.setInt(1, no);
			ps.setInt(2, no);
			ResultSet frs = ps.executeQuery();
			group.setNo(no);

			ArrayList<String> friendNameList = new ArrayList<>();
			while (frs.next()) {
				int friendNo = frs.getInt(1);
				String groupName = frs.getString(2);
				group.setName(groupName);

				fps.setInt(1, friendNo);
				ResultSet fnrs = fps.executeQuery();
				while (fnrs.next()) {
					String friendName = fnrs.getString(1);
					friendNameList.add(friendName);
				}
				group.setMembersList(friendNameList);
				group.setMemberCount(friendNameList.size());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("group : " + group.toString());
		return group;

	}

	/**
	 * 
	 * @param memberNo
	 * @return
	 */
	public List<Group> selectGroupListByNo(int memberNo) {
		List<Group> groupList = new ArrayList<>();

		String selectGroupNo = "SELECT GROUP_NO FROM T_G_MEMBER_LIST WHERE MEMBER_NO = ?";
		String selectFriendNo =
				"SELECT MEMBER_NO, NAME FROM T_G_MEMBER_LIST, T_GROUP WHERE GROUP_NO = ? AND NO = ?";
		String selectFriendName = "SELECT NAME FROM T_MEMBER WHERE NO = ?";

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(selectGroupNo);
				PreparedStatement ps = conn.prepareStatement(selectFriendNo);
				PreparedStatement fps = conn.prepareStatement(selectFriendName);) {
			pstmt.setInt(1, memberNo);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Group group = new Group();
				int groupNo = rs.getInt(1);
				group.setNo(groupNo);

				ps.setInt(1, groupNo);
				ps.setInt(2, groupNo);

				ResultSet frs = ps.executeQuery();
				ArrayList<String> friendNameList = new ArrayList<>();
				while (frs.next()) {
					int friendNo = frs.getInt(1);
					String groupName = frs.getString(2);
					group.setName(groupName);

					fps.setInt(1, friendNo);
					ResultSet fnrs = fps.executeQuery();
					while (fnrs.next()) {
						String friendName = fnrs.getString(1);
						friendNameList.add(friendName);
					}
					group.setMembersList(friendNameList);
					group.setMemberCount(friendNameList.size());
				}
				groupList.add(group);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return groupList;
	}

	/**
	 * 
	 * @return
	 */
	public int selectNo() {
		int groupNo = 0;
		String sql = "SELECT T_GROUP_SEQ.NEXTVAL FROM DUAL";

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {

			ResultSet rs = pst.executeQuery();
			rs.next();
			groupNo = rs.getInt(1);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return groupNo;
	}

	/**
	 * 
	 * @param groupNo
	 * @param memberList
	 * @return
	 */
	public int insertMembers(int groupNo, ArrayList<String> memberList) {
		int result = 0;
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO T_G_MEMBER_LIST (GROUP_NO, MEMBER_NO) ");
		sql.append(" VALUES(?, ?) ");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {

			for (String memberNo : memberList) {
				int loc = 1;
				pst.setInt(loc++, groupNo);
				pst.setInt(loc++, Integer.parseInt(memberNo));
				result = pst.executeUpdate();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 
	 * @param groupNo
	 * @param memberNo
	 * @return
	 */
	public List<Integer> selectMemberByNo(int groupNo, int memberNo) {
		List<Integer> result = new ArrayList<Integer>();
		String sql = "SELECT MEMBER_NO FROM T_G_MEMBER_LIST WHERE GROUP_NO = ?";

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, groupNo);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int friendNo = rs.getInt(1);
				if (friendNo != memberNo) {
					result.add(friendNo);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println(result);

		return result;

	}

	/**
	 * 
	 * @param groupNo
	 * @param groupName
	 * @return
	 */
	public int insert(int groupNo, String groupName) {

		int result = 0;
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO T_GROUP (NO, NAME, REG_DATE) ");
		sql.append(" VALUES(?, ?, SYSDATE) ");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {
			int loc = 1;
			pst.setInt(loc++, groupNo);
			pst.setString(loc++, groupName);

			result = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return result;
	}

}

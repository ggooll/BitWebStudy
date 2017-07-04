package kr.co.bit.bonddebt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.bit.bonddebt.util.DBUtil;
import kr.co.bit.bonddebt.vo.Deal;

public class DealDAO {

	private static final int PAGE_CNT = 12;

	/**
	 * 
	 * @param id
	 * @return List<Deal>
	 */
	public List<Deal> selectAll(int memberNo, int pageNum) {
		List<Deal> dealList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT NO, SENDER_NO, RECEIVER_NO, ");
		sql.append(" (SELECT ID FROM T_MEMBER M WHERE SENDER_NO = M.NO) AS SENDER_ID, ");
		sql.append(" (SELECT ID FROM T_MEMBER M WHERE RECEIVER_NO = M.NO) AS RECEIVER_ID, ");
		sql.append(" MONEY, TO_CHAR(START_DATE, 'YYYY/MM/DD HH:MI:SS') AS START_DATE, COMMENTS ");
		sql.append(
				" FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM T_DEAL_LIST WHERE SENDER_NO = ? OR RECEIVER_NO = ? ORDER BY START_DATE DESC) A) ");
		sql.append(" WHERE RN BETWEEN ? AND ?");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {

			pst.setInt(1, memberNo);
			pst.setInt(2, memberNo);
			pst.setInt(3, PAGE_CNT * (pageNum - 1) + 1);
			pst.setInt(4, PAGE_CNT * (pageNum));
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Deal deal = new Deal();
				deal.setNo(rs.getInt("NO"));
				deal.setSenderNo(rs.getInt("SENDER_NO"));
				deal.setReceiverNo(rs.getInt("RECEIVER_NO"));
				deal.setSenderId(rs.getString("SENDER_ID"));
				deal.setReceiverId(rs.getString("RECEIVER_ID"));
				deal.setMoney(rs.getInt("MONEY"));
				deal.setStartDate(rs.getString("START_DATE"));
				deal.setComments(rs.getString("COMMENTS"));
				dealList.add(deal);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return dealList;
	}

	/**
	 * 
	 * @param memberNo
	 * @return
	 */
	public int selectMaxCount(int memberNo) {
		int count = 0;
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT COUNT(*) AS MAXCOUNT FROM T_DEAL_LIST WHERE SENDER_NO = ? OR RECEIVER_NO = ? ");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {
			pst.setInt(1, memberNo);
			pst.setInt(2, memberNo);
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
	 * @param deal
	 * @return effected DB row 1/0
	 */
	public int insert(Deal deal) {
		int result = 0;
		StringBuilder sql = new StringBuilder();
		sql.append(
				" INSERT INTO T_DEAL_LIST(NO, SENDER_NO, RECEIVER_NO, COMMENTS, MONEY, START_DATE) ");
		sql.append(" VALUES(T_DEAL_LIST_SEQ.nextVal, ?, ?, ?, ?, sysdate)");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {
			pst.setInt(1, deal.getSenderNo());
			pst.setInt(2, deal.getReceiverNo());
			pst.setString(3, deal.getComments());
			pst.setInt(4, deal.getMoney());

			result = pst.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 
	 * @param deal
	 * @return effected DB row 1/0
	 */
	public int delete(Deal deal) {
		int result = 0;
		StringBuffer sql = new StringBuffer();
		sql.append(" DELETE FROM T_DEAL_LIST ");
		sql.append(" WHERE NO = ? ");
		System.out.println("DEAL " + deal);

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {

			pst.setInt(1, deal.getNo());
			result = pst.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 
	 * @return
	 */
	public List<Deal> selectSendDeal(int memberNo) {
		// String sql = "SELECT id, (SELECT NAME FROM T_MEMBER M WHERE RECEIVER_NO = M.NO) AS
		// RECEIVER_NAME, money FROM T_DEAL_LIST WHERE SENDER_NO = ?";
		List<Deal> sendDealList = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append(
				" SELECT (SELECT NAME FROM T_MEMBER M WHERE RECEIVER_NO = M.NO) AS RECEIVER_NAME, MONEY ");
		sql.append(" FROM T_DEAL_LIST ");
		sql.append(" WHERE SENDER_NO = ? ");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {
			pst.setInt(1, memberNo);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				Deal deal = new Deal();
				deal.setReceiverId(rs.getString("RECEIVER_NAME"));
				deal.setMoney(rs.getInt("MONEY"));
				sendDealList.add(deal);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return sendDealList;
	}

	/**
	 * 
	 * @param memberNo
	 * @return
	 */
	public List<Deal> selectTop5(int memberNo) {
		List<Deal> dealList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT NO, SENDER_NO, RECEIVER_NO, ");
		sql.append(" (SELECT ID FROM T_MEMBER M WHERE SENDER_NO = M.NO) AS SENDER_ID, ");
		sql.append(" (SELECT ID FROM T_MEMBER M WHERE RECEIVER_NO = M.NO) AS RECEIVER_ID, ");
		sql.append(" MONEY, TO_CHAR(START_DATE, 'YYYY/MM/DD HH:MI:SS') AS START_DATE, COMMENTS ");
		sql.append(
				" FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM T_DEAL_LIST WHERE SENDER_NO = ? ORDER BY START_DATE DESC) A) ");
		sql.append(" WHERE RN <= 5");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pst = conn.prepareStatement(sql.toString());) {

			pst.setInt(1, memberNo);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Deal deal = new Deal();
				deal.setNo(rs.getInt("NO"));
				deal.setSenderNo(rs.getInt("SENDER_NO"));
				deal.setReceiverNo(rs.getInt("RECEIVER_NO"));
				deal.setSenderId(rs.getString("SENDER_ID"));
				deal.setReceiverId(rs.getString("RECEIVER_ID"));
				deal.setMoney(rs.getInt("MONEY"));
				deal.setStartDate(rs.getString("START_DATE"));
				deal.setComments(rs.getString("COMMENTS"));
				dealList.add(deal);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return dealList;
	}

}

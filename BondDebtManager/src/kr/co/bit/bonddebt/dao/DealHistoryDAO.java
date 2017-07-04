package kr.co.bit.bonddebt.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.bit.bonddebt.util.DBUtil;
import kr.co.bit.bonddebt.util.DateUtil;
import kr.co.bit.bonddebt.vo.Deal;
import kr.co.bit.bonddebt.vo.DealHistory;

public class DealHistoryDAO {

	private static final int PAGE_CNT = 12;

	/**
	 * 회원의 완료된 거래내역을 모두 조회합니다.
	 * 
	 * @param memberNo
	 * @return
	 */
	public List<DealHistory> selectAll(int memberNo, int pageNum) {
		List<DealHistory> dealHistories = new ArrayList<DealHistory>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT NO, ");
		sql.append(" (SELECT ID FROM T_MEMBER M WHERE SENDER_NO = M.NO) AS SENDER_ID, ");
		sql.append(" (SELECT ID FROM T_MEMBER M WHERE RECEIVER_NO = M.NO) AS RECEIVER_ID, ");
		sql.append(" MONEY, TO_CHAR(START_DATE, 'YYYY/MM/DD HH:MI:SS') AS START_DATE, END_DATE, COMMENTS ");
		sql.append(" FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM T_DEAL_HISTORY WHERE SENDER_NO = ? OR RECEIVER_NO = ? ORDER BY END_DATE DESC) A) ");
		sql.append(" WHERE RN BETWEEN ? AND ?");

		try (Connection conn = DBUtil.getConnection(); PreparedStatement pst = conn.prepareStatement(sql.toString());) {

			pst.setInt(1, memberNo);
			pst.setInt(2, memberNo);
			pst.setInt(3, PAGE_CNT * (pageNum - 1) + 1);
			pst.setInt(4, PAGE_CNT * (pageNum));
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				DealHistory dealHistory = new DealHistory();
				dealHistory.setNo(rs.getInt("NO"));
				dealHistory.setSenderId(rs.getString("SENDER_ID"));
				dealHistory.setReceiverId(rs.getString("RECEIVER_ID"));
				dealHistory.setMoney(rs.getInt("MONEY"));
				dealHistory.setStartDate(rs.getString("START_DATE"));
				dealHistory.setEndDate(rs.getString("END_DATE"));
				dealHistory.setComments(rs.getString("COMMENTS"));
				dealHistories.add(dealHistory);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return dealHistories;
	}

	/**
	 * 회원의 완료된 거래내역의 총 갯수를 조회합니다.
	 * @param memberNo
	 * @return
	 */
	public int selectMaxCount(int memberNo) {
		int count = 0;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) AS MAXCOUNT FROM T_DEAL_HISTORY WHERE SENDER_NO = ? OR RECEIVER_NO = ? ");

		try (Connection conn = DBUtil.getConnection(); PreparedStatement pst = conn.prepareStatement(sql.toString());) {
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
	 * 완료된 거래내역을 저장합니다.
	 * @param deal
	 * @param flag : true - 바로송금, false - 거래내역
	 * @return
	 */
	public int insert(Deal deal, boolean flag) {
		int result = 0;
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO T_DEAL_HISTORY(NO, SENDER_NO, RECEIVER_NO, MONEY, START_DATE, END_DATE, COMMENTS) VALUES ");
		sql.append(" (T_DEAL_HISTORY_SEQ.nextVal, ?, ?, ?, ");
		sql.append(flag ? "SYSDATE" : "?");
		sql.append(", SYSDATE, ?) ");

		try (Connection conn = DBUtil.getConnection(); 
				PreparedStatement st = conn.prepareStatement(sql.toString());) {

			int loc = 1;
			st.setInt(loc++, deal.getSenderNo());
			st.setInt(loc++, deal.getReceiverNo());
			st.setInt(loc++, deal.getMoney());

			if(!flag){
				Date sqlStartDate = DateUtil.getDate(deal.getStartDate());
				st.setDate(loc++, sqlStartDate);
			}
			
			st.setString(loc++, deal.getComments());
			result = st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}

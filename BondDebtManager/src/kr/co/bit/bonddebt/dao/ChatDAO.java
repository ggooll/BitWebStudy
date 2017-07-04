package kr.co.bit.bonddebt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kr.co.bit.bonddebt.util.DBUtil;
import kr.co.bit.bonddebt.vo.Chat;

public class ChatDAO {
	
	public int insert(Chat chat){
		
		int result = 0;
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO T_CHAT(GROUP_NO, MEMBER_NO, MESSAGE, CHAT_DATE) ");
		sql.append(" VALUES(?, ?, ?, SYSDATE) ");

		try (Connection conn = DBUtil.getConnection(); PreparedStatement pst = conn.prepareStatement(sql.toString());) {

			int loc = 1;
			pst.setInt(loc++, chat.getGroupNo());
			pst.setInt(loc++, chat.getMemberNo());
			pst.setString(loc++, chat.getMessage());

			result = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean insertChatLog(Chat chatVO) {
		int groupNo = chatVO.getGroupNo();
		int memberNo = chatVO.getMemberNo();
		String memberName = chatVO.getMemberName();
		String message = chatVO.getMessage();
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO T_CHAT(NO, GROUP_NO, MEMBER_NO, MEMBER_NAME, MESSAGE, CHAT_DATE) ");
		sql.append(" VALUES(T_CHAT_SEQ.nextVal, ?, ?, ?, ?, SYSDATE) ");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			int i = 1;
			pstmt.setInt(i++, groupNo);
			pstmt.setInt(i++, memberNo);
			pstmt.setString(i++, memberName);
			pstmt.setString(i++, message);
			
			int rs = pstmt.executeUpdate();

			if(rs == 0) {
				throw new Exception("INSERTING CHAT LOG FAILED");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public ArrayList<Chat> getChatLog(int groupNo) {
		ArrayList<Chat> result = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT NO, GROUP_NO, MEMBER_NO, MEMBER_NAME, MESSAGE, CHAT_DATE ");
		sql.append(" FROM T_CHAT ");
		sql.append(" WHERE T_CHAT.GROUP_NO = ? ");
		sql.append(" ORDER BY CHAT_DATE ");
		
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setInt(1, groupNo);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Chat chat = new Chat();
				chat.setNo(rs.getInt("NO"));
				chat.setGroupNo(rs.getInt("GROUP_NO"));
				chat.setMemberNo(rs.getInt("MEMBER_NO"));
				chat.setMemberName(rs.getString("MEMBER_NAME"));
				chat.setMessage(rs.getString("MESSAGE"));
				chat.setChatDate(rs.getDate("CHAT_DATE"));
				result.add(chat);
			}
			
			System.out.println(result.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}

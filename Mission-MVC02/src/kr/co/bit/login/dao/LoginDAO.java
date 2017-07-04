package kr.co.bit.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.co.bit.login.vo.LoginVO;
import kr.co.bit.util.DBUtil;

public class LoginDAO {

	/**
	 * 
	 * @param loginVO
	 * @return
	 */
	public LoginVO login(LoginVO loginVO) {
		LoginVO userVO = null;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID, PASSWORD, TYPE ");
		sql.append("  FROM T_MEMBER ");
		sql.append(" WHERE ID = ? AND PASSWORD = ? ");

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, loginVO.getId());
			pstmt.setString(2, loginVO.getPassword());

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				userVO = new LoginVO();
				userVO.setId(rs.getString("ID"));
				userVO.setPassword(rs.getString("PASSWORD"));
				userVO.setType(rs.getString("TYPE"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userVO;
	}
}

package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import util.connectDB;


public class MemberDAO {
	//회원 가입 로직 
	public int join(String mMail, String mPw, String mDiv, String mName) {
		String sql1 = "select count(*)+1 from member";
		String sql2 = "insert into member values(?, ?, ?, ?, ?)";
		
		String mid = null;
		
		Connection conn = null;
		Statement stat = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = connectDB.getCon();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql1);
			
			if(rs.next()) {
				mid = rs.getString(1).toString();
			}
			if(!mid.equals(null)) {
				try {
					psmt = conn.prepareStatement(sql2);
					psmt.setString(1, mid);
					psmt.setString(2, mMail);
					psmt.setString(3, mPw);
					psmt.setString(4, mDiv);
					psmt.setString(5, mName);
					psmt.executeUpdate();
					
					rs.close();
					stat.close();
					psmt.close();
					conn.close();
					
					return 1;
				
				}catch(Exception e) {
					e.printStackTrace();
				}
				return -1;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	//로그인 로직 
	public int login(String mMail, String mPw) {
		String sql1 = "select member_pw from member where member_mail=?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = connectDB.getCon();
			psmt = conn.prepareStatement(sql1);
			psmt.setString(1, mMail);
			rs = psmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(mPw)) {
					return 1;
					
				}else {
					return 0; //pw오류
				}
			}
			rs.close();
			psmt.close();
			conn.close();
			return -1;   //mail 오류
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -2; //db 오류
	}
}

package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import util.connectDB;

public class RepDAO {
	//평점 입력 로직
	public static int write(RepDTO repDTO) {
		String sql1 = "insert into rep values(?, ?, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = connectDB.getCon();
			psmt = conn.prepareStatement(sql1);
			psmt.setString(1, repDTO.getrId());
			psmt.setString(2, repDTO.getmName());
			psmt.setString(3, repDTO.getrCname());
			psmt.setString(4, repDTO.getrPer());
			psmt.setString(5, repDTO.getrScore());
			psmt.setString(6, repDTO.getrContent());
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //db 오류
	}
	//평점 테이블에서 평점 정보를 ArrayList형태로 리턴 
	public ArrayList<RepDTO> getList(){
		
		ArrayList<RepDTO> repList = null;
		String sql = "select rep_id, member_name, rep_cname, rep_per, rep_score, rep_content from rep order by 1 desc";
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = connectDB.getCon();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			repList = new ArrayList<RepDTO>();
			while(rs.next()) {
				RepDTO rep = new RepDTO(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6));
			repList.add(rep);
			}
			rs.close();
			stat.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return repList;	
	}
}

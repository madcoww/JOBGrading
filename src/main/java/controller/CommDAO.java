package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import util.connectDB;

public class CommDAO {
	//댓글 작성
	public static int write(CommDTO commDTO) {
		String sql1 = "insert into comm values(?, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = connectDB.getCon();
			psmt = conn.prepareStatement(sql1);
			psmt.setString(1, commDTO.getComm_id());
			psmt.setString(2, commDTO.getQue_id());
			psmt.setString(3, commDTO.getMember_name());
			psmt.setString(4, commDTO.getComm_text());
			psmt.setString(5, commDTO.getComm_date());
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //db 오류
	}
	public ArrayList<CommDTO> getList(String qId){
		//DB에 저장되어 있는 댓글 값 들을 읽어와 ArrayList 형태로 리턴 
		ArrayList<CommDTO> commList = null;
		String sql = String.format("select comm_id, que_id, member_name, comm_text, comm_date from comm where que_id='%s'", qId);
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = connectDB.getCon();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			commList = new ArrayList<CommDTO>();
			while(rs.next()) {
				CommDTO comm = new CommDTO(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5));
				commList.add(comm);
			}
			rs.close();
			stat.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return commList;	
	}
}

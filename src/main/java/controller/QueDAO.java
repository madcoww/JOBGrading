package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import util.connectDB;

public class QueDAO {
	//질문 작성 로직
	public static int write(QueDTO queDTO) {
		String sql1 = "insert into que values(?, ?, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = connectDB.getCon();
			psmt = conn.prepareStatement(sql1);
			psmt.setString(1, queDTO.getqId());
			psmt.setString(2, queDTO.getmName());
			psmt.setString(3, queDTO.getqTitle());
			psmt.setString(4, queDTO.getqContent());
			psmt.setString(5, queDTO.getqImage());
			psmt.setString(6, queDTO.getqDate());
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //db 오류
	}
	//질문 데이터 값을 가져와 ArrayList 형태로 리턴
	public ArrayList<QueDTO> getList(){
		
		ArrayList<QueDTO> queList = null;
		String sql = "select que_id, member_name, que_title, que_content, que_image, que_date from que order by 1 desc";
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			
			conn = connectDB.getCon();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			queList = new ArrayList<QueDTO>();
			while(rs.next()) {
				QueDTO rep = new QueDTO(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6));
				queList.add(rep);
			}
			rs.close();
			stat.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return queList;	
	}
	//각각의 게시물을 볼 수 있도록 QueDTO 형태로 리턴
	public QueDTO getView(String qId){
		
		String sql = String.format("select que_id, member_name, que_title, que_content, que_image, que_date from que where que_id='%s'", qId);
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = connectDB.getCon();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()) {
				QueDTO qView = new QueDTO();
				qView.setqId(rs.getString(1));
				qView.setmName(rs.getString(2));
				qView.setqTitle(rs.getString(3));
				qView.setqContent(rs.getString(4));
				qView.setqImage(rs.getString(5));
				qView.setqDate(rs.getString(6));
				
				
				rs.close();
				stat.close();
				conn.close();

				return qView;
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	//게시물 삭제 로직 
	public int delQue(String qId) {
		delComm(qId);
		String sql1 = "delete from que where que_id = ?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = connectDB.getCon();
			psmt = conn.prepareStatement(sql1);
			psmt.setString(1, qId);
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //db 오류
	}
	// 게시물을 삭제하기 위해선 que테이블(질문테이블)을 참조하고 있는 comm테이블(댓글테이블)을 먼저 삭제
	public void delComm(String qId) {
		String sql1 = "delete from comm where que_id = ?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = connectDB.getCon();
			psmt = conn.prepareStatement(sql1);
			psmt.setString(1, qId);
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//질문 게시글 수정 로직
	public int update(String qTitle, String qContent, String qImage, String qId) {
		String sql1 = "update que set que_title= ?, que_content= ?, que_image= ? where que_id="+ qId;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = connectDB.getCon();
			psmt = conn.prepareStatement(sql1);
			psmt.setString(1, qTitle);
			psmt.setString(2, qContent);
			psmt.setString(3, qImage);
			psmt.executeUpdate();
			
			psmt.close();
			conn.close();
			
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; //db 오류
	}
}

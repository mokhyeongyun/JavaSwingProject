package cim_project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import db.DBConn;

public class MemberDao extends DBConn{
	Statement stmt;
	ResultSet rs;
	PreparedStatement pstmt;

	public MemberDao() {
		
	}
	
	public int getJoin(MemberVO vo) {
		int result = 0;
		try {
			String sql = "INSERT INTO CMEMBER(ID,PASS,NAME,PNUM,RANK) VALUES (?,?,?,?,?)";
			pstmt = getPreparedStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPass());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getPnum());
			pstmt.setString(5, vo.getRank());
			result = pstmt.executeUpdate();
			
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public int getCheck(String id) {
		int result = 0;
		try {
			String sql = "SELECT COUNT(*) FROM CMEMBER WHERE ID='"+id+"'";
			stmt = getStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int getLogin(MemberVO vo) {
		int result =0;
		
		try {
			String sql = "select count(*) from cmember where id='"+vo.getId()+"' and pass='"+vo.getPass()+"'";
			stmt = getStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public int getLogin(String id, String pass) {
		int result =0;
		
		try {
			String sql = "select count(*) from cmember where id='"+id+"' and pass='"+pass+"'";
			stmt = getStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public MemberVO getInfo(String id) {
		MemberVO vo = new MemberVO();
		
		try {
			String sql="SELECT * FROM CMEMBER WHERE ID=?";
			pstmt = getPreparedStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo.setId(rs.getString(1));
				vo.setPass(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setPnum(rs.getString(4));
				vo.setRank(rs.getString(5));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return vo;
		
	}
	
	public int getUpdate(MemberVO vo) {
		int result = 0;
		
		try {
			String sql = "UPDATE CMEMBER SET PASS=?, NAME=?, PNUM=? WHERE ID=?";
			pstmt = getPreparedStatement(sql);
			pstmt.setString(1, vo.getPass());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPnum());
//			pstmt.setString(4, vo.getRank());
			pstmt.setString(4, vo.getId());
			
			result = pstmt.executeUpdate();
			
		close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int getUpdate2(MemberVO vo) {
		int result = 0;
		
		try {
			String sql = "UPDATE CMEMBER SET PASS=?, NAME=?, PNUM=?, RANK=? WHERE ID=?";
			pstmt = getPreparedStatement(sql);
			pstmt.setString(1, vo.getPass());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPnum());
			pstmt.setString(4, vo.getRank());
			pstmt.setString(5, vo.getId());
			
			result = pstmt.executeUpdate();
			
		close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<MemberVO> getList() {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		stmt = getStatement();

		try {
			String sql = "SELECT ID, PASS, NAME, PNUM, RANK FROM CMEMBER ORDER BY RANK DESC";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setId(rs.getString(1));
				vo.setPass(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setPnum(rs.getString(4));
				vo.setRank(rs.getString(5));

				list.add(vo);
			}

			close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}










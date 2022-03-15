package cim_project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import db.DBConn;

public class CimDao extends DBConn {
	Statement stmt;
	ResultSet rs;
	PreparedStatement pstmt;

	public CimDao() {
	}

	public ArrayList<CimVO> getList() {
		ArrayList<CimVO> list = new ArrayList<CimVO>();
		stmt = getStatement();

		try {
			String sql = "SELECT CNO, CNAME, CBRAND ,CKINDS, CPRICE , NVL(CQUANTITY,0), TO_CHAR(CDATE,'YY-MM-DD') FROM CIM"
					+ " ORDER BY CNO";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				CimVO vo = new CimVO();
				vo.setCno(rs.getString(1));
				vo.setCname(rs.getString(2));
				vo.setCbrand(rs.getString(3));
				vo.setCkinds(rs.getString(4));
				vo.setCprice(rs.getString(5));
				vo.setCquantity(rs.getInt(6));
				vo.setCdate(rs.getString(7));

				list.add(vo);
			}

			close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public CimVO getList(String cno) {
		CimVO vo = new CimVO();
		stmt = getStatement();

		try {
			String sql = "SELECT CNO, CNAME, CBRAND, CKINDS, CPRICE, NVL(CQUANTITY,0), TO_CHAR(CDATE,'YY-MM-DD') "
					+ " FROM CIM WHERE CNO='"+cno+"'";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				vo.setCno(rs.getString(1));
				vo.setCname(rs.getString(2));
				vo.setCbrand(rs.getString(3));
				vo.setCkinds(rs.getString(4));
				vo.setCprice(rs.getString(5));
				vo.setCquantity(rs.getInt(6));
				vo.setCdate(rs.getString(7));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return vo;
	}

	
	
	public ArrayList<CimVO> getSearch(String item) {
		ArrayList<CimVO> list = new ArrayList<CimVO>();
		try {
			String sql = "SELECT CNO, CNAME, CBRAND, CKINDS, CPRICE ,"
					+ " NVL(CQUANTITY,0), TO_CHAR(CDATE,'YY-MM-DD') FROM CIM";
			stmt = getStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				CimVO vo = new CimVO();
				vo.setCno(rs.getString(1));
				vo.setCname(rs.getString(2));
				vo.setCbrand(rs.getString(3));
				vo.setCkinds(rs.getString(4));
				vo.setCprice(rs.getString(5));
				vo.setCquantity(rs.getInt(6));
				vo.setCdate(rs.getString(7));

				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		close();
		return list;

	}

	public ArrayList<CimVO> getSearch(String item, String tf) {
		ArrayList<CimVO> list = new ArrayList<CimVO>();

		String col_name = "";
		if (item.equals("품번")) {
			col_name = "CNO";
			tf = "upper('" + tf + "')";
		} else if (item.equals("제품명")) {
			col_name = "CNAME";
			tf = "upper('" + tf + "')";
		} else if (item.equals("구분")) {
			col_name = "CKINDS";
			tf = "upper('" + tf + "')";
		} else if (item.contentEquals("브랜드")) {
			col_name = "CBRAND";
			tf = "upper('" + tf + "')";
		}

		try {
			String sql = "SELECT CNO, CNAME, CBRAND, CKINDS, CPRICE ,"
					+ " NVL(CQUANTITY,0), TO_CHAR(CDATE,'YY-MM-DD') FROM CIM" + " WHERE " + col_name + "=" + tf;

			stmt = getStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				CimVO vo = new CimVO();
				vo.setCno(rs.getString(1));
				vo.setCname(rs.getString(2));
				vo.setCbrand(rs.getString(3));
				vo.setCkinds(rs.getString(4));
				vo.setCprice(rs.getString(5));
				vo.setCquantity(rs.getInt(6));
				vo.setCdate(rs.getString(7));

				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		close();
		return list;
	}

	
	public int getInsert(CimVO vo) {
		int result = 0;
		
		try {
			String sql = "INSERT INTO CIM VALUES('"+vo.getCno()+"','"+vo.getCname()+"','"+vo.getCbrand()+"','"+vo.getCkinds()+"'"
					+ " ,'"+vo.getCprice()+"',"+vo.getCquantity()+",SYSDATE)";
			stmt = getStatement();
			result =stmt.executeUpdate(sql);
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public int getUpdate(CimVO vo) {
		int result = 0;
		
		try { 
			String sql = "UPDATE CIM SET CNAME='"+vo.getCname()+"', CBRAND='"+vo.getCbrand()+"', CKINDS='"+vo.getCkinds()+"', CPRICE='"+vo.getCprice()+"', CQUANTITY="+vo.getCquantity()+",CDATE=SYSDATE" + 
					" WHERE CNO='"+vo.getCno()+"'";	
					
			stmt = getStatement();
			result = stmt.executeUpdate(sql);
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int getDelete(CimVO vo) {
		int result=0;
		
		try {
			String sql = "delete from cim where cno=?";
			pstmt = getPreparedStatement(sql);
			pstmt.setString(1, vo.getCno());
			result = pstmt.executeUpdate();
			
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}//






















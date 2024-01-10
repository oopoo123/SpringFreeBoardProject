package com.jbedu.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.jbedu.board.dto.FboardDto;

public class FreeBoardDao {
	
	DataSource dataSource;

	public FreeBoardDao() {
		super();
		// TODO Auto-generated constructor stub
		Context context;
		try {
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/oracledb");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void write(String fbtitle, String fbname, String fbcontent) {
		Connection conn = null;
		// Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO freeboard(fbnum, fbtitle, fbname, fbcontent, fbhit) VALUES (freeboard_seq.nextval, ?, ?, ?, 0)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, fbtitle);
			pstmt.setString(2, fbname);
			pstmt.setString(3, fbcontent);			
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<FboardDto> list() {
		
		Connection conn = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<FboardDto> dtos = new ArrayList<FboardDto>();
		
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM freeboard ORDER BY fbnum DESC";
			// 내림차순으로 정렬된 모든 데이터 요청
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			//dtos = new ArrayList<FboardDto>();
			
			while(rs.next()) { // 다음 레코드가 있으면 참, 아니면 거짓
				int fbnum = rs.getInt("fbnum");
				String fbname = rs.getString("fbname");
				String fbtitle = rs.getString("fbtitle");
				String fbcontent = rs.getString("fbcontent");
				int fbhit = rs.getInt("fbhit");
				Timestamp fbdate = rs.getTimestamp("fbdate");
				
				FboardDto fboardDto = new FboardDto();
				
				fboardDto.setFbnum(fbnum);
				fboardDto.setFbname(fbname);
				fboardDto.setFbtitle(fbtitle);
				fboardDto.setFbcontent(fbcontent);
				fboardDto.setFbhit(fbhit);
				fboardDto.setFbdate(fbdate);
				
				dtos.add(fboardDto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return dtos;
	}
	
	public FboardDto content_view(String clickNum) {
		
		upHit(clickNum);
		
		Connection conn = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//ArrayList<FboardDto> dtos = new ArrayList<FboardDto>();
		FboardDto fboardDto = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM freeboard WHERE fbnum = ?";
			// 내림차순으로 정렬된 모든 데이터 요청
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, clickNum);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) { // 다음 레코드가 있으면 참, 아니면 거짓
				int fbnum = rs.getInt("fbnum");
				String fbname = rs.getString("fbname");
				String fbtitle = rs.getString("fbtitle");
				String fbcontent = rs.getString("fbcontent");
				int fbhit = rs.getInt("fbhit");
				Timestamp fbdate = rs.getTimestamp("fbdate");
				
				fboardDto = new FboardDto(fbnum, fbtitle, fbname, fbcontent, fbhit, fbdate);
				
//				fboardDto.setFbnum(fbnum);
//				fboardDto.setFbname(fbname);
//				fboardDto.setFbtitle(fbtitle);
//				fboardDto.setFbcontent(fbcontent);
//				fboardDto.setFbhit(fbhit);
//				fboardDto.setFbdate(fbdate);
				
//				dtos.add(fboardDto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return fboardDto;
	}
	
	public void modify(String fbtitle, String fbname, String fbcontent, String fbnum) {
		Connection conn = null;
		// Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE freeboard SET fbtitle=?, fbname=?, fbcontent=? WHERE fbnum=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, fbtitle);
			pstmt.setString(2, fbname);
			pstmt.setString(3, fbcontent);		
			pstmt.setString(4, fbnum);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void delete(String fbnum) {
		Connection conn = null;		
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "DELETE FROM freeboard WHERE fbnum=?";
			
			pstmt = conn.prepareStatement(sql);	
			
			pstmt.setString(1, fbnum);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<FboardDto> search_list(String searchKey) {
		
		Connection conn = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<FboardDto> dtos = new ArrayList<FboardDto>();
		
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM freeboard WHERE fbtitle LIKE '%' || ? || '%' OR fbcontent LIKE '%' || ? || '%' ORDER BY fbnum DESC";
			// 내림차순으로 정렬된 모든 데이터 요청
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, searchKey);
			pstmt.setString(2, searchKey);
			
			rs = pstmt.executeQuery();
			
			//dtos = new ArrayList<FboardDto>();
			
			while(rs.next()) { // 다음 레코드가 있으면 참, 아니면 거짓
				int fbnum = rs.getInt("fbnum");
				String fbname = rs.getString("fbname");
				String fbtitle = rs.getString("fbtitle");
				String fbcontent = rs.getString("fbcontent");
				int fbhit = rs.getInt("fbhit");
				Timestamp fbdate = rs.getTimestamp("fbdate");
				
				FboardDto fboardDto = new FboardDto();
				
				fboardDto.setFbnum(fbnum);
				fboardDto.setFbname(fbname);
				fboardDto.setFbtitle(fbtitle);
				fboardDto.setFbcontent(fbcontent);
				fboardDto.setFbhit(fbhit);
				fboardDto.setFbdate(fbdate);
				
				dtos.add(fboardDto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return dtos;
	}
	
	public void upHit(String fbnum) {
		
		Connection conn = null;		
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE freeboard SET fbhit=fbhit+1 WHERE fbnum=?";
			
			pstmt = conn.prepareStatement(sql);
				
			pstmt.setString(1, fbnum);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
package kr.co.happy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDAO {
	
	public static BoardDAO instanse = null;
	
	//싱글톤
	public static BoardDAO getInstance() {
		if(instanse == null) {
			instanse = new BoardDAO();
		}
		return instanse;
	}
	
	private BoardDAO() {}
	private final int LIST_CNT = 15;
	
	public ArrayList<BoardDTO> getBaordList(int btype, int page){
		ArrayList<BoardDTO> datas = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnector.getConn();
			
			int start = (page - 1) * LIST_CNT + 1;
			int end = page * LIST_CNT;
			
			String query = " select * from ("  
				  + " select h.*, row_number() over(order by seq desc) as rnum " 
				  + " from h_board h" 
				  + " where h.btype = ?"  
				  + " ) where rnum between ? and ? ";
			
			ps = conn.prepareStatement(query);
			ps.setInt(1, btype);
			ps.setInt(2, start);
			ps.setInt(3, end);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBid(rs.getInt("bid"));
				dto.setSeq(rs.getInt("seq"));
				dto.setBtitle(rs.getString("btitle"));
				dto.setBregdate(rs.getString("bregdate"));
				datas.add(dto);
				
			}
			
		} catch (Exception e) {
			System.out.println("getBoardList 오류");
			e.printStackTrace();
		}finally {
			DBConnector.close(conn, ps, rs);
		}
		
		return datas;
	}

	public ArrayList<BoardDTO> getBoardDetail(int btype, int seq) {
		ArrayList<BoardDTO> datas = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnector.getConn();
			
			String query = " select "
					+ " bid, btype, seq, btitle, bcontent, bregdate, pw " 
					+ " from h_board " 
					+ " where btype = ? and seq =? ";
			
			ps = conn.prepareStatement(query);
			ps.setInt(1, btype);
			ps.setInt(2, seq);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBid(rs.getInt("bid"));
				dto.setBtype(rs.getInt("btype"));
				dto.setSeq(rs.getInt("seq"));
				dto.setBtitle(rs.getString("btitle"));
				dto.setBcontent(rs.getString("bcontent"));
				dto.setBregdate(rs.getString("bregdate"));
				dto.setPw(rs.getString("pw"));
				
				datas.add(dto);
			}
			
		} catch (Exception e) {
			System.out.println("getBoardDetail 오류");
			e.printStackTrace();
		}finally {
			DBConnector.close(conn, ps, rs);
		}
		
		return datas;
	}

	public void boardInsert(int btype, String btitle, String bcontent, String pw) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBConnector.getConn();
			
			String query = " insert into h_board "
					+ " (bid, btype, seq, btitle, bcontent, pw) "
					+ " values "
					+ " ( "
					+ "   (select nvl(max(bid),0)+1 from h_board),"
					+ " ?, "
					+ "   (select nvl(max(seq),0)+1 from h_board where btype=?),"
					+ " ?, ?, ?)";
			
			ps = conn.prepareStatement(query);
			ps.setInt(1, btype);
			ps.setInt(2, btype);
			ps.setString(3, btitle);
			ps.setString(4, bcontent);
			ps.setString(5, pw);
			
			ps.executeQuery();
			
		} catch (Exception e) {
			System.out.println("boardInsert 오류");
			e.printStackTrace();
		}finally {
			DBConnector.close(conn, ps, null);
		}
		
	}

	public boolean chkPassword(int btype, int seq, String pw) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flag = false;
		
		try {
			conn = DBConnector.getConn();
			
			String query = " select bid, btype, seq, pw " 
					+ " from h_board " 
					+ " where btype=? and seq=? and pw=? ";
			
			ps = conn.prepareStatement(query);
			ps.setInt(1, btype);
			ps.setInt(2, seq);
			ps.setString(3, pw);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				flag = true;
			}
			
		}catch (Exception e) {
			System.out.println("chkPw 오류");
			e.printStackTrace();
		}finally {
			DBConnector.close(conn, ps, rs);
		}
		return flag;
	}

	public void boardUpdate(int btype, int seq, String btitle, String bcontent, String pw) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBConnector.getConn();
			
			String query = " update h_board "
					+ " set btitle=?"
					+ " ,bcontent=?"
					+ " ,pw=?"
					+ "where btype=? and seq=?";
			
			ps = conn.prepareStatement(query);
			ps.setString(1, btitle);
			ps.setString(2, bcontent);
			ps.setString(3, pw);
			ps.setInt(4, btype);
			ps.setInt(5, seq);
			
			ps.executeQuery();
			
		} catch (Exception e) {
			System.out.println("boardUpdate 오류");
			e.printStackTrace();
		}finally {
			DBConnector.close(conn, ps, null);
		}
		
	}

	public void boardDelete(int btype, int seq) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBConnector.getConn();
			
			String query = " delete from h_board "
					+ " where btype=? and seq=? ";
			
			ps = conn.prepareStatement(query);
			ps.setInt(1, btype);
			ps.setInt(2, seq);
			
			ps.executeQuery();
			
		} catch (Exception e) {
			System.out.println("boardDelete 오류");
			e.printStackTrace();
		}finally {
			DBConnector.close(conn, ps, null);
		}
		
	}

	public int getMaxlist(int intBtype) {
		int idxPage = 1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnector.getConn();
			
			/*String query = " select ceil(count(seq)/15) as cnt from h_board "
					+ " where btype=? ";*/
			
			String query = String.format("select ceil(count(seq)/%d)"
					+ " as cnt from h_board where btype = ? ", LIST_CNT);
			
			ps = conn.prepareStatement(query);
			ps.setInt(1, intBtype);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				idxPage = rs.getInt("cnt");
			}
			
		} catch (Exception e) {
			System.out.println("getMaxlist 오류");
			e.printStackTrace();
		}finally {
			DBConnector.close(conn, ps, rs);
		}
		
		return idxPage;
	} 
}

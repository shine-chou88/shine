package com.gwideal.common.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenUniqueCodeUtil {
	public static String  genMaxQrCode(int len) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String curCode="";
		try {
			conn=DBHelper.getConn();
			String sql="select replace(lpad(SEQ_CERTIFICATE_QRCODE.nextval,"+len+",'0'),'','0') sn from dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				curCode = rs.getString("sn");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBHelper.closeAll(conn, pstmt, rs);
		}
		return curCode;
	}
	
	
}

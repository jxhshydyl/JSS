package com.jss.teacher.dao;

import java.util.ArrayList;
import com.jss.teacher.util.DBUtil;

public class ExportExcelDao {
	/**
	 * 导出学生信息表
	 * @param Cname
	 * @return
	 */
	public ArrayList<ArrayList<Object>> queryStudentsExportExcel(String Cname){
		DBUtil.openConn();
		try{
			String sql="select * from Sinformation where Sclass=?";
			DBUtil.pstat=DBUtil.conn.prepareStatement(sql);
			DBUtil.pstat.setString(1, Cname);
			ArrayList<ArrayList<Object>> result =new ArrayList<ArrayList<Object>>();
			DBUtil.rs=DBUtil.pstat.executeQuery();
			while(DBUtil.rs.next()){
				ArrayList<Object> list=new ArrayList<Object>();
				list.add(DBUtil.rs.getString("Sno"));
				list.add(DBUtil.rs.getString("Sname"));
				list.add(DBUtil.rs.getString("Ssex"));
				list.add(DBUtil.rs.getString("Syear"));
				list.add(DBUtil.rs.getString("Sacademy"));
				list.add(DBUtil.rs.getString("Smajor"));
				list.add(DBUtil.rs.getString("Sclass"));
				result.add(list);
			}
			return result;
		}catch(Exception e){
			e.printStackTrace();
		}
		DBUtil.closeConn();
		return null;
	}
}

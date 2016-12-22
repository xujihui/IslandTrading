/**
 * Function:UserBiz
 * Date:2016.12.11
 * Author:LiuXin
 */
package service;

import java.util.List;

/** 
 * 用户处理类
 */

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class UserBiz {
	/**
	 * 增加用户
	 */
	public boolean save(String pNickname, String pUsername, String pPassword, String pImage, String pPower,
			String pTakingid, String pTel, String pHx_username, String pHx_password) {
		Record pro = new Record().set("User_Nickname", pNickname).set("User_Username", pUsername)
				.set("User_Password", pPassword).set("User_Image", pImage).set("User_Power", pPower)
				.set("User_TakingId", pTakingid).set("User_Tel", pTel).set("Hx_Username", pHx_username)
				.set("Hx_Password", pHx_password);
		boolean res = Db.save("islandtrading_user", pro);
		return res;
	}

	/**
	 * 删除用户
	 */
	public boolean deleteByID(String pid) {
		boolean res = Db.deleteById("islandtrading_user", "User_Id", pid);
		return res;
	}

	/**
	 * 修改用户
	 */
	public int update(String pID, String pNickname, String pUsername, String pPassword, String pImage, String pPower,
			String pTakingid, String pTel, String pHx_username, String pHx_password) {
		String sql = "UPDATE islandtrading_user SET" + " User_Id='" + pID + "'," + "User_Nickname='" + pNickname
				+ "'," + "User_Username='" + pUsername + "'," + "User_Password='" + pPassword + "'," + "User_Image='"
				+ pImage + "'," + "User_Power='" + pPower + "'," + "User_TakingId='" + pTakingid + "'," + "User_Tel='"
				+ pTel + "'," + "Hx_Username='" + pHx_username + "'," + "Hx_Password='" + pHx_password + "'"
				+ " WHERE User_Id='" + pID + "'";
		int res = Db.update(sql);
		return res;
	}

	public List<Record> findAll() {
		List<Record> pros = Db.find("select * from islandtrading_user");
		return pros;
	}

	/**
	 * 查找用户
	 */
	public Record findByID(String id) {
		Record rec = Db.findById("islandtrading_user", "User_Id", id);
		return rec;
	}

}

package com.revature.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.Reimb;

import util.ConnectionUtil;

public class ReimbPostgres implements ReimbDao {
	private static Logger log = LogManager.getRootLogger();

	@Override
	public List<Reimb> getAllReimb() {
		List<Reimb> reimb = new ArrayList<>();
		
		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			String sql =  "select * from reimb r;";
			
			Statement ps = c.createStatement();
			ResultSet rs = ps.executeQuery(sql);
			
			while (rs.next()) {
				
				int id = rs.getInt("re_id");
				int authorId = rs.getInt("re_authorId");
				double amount = rs.getDouble("re_amount");
				int typeId = rs.getInt("re_typeId");
				String description = rs.getString("re_description");
				String submitted = rs.getString("re_submitted");
				String resolved = rs.getString("re_resolved");
				int resolverId = rs.getInt("re_resolverId");
				int statusId = rs.getInt("re_statusId");
				
				
				
				Reimb r = new Reimb(id,authorId,amount,typeId,description,submitted,resolved,resolverId,statusId);
				 r.setTypeName(typeId);
				reimb.add(r);
			}
		} catch (SQLException | IOException e) {
			log.error("Error while trying to get item.");
			e.printStackTrace();
		}
		return reimb;
	}
	
	@Override
	public List<Reimb> getAllResolvedRequest() {
		List<Reimb> reimb = new ArrayList<>();
		try (Connection c = ConnectionUtil.getConnectionFromFile()) {
			String sql =  "select * from reimb r "
					+ "join status s on r.re_statusid = s.s_id "
					+ "join users u on r.re_resolverId = u.u_id;";
			
			Statement ps = c.createStatement();
			ResultSet rs = ps.executeQuery(sql);
			
			while (rs.next()) {
				
				int id = rs.getInt("re_id");
				int authorId = rs.getInt("re_authorId");
				double amount = rs.getDouble("re_amount");
				int typeId = rs.getInt("re_typeId");
				String description = rs.getString("re_description");
				String submitted = rs.getString("re_submitted");
				String resolved = rs.getString("re_resolved");
				int resolverId = rs.getInt("re_resolverId");
				int statusId = rs.getInt("re_statusId");
				String resolverName = rs.getString("u_username");
				String statusName = rs.getString("s_status");
				
				
				
				Reimb r = new Reimb(id,authorId,amount,typeId,description,submitted,resolved,resolverId,statusId);
				 r.setTypeName(typeId);
				 r.resolverName = resolverName;
				 r.statusName = statusName;
				reimb.add(r);
			}
		} catch (SQLException | IOException e) {
			log.error("Error while trying to get item.");
			e.printStackTrace();
		}
		return reimb;
		
	}
	

	
	@Override
	public Reimb addReimb(Reimb reimb) {
		Reimb resultId = null;
		String sql = "insert into reimb(re_amount,re_typeId,re_description,re_submitted,re_statusId,re_authorId)" + "values (?,?,?,?,?,?) returning re_id;";
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setDouble(1, reimb.getAmount());
			ps.setInt(2, reimb.getTypeId());
			ps.setString(3, reimb.getDescription());
			ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			ps.setInt(5, reimb.getStatusId());
			ps.setInt(6, reimb.getAuthorId());
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				resultId = reimb;
			}
			
		}catch (SQLException | IOException e) {
			log.error("Error while trying to get lists.");
			e.printStackTrace();
			
		}
		return resultId;
	}


	@Override
	public boolean updateReimb(Reimb r) {
		String sql = "update reimb set re_statusId = ?, re_resolverId = ?, re_resolved = ?  Where re_id = ?;";
		int rowsChanged = -1;
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, r.getStatusId());
			ps.setInt(2, r.getResolverId());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setInt(4, r.getId());
			
			rowsChanged = ps.executeUpdate();
			
			if (rowsChanged > 0) {
				return true;
			} else {
				return false;
				}
			
		}catch (SQLException | IOException e) {
			log.error("Error while trying to update Reimb.");
				e.printStackTrace();
			}
		return false;
		}

	@Override
	public Reimb getReimbById(int id) {
		String sql = "select * from reimb where re_id = ?;";
		Reimb allReimb1 = null;
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, id); 

			ResultSet rs = ps.executeQuery();
			
		if (rs.next()) {
			int id1 = rs.getInt("re_id");
			int authorId = rs.getInt("re_authorId");
			double amount = rs.getDouble("re_amount");
			String submitted = rs.getString("re_submitted");
			String resolved = rs.getString("re_resolved");
			String description = rs.getString("re_description");
			int resolverId = rs.getInt("re_resolverId");
			int typeId = rs.getInt("re_typeId");
			int statusId = rs.getInt("re_statusId");
			
			 allReimb1 = new Reimb(id1,authorId,amount,typeId,description,submitted,resolved,resolverId,statusId);
			}
		} catch (SQLException | IOException e) {
			log.error("Error while trying to get Reimb.");
			e.printStackTrace();
			}
		
		return allReimb1;
	}


	@Override
	public List<Reimb> getReimbsById(int id) {
		String sql = "select * from reimb r "
				+"left join  users u on r.re_resolverId = u.u_id "
				+"where r.re_authorId = ?;";
		List<Reimb> allReimbs = new ArrayList<>();
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, id); 

			ResultSet rs = ps.executeQuery();
			
		while (rs.next()) {
			int id1 = rs.getInt("re_id");
			int typeId = rs.getInt("re_typeId");
			double amount = rs.getDouble("re_amount");
			String description = rs.getString("re_description");
			String submitted = rs.getString("re_submitted");
			int statusId = rs.getInt("re_statusId");
			String resolved = rs.getString("re_resolved");
			int resolverId = rs.getInt("re_resolverId");
			String  resolverName = rs.getString("u_username");
		
			 Reimb allReimb = new Reimb(id1,typeId,amount,description,submitted,statusId,resolved,resolverId);
			 allReimb.setTypeName(typeId);
			 allReimb.setResolved(resolved);
			 allReimb.resolverName = resolverName;
			
			 allReimbs.add(allReimb);
			}
		} catch (SQLException | IOException e) {
			log.error("Error while trying to get Reimbs.");
			e.printStackTrace();
			}
		
		return allReimbs;
	}
	
	@Override
	public List<Reimb> getAllPendingReimb(int id) {
		String sql = "select * from reimb r where r.re_authorId = ?;";
		List<Reimb> pending =new ArrayList<>();
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, id); 

			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {
				int id1 = rs.getInt("re_id");
				double amount = rs.getDouble("re_amount");
				String submitted = rs.getString("re_submitted");
				int statusId = rs.getInt("re_statusId");
				int typeId = rs.getInt("re_typeId");
				String description = rs.getString("re_description");
				int authorId = rs.getInt("re_authorId");
				
				Reimb pendingRqt = new Reimb(id1,authorId,amount,typeId,description,submitted,statusId);
				pendingRqt.setTypeName(typeId);
				pending.add(pendingRqt);
			}
			
		}catch (SQLException |IOException e) {
			log.error("Error while trying to get lists.");
			e.printStackTrace();
		
	}
		return pending;
	}
	
	
	@Override
	public List<Reimb> getAllResolvedReimb(int id) {
		String sql = "select * from reimb r "
				+ "join users u on r.re_resolverId = u.u_id "
				+"join status s on r.re_statusid = s.s_id "
				+ "where r.re_authorId = ?;";
		List<Reimb> pending =new ArrayList<>();
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, id); 

			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id1 = rs.getInt("re_id");
				double amount = rs.getDouble("re_amount");
				String submitted = rs.getString("re_submitted");
				int statusId = rs.getInt("re_statusId");
				String resolved = rs.getString("re_resolved");
				int typeId = rs.getInt("re_typeId");
				String description = rs.getString("re_description");
				int authorId = rs.getInt("re_authorId");
				String resolverName = rs.getString("u_username");
				String statusName = rs.getString("s_status");
				
				Reimb pendingRqt = new Reimb(id1,authorId,amount,typeId,description,submitted,statusId);
				pendingRqt.setTypeName(typeId);
				pendingRqt.setResolved(resolved);
				pendingRqt.resolverName = resolverName;
				pendingRqt.statusName = statusName;
				pending.add(pendingRqt);
			}
			
		}catch (SQLException |IOException e) {
			log.error("Error while trying to get lists.");
			e.printStackTrace();
		
	}
		return pending;
	}



}

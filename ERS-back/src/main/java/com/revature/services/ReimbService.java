package com.revature.services;

import java.util.List;

import com.revature.models.Reimb;
import com.revature.repositories.ReimbDao;
import com.revature.repositories.ReimbPostgres;

public class ReimbService {
	
	private ReimbDao rd = new ReimbPostgres();
	
	public List<Reimb> getAllReimb(){
		return rd.getAllReimb();
		
	}
	
	public List<Reimb> getAllResolvedRequest(){
		return rd.getAllResolvedRequest();
	}
	
	public List<Reimb> getReimbsById(int id){
		return rd.getReimbsById(id);
		
	}
	
	public Reimb addReimb(Reimb reimb) {
		return rd.addReimb(reimb);
	}
	
	public Reimb getReimbById(int id) {
		return rd.getReimbById(id);
	}
	
	
	public List<Reimb> getPendings(int id) {
		return rd.getAllPendingReimb(id);
	}
	
	public List<Reimb> getAllResolved(int id) {
		return rd.getAllResolvedReimb(id);
	}
	
	
	public boolean update(Reimb r) {
		return rd.updateReimb(r);
	}
	
}

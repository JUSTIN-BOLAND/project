package com.deyi.util;

import java.util.List;

import com.deyi.entity.Merchant;
import com.deyi.entity.Org;
import com.deyi.entity.Role;
import com.deyi.entity.Store;
import com.deyi.entity.UserOperator;

public class GetIdsFromList {
	public synchronized static String getOrgIds(List<Org> list){
		StringBuffer sb = new StringBuffer();
		if(list==null){
			return "''";
		}
		for (int i = 0; i < list.size(); i++) {
			if(i== list.size()-1){
				sb.append("'"+list.get(i).getId()+"'");
			}else{
				sb.append("'"+list.get(i).getId()+"',");
			}
			
		}
		return sb.toString();
	}
	
	public synchronized static String getMerchantIds(List<Merchant> list){
		StringBuffer sb = new StringBuffer();
		if(list==null || list.isEmpty()){
			return "''";
		}
		for (int i = 0; i < list.size(); i++) {
			if(i== list.size()-1){
				sb.append("'"+list.get(i).getId()+"'");
			}else{
				sb.append("'"+list.get(i).getId()+"',");
			}
			
		}
		return sb.toString();
	}
	
	public synchronized static String getIdsByList(List<String> list){
		StringBuffer sb = new StringBuffer();
		if(null == list || list.isEmpty()){
			return "''";
		}
		
		for (int i = 0; i < list.size(); i++) {
			if(i== list.size()-1){
				sb.append("'"+list.get(i)+"'");
			}else{
				sb.append("'"+list.get(i)+"',");
			}
			
		}
		return sb.toString();
	}
	
	
	public synchronized static String getIdsByStore(List<Store> list){
		StringBuffer sb = new StringBuffer();
		if(null == list || list.isEmpty()){
			return "''";
		}
		
		for (int i = 0; i < list.size(); i++) {
			if(i== list.size()-1){
				sb.append("'"+list.get(i).getId()+"'");
			}else{
				sb.append("'"+list.get(i).getId()+"',");
			}
			
		}
		return sb.toString();
	}

	public synchronized static String getIdsByUserList(List<UserOperator> list) {
		StringBuffer sb = new StringBuffer();
		if(null == list || list.isEmpty()){
			return "''";
		}
		
		for (int i = 0; i < list.size(); i++) {
			if(i== list.size()-1){
				sb.append("'"+list.get(i).getId()+"'");
			}else{
				sb.append("'"+list.get(i).getId()+"',");
			}
			
		}
		return sb.toString();
	}
	
	public synchronized static String getIdsByRoleList(List<Role> list) {
		StringBuffer sb = new StringBuffer();
		if(null == list || list.isEmpty()){
			return "''";
		}
		
		for (int i = 0; i < list.size(); i++) {
			if(i== list.size()-1){
				sb.append("'"+list.get(i).getId()+"'");
			}else{
				sb.append("'"+list.get(i).getId()+"',");
			}
			
		}
		return sb.toString();
		
	}
	
}

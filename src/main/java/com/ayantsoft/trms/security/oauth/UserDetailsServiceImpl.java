package com.ayantsoft.trms.security.oauth;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ayantsoft.trms.pojo.UserMst;
import com.ayantsoft.trms.service.UserMstService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {


	@Autowired
	private UserMstService userMstService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		try{
			UserMst userMst = userMstService.getUserMstByUsername(username);
			if(userMst == null){
				throw new UsernameNotFoundException("User Not Found");
			}else{
				if(userMst.getUsername().equals(username)){
					GrantedAuthority authority = new GrantedAuthorityImpl("ROLE_ADMIN");
					Set<GrantedAuthority> authorities = new HashSet<>();
					authorities.add(authority);
					AuthUser authUser = new AuthUser(userMst.getUsername(), userMst.getPassword(), authorities);
					authUser.setUserMst(userMst);
					return authUser;
				}else{
					throw new UsernameNotFoundException("User Not Found");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
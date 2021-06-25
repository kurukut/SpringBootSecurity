package com.configuration;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;
import static com.configuration.ApplicationUserPermission.*;

public enum ApplicationUserRole {
STUDENT(Sets.newHashSet()),
ADMIN(Sets.newHashSet(COURSE_READ,COURSE_WRITE,STUDENT_READ,STUDENT_WRITE)),
ADMINTRAINEE(Sets.newHashSet(COURSE_READ,STUDENT_READ));


private final Set<ApplicationUserPermission> permissions;

private ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
	this.permissions = permissions;
}

public Set<ApplicationUserPermission> getPermissions() {
	return permissions;
}

/*
 * generating a Set<SimpleGrantedAuthority> to pass on to authorities() in UserDetails
 */
public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
	//add the permissions
	Set<SimpleGrantedAuthority> permissions= getPermissions().stream()
	.map(permission-> new SimpleGrantedAuthority(permission.getPermission()))
	.collect(Collectors.toSet());
	
	//add the roles
	permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
	return permissions;
}



}

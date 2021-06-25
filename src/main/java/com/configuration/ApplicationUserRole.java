package com.configuration;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Sets;
import static com.configuration.ApplicationUserPermission.*;

public enum ApplicationUserRole {
STUDENT(Sets.newHashSet()),
ADMIN(Sets.newHashSet(COURSE_READ,COURSE_WRITE,STUDENT_READ,STUDENT_WRITE));


private final Set<ApplicationUserPermission> permissions;

private ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
	this.permissions = permissions;
}

public Set<ApplicationUserPermission> getPermissions() {
	return permissions;
}



}

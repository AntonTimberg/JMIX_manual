package com.company.testing.security;

import com.company.testing.entity.Department;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;

@RowLevelRole(name = "HR manager's departments and users", code = HrManagerRlRole.CODE)
public interface HrManagerRlRole {
    String CODE = "hr-manager-rl";

    @JpqlRowLevelPolicy(entityClass = Department.class, where = "{E}.hrManager.id = :current_user_id")
    void department();
}
package com.xfour.businesscapitalloan.service;

import com.xfour.businesscapitalloan.entity.Role;

public interface RoleService {
    Role getOrSave(String roleNumber);
}

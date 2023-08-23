package com.xfour.busniesscapitalloan.service;

import com.xfour.busniesscapitalloan.entity.Role;

public interface RoleService {
    Role getOrSave(String roleNumber);
}

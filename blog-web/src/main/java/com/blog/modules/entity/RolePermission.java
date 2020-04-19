package com.blog.modules.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 角色-权限值
 */
@Entity
@Table(name = "shiro_role_permission")
@Data
public class RolePermission implements Serializable {
    private static final long serialVersionUID = -5979636077649378677L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "role_id")
    private long roleId;


    @Column(name = "permission_id")
    private long permissionId;
}

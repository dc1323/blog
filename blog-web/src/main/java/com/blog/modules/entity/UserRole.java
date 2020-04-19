package com.blog.modules.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户角色映射表
 */
@Entity
@Table(name = "shiro_user_role")
@Data
public class UserRole implements Serializable {
	private static final long serialVersionUID = -2908144287976184011L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "role_id")
    private Long roleId;
}

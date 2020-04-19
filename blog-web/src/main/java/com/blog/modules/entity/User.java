package com.blog.modules.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 */
@Entity
@Table(name = "mto_user")
@Data
public class User implements Serializable {
	private static final long serialVersionUID = -3629784071225214858L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/**
	 * 用户名
	 */
	@Column(name = "username", unique = true, nullable = false, length = 64)
	private String username;

	/**
	 * 密码(密文)
	 */
	@Column(name = "password", length = 64)
	private String password;

	/**
	 * 头像
	 */
	private String avatar;

	/**
	 * 昵称
	 */
	@Column(name = "name", length = 18)
	private String name;

	/**
	 * 性别
	 */
	private int gender;

	/**
	 * 邮箱
	 */
	@Column(name = "email", unique = true, length = 64)
	private String email;

	/**
	 * 文章数
	 */
	private int posts;

	/**
	 * 发布评论数
	 */
	private int comments;

	/**
	 * 注册时间
	 */
	private Date created;

	/**
	 * 最后登录时间
	 */
	@Column(name = "last_login")
	private Date lastLogin;

	/**
	 * 个性签名
	 */
	private String signature;

	/**
	 * 用户状态
	 */
	private int status;

	public User() {

	}
}

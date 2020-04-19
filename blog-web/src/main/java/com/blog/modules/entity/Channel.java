/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.blog.modules.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 模块/内容分组
 */
@Entity
@Table(name = "mto_channel")
@Data
public class Channel implements Serializable {
	private static final long serialVersionUID = 2436696690653745208L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * 名称
	 */
	@Column(length = 32)
	private String name;

	/**
	 * 唯一关键字
	 */
	@Column(name = "key_", unique = true, length = 32)
	private String key;

	/**
	 * 预览图
	 */
	@Column(length = 128)
	private String thumbnail;

	/**
	 * 状态 0 显示, 1隐藏
	 */
	@Column(length = 5)
	private int status;

	/**
	 * 排序值
	 */
	private int weight;
}

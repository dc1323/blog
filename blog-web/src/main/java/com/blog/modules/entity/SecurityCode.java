package com.blog.modules.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 验证码
 */
@Entity
@Table(name = "mto_security_code")
@Data
public class SecurityCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "key_", unique = true, nullable = false, length = 64)
    private String key;

    /**
     * 验证码
     */
    @Column(length = 16, nullable = false)
    private String code;

    /**
     * 目标：邮箱
     */
    @Column(length = 64)
    private String target;

    /**
     * 验证类型：注册验证、找回密码验证
     */
    @Column
    private int type;

    /**
     * 过期时间
     */
    @Column(name = "expired", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date expired;

    /**
     * 创建时间
     */
    @Column(name = "created", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created;

    /**
     * 状态：正常、关闭
     */
    @Column
    private int status;
}

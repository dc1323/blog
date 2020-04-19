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
import java.util.Date;

/**
 * 评论
 *
 * @author langhsu
 */
@Entity
@Table(name = "mto_comment", indexes = {
        @Index(name = "IK_POST_ID", columnList = "post_id")
})
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * 父评论ID
     */
    private long pid;

    /**
     * 所属内容ID
     */
    @Column(name = "post_id")
    private long postId;

    /**
     * 评论内容
     */
    @Column(name = "content")
    private String content;

    @Column(name = "created")
    private Date created;

    @Column(name = "author_id")
    private long authorId;

    private int status;
}

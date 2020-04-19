package com.blog.modules.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 文章标签映射表
 */
@Entity
@Table(name = "mto_post_tag", indexes = {
        @Index(name = "IK_TAG_ID", columnList = "tag_id")
})
@Data
public class PostTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "post_id")
    private long postId;

    @Column(name = "tag_id")
    private long tagId;

    private long weight;
}

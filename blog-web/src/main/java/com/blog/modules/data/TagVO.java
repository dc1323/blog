package com.blog.modules.data;

import com.blog.modules.entity.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author : langhsu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TagVO extends Tag implements Serializable {
    private static final long serialVersionUID = -7787865229252467418L;

    private PostVO post;
}

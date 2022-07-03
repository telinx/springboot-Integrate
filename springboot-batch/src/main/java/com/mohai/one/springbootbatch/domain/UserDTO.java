package com.mohai.one.springbootbatch.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @Auther: moerhai@qq.com
 * @Date: 2020/8/10 23:55
 */


@Data
@Entity
@Table(name = "user")
public class UserDTO {

    @Id // 表名实体唯一标识
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键自动生成策略
    private int id;
    @Column
    @Size(max = 10)
    @NotBlank(message = "姓名不能为空")
    private String name;
    @Column
    private int age;
    @Column
    private Date createTime;
    private byte status;
}

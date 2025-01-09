package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class MusicSheet {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long musicId;
    @TableField(exist = false)
    private Music music;
    private String url;
}

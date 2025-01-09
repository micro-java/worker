package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class Music {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String title;
    private String album;
    private String artist;
    @TableField(exist = false)
    private List<MusicSheet> musicSheets;
}

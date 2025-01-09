package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Music;
import com.example.demo.mapper.MusicMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicService {
    private  final  MusicMapper musicMapper;

    public MusicService(MusicMapper musicMapper){
        this.musicMapper = musicMapper;
    }

    public boolean save(Music music){
        return musicMapper.insert(music) > 0;
    }

    public Music getById(Long id){
        return musicMapper.selectById(id);
    }

    public List<Music> list(){
        return musicMapper.selectList(null);
    }

    public Page<Music> page(int pageIndex,int pageSize){
        return musicMapper.selectPage(new Page<>(pageIndex,pageSize),null);
    }

    public boolean update(Music music){
        return musicMapper.updateById(music) > 0;
    }

    public void delete(Long id){
        musicMapper.deleteById(id);
    }
}

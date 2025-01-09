package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Music;
import com.example.demo.service.MusicService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/music")
public class MusicController {
    private  final MusicService musicService;

    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @PostMapping
    public boolean save(@RequestBody Music music){
        return musicService.save(music);
    }

    @GetMapping("/{id}")
    public Music getById(Long id){
        return musicService.getById(id);
    }

    @GetMapping
    public List<Music> list(){
        return musicService.list();
    }

    @GetMapping("/page")
    public Page<Music> page(int pageIndex, int pageSize){
        return musicService.page(pageIndex,pageSize);
    }

    @PutMapping
    public boolean update(@RequestBody Music music){
        return musicService.update(music);
    }

    @DeleteMapping("/{id}")
    public void delete(Long id){
         musicService.delete(id);
    }
}

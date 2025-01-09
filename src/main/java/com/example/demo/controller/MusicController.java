package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Music;
import com.example.demo.service.MusicService;

@RestController
@RequestMapping("/music")
public class MusicController {

    private final MusicService musicService;

    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @PostMapping
    public boolean save(@RequestBody Music music) {
        return musicService.save(music);
    }

    @GetMapping("/{id}")
    public Music getById(@PathVariable Long id) {
        return musicService.getById(id);
    }

    @GetMapping
    public List<Music> list() {
        return musicService.list();
    }

    @GetMapping("/page")
    public Page<Music> page(@RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "10") int pageSize) {
        return musicService.page(pageIndex, pageSize);
    }

    @PutMapping
    public boolean update(@RequestBody Music music) {
        return musicService.update(music);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        musicService.delete(id);
    }
}

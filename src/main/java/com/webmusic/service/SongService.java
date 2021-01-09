package com.webmusic.service;

import java.util.List;

public interface SongService<E> {
    List<E> findAll();
    E findById(Long id);
    E save(E obj);
}


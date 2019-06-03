package jun.prospring5.ch8.service;

import jun.prospring5.ch8.entity.Album;
import jun.prospring5.ch8.entity.Singer;

import java.util.List;

public interface AlbumService {

    List<Album> findBySinger(Singer singer);

    List<Album> findByTitle(String title);
}

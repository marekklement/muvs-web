package cz.asmk.muvsweb.repository;

import org.springframework.data.repository.CrudRepository;

import cz.asmk.muvsweb.entity.Image;

public interface ImageRepository  extends CrudRepository<Image, Long> {
}

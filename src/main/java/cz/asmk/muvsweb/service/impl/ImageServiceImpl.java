package cz.asmk.muvsweb.service.impl;

import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.asmk.muvsweb.entity.Image;
import cz.asmk.muvsweb.repository.ImageRepository;
import cz.asmk.muvsweb.service.api.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageRepository imageRepository;

	@Override
	public Image get(Long id) {
		return imageRepository.findById(id).orElse(null);
	}

	@Override
	public List<Image> findAll() {
		return IterableUtils.toList(imageRepository.findAll());
	}

	@Override
	public Image delete(Long id) {
		Image image = this.get(id);
		imageRepository.delete(image);
		return image;
	}

	@Override
	public Image update(Image entity) {
		Image image = this.get(entity.getId());
		if(Objects.isNull(image)) throw new IllegalArgumentException("Image not found in database!");
		image = entity;
		return imageRepository.save(image);
	}

	@Override
	public Image save(Image entity) {
		return imageRepository.save(entity);
	}
}

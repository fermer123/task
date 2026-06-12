package ru.demo.task.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.demo.task.domain.task.TaskImage;
import ru.demo.task.service.ImageService;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Override
    public String upload(TaskImage image) {
        return "";
    }
}

package com.jayp.docker_manager.controller;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import com.jayp.docker_manager.services.DockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/containers")
public class DockerContainersController {

    private final DockerService dockerService;

    public DockerContainersController(DockerService dockerService) {
        this.dockerService = dockerService;
    }

    @GetMapping("")
    public List<Container> listImages(@RequestParam(required = false, defaultValue = "image-") boolean showAll) {
        return dockerService.listContainers(showAll);
    }

    @GetMapping("{id}/strat")
    public void startContainer(@PathVariable String id) {
        dockerService.startContainer(id);
    }

    @GetMapping("/{id}/stop")
    public void stopContainer(@PathVariable String id) {
        dockerService.stopContainer(id);
    }

    @DeleteMapping("/{id}")
    public void deleteContainer(@PathVariable String id) {
        dockerService.deleteContainer(id);
    }

    @PostMapping()
    public void createContainer(@RequestParam() String imageName) {
        dockerService.createContainer(imageName);
    }
}

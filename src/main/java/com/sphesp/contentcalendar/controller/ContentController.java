package com.sphesp.contentcalendar.controller;

import com.sphesp.contentcalendar.model.Content;
import com.sphesp.contentcalendar.model.Status;
import com.sphesp.contentcalendar.model.Type;
import com.sphesp.contentcalendar.repository.ContentCollectionRepository;
import com.sphesp.contentcalendar.repository.ContentJdbcTemplateRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/api/content")
@CrossOrigin // use the default config
public class ContentController{

    private final ContentJdbcTemplateRepository repository;

    public ContentController(ContentJdbcTemplateRepository repository) {
        this.repository = repository;
    }

    // make a request to find all the pieces of content in the system.
    @GetMapping
    public List<Content> findAll(){
        return repository.getAllContent();
    }

    // Create Read Update

    @GetMapping("/{id}")
    public Content findById(@PathVariable  Integer id){
        return repository.getContent(id);
    }

    /*
        `@RequestBody`says that content will be passed as part of the request body
     */
    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping
    public void create(@Valid @RequestBody Content content){
        repository.createContent(content.title(),content.desc(),content.status(),content.contentType(),content.url());
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Content  content, @PathVariable Integer id){
        if(!repository.existById(id))
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"Content not Found Exception");
        repository.updateContent(content.title(),content.desc(),content.status(),content.contentType(),content.url());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        repository.deleteContent(id);
    }

}

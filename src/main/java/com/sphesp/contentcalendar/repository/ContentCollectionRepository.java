package com.sphesp.contentcalendar.repository;

import com.sphesp.contentcalendar.model.Content;
import com.sphesp.contentcalendar.model.Status;
import com.sphesp.contentcalendar.model.Type;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.net.Inet4Address;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {
    /*
     * We not speaking to the database yet
     * But we want to keep a collection of peaces of memory together.
     */

    private final List<Content> contentList  = new ArrayList<>();

    public List<Content> findAll(){
        return contentList;
    }

    // Optional to deal with null values.
    public Optional<Content> findById(Integer id){
        return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
    }

    @PostConstruct
    public void init(){
        Content content = new Content(
                1,
                "My First Blog Post",
                "My first blog post",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                ""
        );
        contentList.add(content);

    }

    public void save(Content content){
        contentList.removeIf(e->e.id().equals(content.id()));
        contentList.add(content);
    }

    public boolean existById(Integer id){
        return contentList.stream().filter(c->c.id().equals(id)).count()==1;
    }

    public void deleteById(Integer id){
        contentList.removeIf(e->e.id().equals(id));
    }
}

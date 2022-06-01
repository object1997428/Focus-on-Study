package com.pos.posproject.Repository;



import com.pos.posproject.domain.Group;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Group save(Group group);

    Optional<Group> findByCode(Long code);

    Optional<Group> findByTitle(String title);

    Optional<Group> findByWriter(String writer);

    List<Group> findAll();
}

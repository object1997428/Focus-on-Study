package com.pos.posproject.service;

import PoSproject.PoS.Repository.MemoryPostRepository;
import PoSproject.PoS.Repository.PostRepository;
import PoSproject.PoS.domain.Group;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class postService {
    private final PostRepository postRepository = new MemoryPostRepository();

    public String registerPost(Group group) {//왜 저장하고 code말고 title저장하는지는 모르겠는데
        postRepository.save(group);
        return group.getTitle();
    }

    public List<Group> findPosts() {
        return postRepository.findAll();
    }

    public Optional<Group> findByTitle(String title) {
        return postRepository.findByTitle(title);
    }

    public Optional<Group> findByWriter(String writer) {
        return postRepository.findByWriter(writer);
    }


}

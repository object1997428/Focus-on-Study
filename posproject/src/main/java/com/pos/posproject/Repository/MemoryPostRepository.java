package com.pos.posproject.Repository;

import PoSproject.PoS.domain.Group;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryPostRepository implements PostRepository {

    private static Map<Long, Group> store = new HashMap<>();//코드, 포스트클래스
    private static long sequence = 0L;

    public void clearStore() {
        store.clear();
    }

    @Override
    public Group save(Group group) {
        group.setCode(++sequence);
        store.put(group.getCode(), group);
        return group;
    }

    @Override
    public Optional<Group> findByCode(Long code) {
        return Optional.ofNullable(store.get(code));
    }

    @Override
    public Optional<Group> findByTitle(String title) {
        return store.values().stream()
                .filter(group -> group.getTitle().equals(title))
                .findAny();
    }

    @Override
    public Optional<Group> findByWriter(String writer) {//작성자로 검색
        return store.values().stream()
                .filter(group -> group.getWriter_id().equals(writer))
                .findAny();
    }

    @Override
    public List<Group> findAll() {
        return new ArrayList<>(store.values());
    }
}

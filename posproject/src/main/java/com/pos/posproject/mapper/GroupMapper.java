package com.pos.posproject.mapper;

import com.pos.posproject.domain.Group;
import com.pos.posproject.domain.Member;
import com.pos.posproject.domain.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupMapper {

    public Member findMemberById(String id);

    public List<Member> findMembers();

    public void updatestudyTime(Member member);
    public List<Group> findGroups();

    public Group findOneByCode(Integer codes);

    public Group findOneByTitle(String titles);

    public void save(Group group);


    public void deleteByTitle(String id);
}

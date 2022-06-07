package com.pos.posproject;

import com.pos.posproject.domain.Group;
import com.pos.posproject.domain.Member;
import com.pos.posproject.domain.Student;
import com.pos.posproject.mapper.GroupMapper;
import com.pos.posproject.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@WebAppConfiguration
class PosprojectApplicationTests {

	// private StudentMapper studentMapper;
	@Autowired
	private GroupMapper groupMapper;

	// @Test
	// void 학생_생성() {
	// String code = "S20200102154530";
	// String name = "Son";
	// Student student = studentMapper.findOneByCode(code);
	//
	// if (student == null) {
	// student = new Student(name, code);
	// studentMapper.save(student);
	// } else {
	// student.setSaveDate(LocalDateTime.now());
	// studentMapper.update(student);
	// }
	// }
	//
	// @Test
	// void 학생_리스트_출력() {
	// studentMapper.findList().forEach(data -> System.out.println(data.getId()));
	// }
	//

	@Test
	void 그룹_생성() {
		Integer group_code = 4;
		Group group = groupMapper.findOneByCode(group_code);

		if (group == null) {
			group = new Group("title", "explain", 3, "mId");
			groupMapper.save(group);
			System.out.println(group.getGroup_explain().toString());
		}
	}

	@Test
	void 그룹_리스트_출력() {
		groupMapper.findGroups().forEach(Group -> System.out.println(
				Group.getGroup_title() + " " + Group.getGroup_explain()));
	}

	@Test
	void 회원_공부시간_수정() {
		Member member = groupMapper.findMemberById("qqq");
		member.setStudyTime(20);
		groupMapper.updatestudyTime(member);
	}

	@Test
	void 아이디_확인() {
		Member member = groupMapper.findMemberById("qqq");
		System.out.println(member.getName());
	}

	@Test
	void 회원_리스트_출력() {
		groupMapper.findMembers().forEach(member -> System.out.println(member.getName() + " " + member.getId()));
	}

}

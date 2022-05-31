package com.pos.posproject;

import com.pos.posproject.domain.Student;
import com.pos.posproject.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDateTime;

@SpringBootTest
@WebAppConfiguration
class PosprojectApplicationTests {

	@Autowired
	private StudentMapper studentMapper;

	@Test
	void 학생_생성() {
		String code = "S20200102154530";
		String name = "Son";
		Student student = studentMapper.findOneByCode(code);

		if (student == null) {
			student = new Student(name, code);
			studentMapper.save(student);
		} else {
			student.setSaveDate(LocalDateTime.now());
			studentMapper.update(student);
		}
	}

	@Test
	void 학생_리스트_출력() {
		studentMapper.findList().forEach(data -> System.out.println(data.getId()));
	}


}

package org.jp.spring.data.jpa.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.jp.spring.data.jpa.jpql.entity.Student;
import org.jp.spring.data.jpa.jpql.repositories.StudentRepository;
import org.jp.spring.data.jpa.jpql.starter.JpqlAndNativeQueriesApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest(bootstrapMode = BootstrapMode.LAZY)
@ContextConfiguration(classes = JpqlAndNativeQueriesApplication.class)
public class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	public void findByFname() {
		final List<Student> studentPage = studentRepository.findByFname("garry", Sort.by(Direction.DESC, "score"));
		assertThat(studentPage).hasSize(2).extracting(Student::getScore).allMatch(score -> score > 33);
	}

	@Test
	public void findByScoreBetween() {
		final Page<Object[]> studentPage = studentRepository.findByScoreBetween(40, 70, PageRequest.of(0, 3));
		studentPage.forEach(object -> System.out.println("first_name : " + object[0] + " lastname : " + object[1]));
		assertThat(studentPage).hasSize(3).anyMatch(object -> String.valueOf(object[0]).equals("tom"));
	}

	@Test
	public void updateStudentLnameById() {
		studentRepository.updateLName(3, "singh");
		assertThat(studentRepository.findById(3l).get().getLName()).isEqualTo("singh");
	}

	@Test
	public void deleteByFName() {
		studentRepository.deleteByFName("garry");
		assertEquals(8, studentRepository.count());
	}

	@Test
	public void saveStudent() {
		studentRepository.saveStudent("rohit", "sharma", 76);
		assertThat(studentRepository.findByFname("rohit", Sort.unsorted())).extracting(Student::getLName)
				.allMatch(lName -> lName.equals("sharma"));
	}

	@Test
	public void findAllStudentByIdIn() {
		final List<Student> studentList = studentRepository.findAllStudentByIdIn(Arrays.asList(3l, 4l, 7l, 8l),
				Sort.by(Direction.DESC, "fName"));
		studentList.forEach(System.out::println);
		assertThat(studentList).hasSize(4).extracting(Student::getFName).containsSequence("tom","kartik","garry","anjana");
	}
}

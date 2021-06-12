package org.jp.spring.data.jpa.jpql.repositories;

import java.util.List;

import org.jp.spring.data.jpa.jpql.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long> {

	@Query("from Student st where st.fName=:fName")
	List<Student> findByFname(String fName, Sort sort);

	@Query("select fName,lName from Student st where st.score between :min and :max")
	Page<Object[]> findByScoreBetween(float min, float max, Pageable pageable);

	@Modifying
	@Query("update Student set lName=:last_Name where id=:id")
	void updateLName(long id, @Param("last_Name") String lName);

	@Modifying
	@Query("delete from Student where fName=:fName")
	void deleteByFName(String fName);

	@Modifying
	@Query(value = "insert into student(first_name,last_name,score) values(:fName,:lName,:score)", nativeQuery = true)
	void saveStudent(String fName, String lName, float score);

	@Query("from Student st where st.id in (:ids)")
	List<Student> findAllStudentByIdIn(List<Long> ids, Sort sort);

}

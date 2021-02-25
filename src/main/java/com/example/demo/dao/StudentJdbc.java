package com.example.demo.dao;

import com.example.demo.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentJdbc {

    private final JdbcTemplate jdbcTemplate;

    public StudentJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Student get(int id) {
        String sql = "SELECT * FROM STUDENT WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapStudent, id);
    }

    public List<Student> getStudentByGroup(int study_group_id) {
        String sql = "SELECT * FROM STUDENT WHERE study_group_id = ?";
        return jdbcTemplate.query(sql, this::mapStudent, study_group_id);
    }

    public List<Student> getAll() {
        String sql = "SELECT * FROM STUDENT";
        return jdbcTemplate.query(sql, this::mapStudent);
    }

    public int CreateStudent(int id, String surname, String name, String second_name, int study_group_id) {
        return jdbcTemplate.update(
                "INSERT INTO STUDENT VALUES(?, ?, ?, ?, ?)",
                id, surname, name, second_name, study_group_id);
    }

    public int CreateStudent(Student student) {
        return jdbcTemplate.update(
                "INSERT INTO STUDENT VALUES(?, ?, ?, ?, ?)",
                student.getId(), student.getSurname(),
                student.getName(), student.getSecondName(),
                student.getStudy_group_id());
    }

    public int UpdateStudent(int id, String surname, String name, String second_name, int study_group_id) {
        return jdbcTemplate.update(
                "MERGE INTO CUSTOMER KEY (ID) VALUES (?, ?, ?, ?, ?)",
                id, surname, name, second_name, study_group_id);

    }

    public int UpdateStudent(Student student) {
        return jdbcTemplate.update(
                "MERGE INTO CUSTOMER KEY (ID) VALUES (?, ?, ?, ?, ?)",
                student.getId(), student.getSurname(),
                student.getName(), student.getSecondName(),
                student.getStudy_group_id());
    }

    private Student mapStudent(ResultSet rs, int i) throws SQLException {
        return new Student(
                rs.getInt("id"),
                rs.getString("surname"),
                rs.getString("name"),
                rs.getString("second_name")
        );
    }

    public int delete(String id) {
        return jdbcTemplate.update("DELETE FROM STUDENT WHERE id = ?", id);
    }

}
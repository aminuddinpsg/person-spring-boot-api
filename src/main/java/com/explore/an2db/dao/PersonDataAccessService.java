package com.explore.an2db.dao;

import com.explore.an2db.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("h2Db")
public class PersonDataAccessService implements PersonDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        String query = "insert into person(id,name)values (?,?)";
        jdbcTemplate.update(query,id,person.getName());
        return 0;
    }

    @Override
    public List<Person> getAllPerson() {
        String query = "select id,name from person";
        return jdbcTemplate.query(query, (rs, row) ->
                new Person(UUID.fromString(rs.getString("id")),
                        rs.getString("name")));
    }

    @Override
    public Optional<Person> getPerson(UUID id) {
        String query = "select id,name from person where id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id},
                (resultSet, i) -> Optional.of(new Person(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("name"))));
    }
}

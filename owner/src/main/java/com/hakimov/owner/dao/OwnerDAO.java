package com.hakimov.owner.dao;

import com.hakimov.owner.models.Owner;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class OwnerDAO {

    private final JdbcTemplate jdbcTemplate;

    public List<Owner> getAllOwners() {
        return jdbcTemplate.query("SELECT * FROM owner",
                new BeanPropertyRowMapper<>(Owner.class));
    }

    public Owner getOwnerById(int id) {
        return jdbcTemplate.query("SELECT * FROM owner WHERE id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Owner.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public void saveOwner(Owner owner) {
        jdbcTemplate.update("INSERT INTO owner(name, surname, phone, email)" +
                " VALUES (?, ?, ?, ?)" ,
                owner.getName(),
                owner.getSurname(),
                owner.getPhone(),
                owner.getEmail());
    }

    public void updateOwner(Owner owner, int id) {
        jdbcTemplate.update("UPDATE owner SET name=?, surname=?, phone=?, email=? WHERE id=?",
                owner.getName(),
                owner.getSurname(),
                owner.getPhone(),
                owner.getEmail(), id);
    }

    public void deleteOwnerById(int id) {
        jdbcTemplate.update("DELETE FROM owner WHERE id=?",id);
    }

}

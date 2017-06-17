package com.marvelsassemble.managebio;

import com.marvelsassemble.authenticate.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by hemantv on 17/6/17.
 */
public interface BioRepository extends CrudRepository<User, String> {

    public User findByUname(String uname);
    public List<User> findByIsAvenger(boolean isAvenger);
    public List<User> findByIsXmen(boolean isXmen);
}

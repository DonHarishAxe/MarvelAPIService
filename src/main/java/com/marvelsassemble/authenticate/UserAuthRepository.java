package com.marvelsassemble.authenticate;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by hemantv on 16/6/17.
 */
public interface UserAuthRepository extends CrudRepository<User, String> {

    public User findByUname(String unmae);

}

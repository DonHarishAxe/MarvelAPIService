package com.marvelsassemble.utils;

/**
 * Created by hemantv on 18/6/17.
 */
public class SaltGenrator {
    public static void main(String[] args) {
        System.out.println(BCrypt.gensalt());
    }
}

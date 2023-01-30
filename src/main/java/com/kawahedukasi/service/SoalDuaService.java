package com.kawahedukasi.service;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SoalDuaService {

    public long hitungFaktorial(int n) {
        if (n <= 1) {
            return 1;
        }

        return n * hitungFaktorial(n - 1);
    }
}

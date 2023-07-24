package com.angelobdev.casadellibro.service;

import com.angelobdev.casadellibro.repository.RitiriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RitiriService {

    @Autowired
    private RitiriRepository ritiriRepository;

}

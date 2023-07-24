package com.angelobdev.casadellibro.service;

import com.angelobdev.casadellibro.repository.OrdiniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdiniService {

    @Autowired
    private OrdiniRepository ordiniRepository;

}

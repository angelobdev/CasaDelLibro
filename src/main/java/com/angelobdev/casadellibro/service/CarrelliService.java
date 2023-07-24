package com.angelobdev.casadellibro.service;

import com.angelobdev.casadellibro.repository.CarrelliRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarrelliService {

    @Autowired
    private CarrelliRepository carrelliRepository;

}

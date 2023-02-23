package com.glebgol.restapi.services;

import com.glebgol.restapi.dto.CalculationParamsDTO;

import java.io.File;

public interface CalculationService {
    File calculate(CalculationParamsDTO calculationParamsDTO);
}

package com.user.spring_data_rest.service;

import com.user.spring_data_rest.entity.Contract;

import java.util.List;

public interface ContractService {
    List<Contract> getContracts();
    Contract getContract(int id);
    Contract getContractByContractNumber(int contractNumber);
    void saveOrUpdateContract(Contract contract);
    void deleteContract(int id);
}

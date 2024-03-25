package com.user.spring_data_rest.dao;

import com.user.spring_data_rest.entity.Contract;

import java.util.List;

public interface ContractDAO {
    List<Contract> getContracts();
    Contract getContract(int id);
    Contract getContractByContractNumber(int contractNumber);
    void saveOrUpdateContract(Contract contract);
    void deleteContract(int id);
}

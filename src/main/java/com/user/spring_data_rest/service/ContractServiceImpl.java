package com.user.spring_data_rest.service;


import com.user.spring_data_rest.dao.ContractDAO;
import com.user.spring_data_rest.entity.Contract;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractDAO contractDAO;

    @Override
    public List<Contract> getContracts() {
        return contractDAO.getContracts();
    }

    @Override
    public Contract getContract(int id) {
        return contractDAO.getContract(id);
    }

    @Override
    public Contract getContractByContractNumber(int contractNumber) {
        return contractDAO.getContractByContractNumber(contractNumber);
    }

    @Override
    @Transactional
    public void saveOrUpdateContract(Contract contract) {
        contractDAO.saveOrUpdateContract(contract);
    }

    @Override
    @Transactional
    public void deleteContract(int id) {
        contractDAO.deleteContract(id);
    }
}

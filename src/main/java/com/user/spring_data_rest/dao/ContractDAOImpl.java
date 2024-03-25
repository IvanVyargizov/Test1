package com.user.spring_data_rest.dao;

import com.user.spring_data_rest.entity.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ContractDAOImpl implements ContractDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Contract> getContracts() {
        TypedQuery<Contract> query = entityManager.createQuery("SELECT c FROM Contract c", Contract.class);
        List<Contract> contracts = query.getResultList();
        return contracts;
    }

    @Override
    public Contract getContract(int id) {
        return entityManager.find(Contract.class, id);
    }

    @Override
    public Contract getContractByContractNumber(int contractNumber) {
        try {
            TypedQuery<Contract> query = entityManager.createQuery("SELECT c FROM Contract c " +
                    "WHERE c.contractNumber = :contractNumber", Contract.class);
            query.setParameter("contractNumber", contractNumber);
            return query.getSingleResult();
        } catch (NoResultException e)  {
            return null;
        }

    }

    @Override
    public void saveOrUpdateContract(Contract contract) {
        entityManager.merge(contract);
    }

    @Override
    public void deleteContract(int id) {
        Contract contract = entityManager.find(Contract.class, id);
        if (contract != null) {
            entityManager.remove(contract);
        }
    }
}

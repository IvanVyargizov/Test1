package com.user.spring_data_rest.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "contracts")
@EntityListeners(Contract.ContractListener.class)
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "contract_number")
    private int contractNumber;

    @Column(name = "contract_date")
    private LocalDate contractDate;

    @Column(name = "contract_update_date")
    private LocalDate contractUpdateDate;

    @Transient
    private boolean relevant;

    public static class ContractListener {
        @PrePersist
        @PreUpdate
        public void prePersistAndPreUpdate(Contract contract) {
            contract.setContractUpdateDate(LocalDate.now());
        }

        @PostLoad
        public void postLoad(Contract contract) {
            boolean isRelevant = contract.getContractUpdateDate().isAfter(LocalDate.now().minusDays(60))
                    || contract.getContractUpdateDate().isEqual(LocalDate.now());
            contract.setRelevant(isRelevant);
        }
    }

    public Contract() {
    }

    public Contract(int id, int contractNumber, LocalDate contractDate) {
        this.id = id;
        this.contractNumber = contractNumber;
        this.contractDate = contractDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContractNumber() {
        return this.contractNumber;
    }

    public void setContractNumber(int contractNumber) {
        this.contractNumber = contractNumber;
    }

    public LocalDate getContractDate() {
        return this.contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    public LocalDate getContractUpdateDate() {
        return this.contractUpdateDate;
    }

    public void setContractUpdateDate(LocalDate contractUpdateDate) {
        this.contractUpdateDate = contractUpdateDate;
    }

    public boolean getRelevant() {
        return this.relevant;
    }

    public void setRelevant(boolean relevant) {
        this.relevant = relevant;
    }
}

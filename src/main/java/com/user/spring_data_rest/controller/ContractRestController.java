package com.user.spring_data_rest.controller;

import com.user.spring_data_rest.entity.Contract;
import com.user.spring_data_rest.service.ContractService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContractRestController {
    @Autowired
    private ContractService contractService;

    @GetMapping("/contracts")
    @Operation(summary = "Get all contracts")
    public List<Contract> showAllContracts() {
        return contractService.getContracts();
    }

    @GetMapping("/contracts/{id}")
    @Operation(summary = "Get contract by id")
    public Contract getContract(@PathVariable int id) {
        Contract contract = contractService.getContract(id);
        if (contract == null) {
            throw new RuntimeException("Contract with id = " + id + " not found");
        }
        return contract;
    }

    @PostMapping("/contracts")
    @Operation(summary = "Add new contract")
    public ResponseEntity<String> addNewContract(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Contract to add",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = Contract.class),
                            examples = @ExampleObject(value
                                    = "{\"contractNumber\":9999,\"contractDate\":\"2024-03-22\"}")
                    )
            )
            @RequestBody Contract contract
    ) {
        if (contract.getId() > 0) {
            throw new RuntimeException("Have to use the PUT request to updating");
        }
        if (contractService.getContractByContractNumber(contract.getContractNumber()) != null) {
            throw new RuntimeException("Contract with contract number = "
                    + contract.getContractNumber() + " already exist");
        }
        contractService.saveOrUpdateContract(contract);
        return ResponseEntity.ok("Contract was added successfully");
    }

    @PutMapping("/contracts")
    @Operation(summary = "Update contract")
    public ResponseEntity<String> updateContract(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Contract to update",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = Contract.class),
                            examples = @ExampleObject(value
                                    = "{\"id\":1, \"contractNumber\":1111,\"contractDate\":\"2024-03-22\"}")
                    )
            )
            @RequestBody Contract contract
    ) {
        if (contract.getId() == 0) {
            throw new RuntimeException("Have to use the POST request to adding");
        }
        if (contractService.getContract(contract.getId()) == null) {
            throw new RuntimeException("Contract with id = " + contract.getId() + " not found");
        }
        contractService.saveOrUpdateContract(contract);
        return ResponseEntity.ok("Contract was updated successfully");
    }

    @DeleteMapping("/contracts/{id}")
    @Operation(summary = "Delete contract by id")
    public ResponseEntity<String>  deleteContract(@PathVariable int id) {
        Contract contract = contractService.getContract(id);
        if (contract == null) {
            throw new RuntimeException("Contract with id = " + id + " not found");
        }
        contractService.deleteContract(id);
        return ResponseEntity.ok("Contract was deleted successfully");
    }
}

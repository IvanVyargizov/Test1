DROP TABLE contracts;

CREATE TABLE contracts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    contract_number INT NOT NULL,
    contract_date DATE NOT NULL,
    contract_update_date DATE NOT NULL
);

INSERT INTO contracts (contract_number, contract_date, contract_update_date) VALUES
(1111, '2024-01-01', '2024-01-01'),
(2222, '2024-02-15', '2024-03-15'),
(3333, '2023-12-10', '2024-02-01'),
(4444, '2023-10-18', '2023-10-18'),
(5555, '2024-03-10', '2024-03-10');
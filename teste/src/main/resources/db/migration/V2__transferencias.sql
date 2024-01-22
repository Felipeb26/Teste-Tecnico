CREATE TABLE IF NOT EXISTS TRANSFERENCIA_TB (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    contaEnvio VARCHAR(255),
    contatoNome VARCHAR(255),
    pessoaEnvio VARCHAR(255),
    valor DECIMAL,
    contaDestino BIGINT,
    dataTransferencia TIMESTAMP,
    dataCadastro TIMESTAMP,
    FOREIGN KEY (contaDestino) REFERENCES CONTATO_TB(id)
);
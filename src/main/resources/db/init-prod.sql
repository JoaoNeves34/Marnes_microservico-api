-- Script de inicialização para banco de dados de produção (PostgreSQL)
-- Execute este script após criar o banco de dados

CREATE TABLE IF NOT EXISTS tb_categorias (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS tb_madeiras (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    origem VARCHAR(50) NOT NULL,
    densidade VARCHAR(30) NOT NULL,
    resistencia VARCHAR(50) NOT NULL,
    cor VARCHAR(30) NOT NULL,
    categoria_id BIGINT NOT NULL,
    CONSTRAINT fk_categoria FOREIGN KEY (categoria_id) REFERENCES tb_categorias(id)
);

-- Inserir categorias padrão
INSERT INTO tb_categorias (nome) VALUES ('Natural') ON CONFLICT (nome) DO NOTHING;
INSERT INTO tb_categorias (nome) VALUES ('Processada') ON CONFLICT (nome) DO NOTHING;

-- Dados de exemplo (opcional)
INSERT INTO tb_madeiras (nome, origem, densidade, resistencia, cor, categoria_id)
VALUES 
('Ipê', 'Brasil - Amazônia', '1050 kg/m³', 'Alta resistência a cupins', 'Marrom escuro', 1),
('Mogno', 'Brasil - Mata Atlântica', '640 kg/m³', 'Média resistência', 'Avermelhado', 1),
('MDF', 'Industrial', '750 kg/m³', 'Baixa resistência à umidade', 'Bege', 2)
ON CONFLICT DO NOTHING;

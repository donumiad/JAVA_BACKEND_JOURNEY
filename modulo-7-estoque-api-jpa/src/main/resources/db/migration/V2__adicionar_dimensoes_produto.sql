ALTER TABLE produtos
    ADD COLUMN largura_cm INTEGER,
    ADD COLUMN altura_cm INTEGER,
    ADD COLUMN profundidade_cm INTEGER,
    ADD CONSTRAINT chk_produtos_dimensoes CHECK (
        (largura_cm IS NULL AND altura_cm IS NULL AND profundidade_cm IS NULL)
            OR
        (
            largura_cm IS NOT NULL AND largura_cm > 0
                AND altura_cm IS NOT NULL AND altura_cm > 0
                AND profundidade_cm IS NOT NULL AND profundidade_cm > 0
            )
        );
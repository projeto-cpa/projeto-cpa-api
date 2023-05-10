package br.com.biopark.cpa.models.enums;

public enum TipoPergunta {
    DESCRITIVA("descritiva"), AVALIATIVA("avaliativa");

    private String code;

    private TipoPergunta(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

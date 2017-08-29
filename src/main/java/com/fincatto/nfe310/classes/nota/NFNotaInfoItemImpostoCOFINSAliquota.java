package com.fincatto.nfe310.classes.nota;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.simpleframework.xml.Element;

import com.fincatto.nfe310.classes.NFBase;
import com.fincatto.nfe310.classes.NFNotaInfoSituacaoTributariaCOFINS;
import com.fincatto.nfe310.validadores.BigDecimalParser;

public class NFNotaInfoItemImpostoCOFINSAliquota extends NFBase {
    private static final List<NFNotaInfoSituacaoTributariaCOFINS> SITUACOES_VALIDAS = Arrays.asList(NFNotaInfoSituacaoTributariaCOFINS.OPERACAO_TRIBUTAVEL_CUMULATIVO_NAO_CUMULATIVO, NFNotaInfoSituacaoTributariaCOFINS.OPERACAO_TRIBUTAVEL_ALIQUOTA_DIFERENCIADA, NFNotaInfoSituacaoTributariaCOFINS.OPERACAO_DIREITO_CREDITO_VINCULADA_EXCLUSIVAMENTE_RECEITA_TRIBUTADA_MERCADO_INTERNO);

    @Element(name = "CST", required = true)
    private NFNotaInfoSituacaoTributariaCOFINS situacaoTributaria;

    @Element(name = "vBC", required = true)
    private String valorBaseCalulo;

    @Element(name = "pCOFINS", required = true)
    private String percentualAliquota;

    @Element(name = "vCOFINS", required = true)
    private String valor;

    public void setSituacaoTributaria(final NFNotaInfoSituacaoTributariaCOFINS situacaoTributaria) {
        if (!NFNotaInfoItemImpostoCOFINSAliquota.SITUACOES_VALIDAS.contains(situacaoTributaria)) {
            throw new IllegalStateException("Situacao tributaria invalido");
        }
        this.situacaoTributaria = situacaoTributaria;
    }

    public void setValorBaseCalulo(final BigDecimal valorBaseCalulo) {
        this.valorBaseCalulo = BigDecimalParser.tamanho15Com2CasasDecimais(valorBaseCalulo, "Valor Base de Calculo COFINS Item");
    }

    public void setPercentualAliquota(final BigDecimal aliquota) {
        this.percentualAliquota = BigDecimalParser.tamanho7ComAte4CasasDecimais(aliquota, "Aliquota COFINS Item");
    }

    public void setValor(final BigDecimal valor) {
        this.valor = BigDecimalParser.tamanho15Com2CasasDecimais(valor, "Valor COFINS Item");
    }

    public NFNotaInfoSituacaoTributariaCOFINS getSituacaoTributaria() {
        return this.situacaoTributaria;
    }

    public String getValorBaseCalulo() {
        return this.valorBaseCalulo;
    }

    public String getPercentualAliquota() {
        return this.percentualAliquota;
    }

    public String getValor() {
        return this.valor;
    }
}
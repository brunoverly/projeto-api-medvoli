package medVoli.api.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import medVoli.api.dto.EnderecoDto;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private int numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(EnderecoDto dto) {
        this.logradouro = dto.logradouro();
        this.bairro = dto.bairro();
        this.cep = dto.cep();
        this.numero = dto.numero();
        this.complemento = dto.complemento();
        this.cidade = dto.cidade();
        this.uf = dto.uf();
    }

    public void atualizarEndereco(EnderecoDto dto) {
        if(dto.logradouro() != null) this.logradouro = dto.logradouro();
        if(dto.bairro() != null) this.bairro = dto.bairro();
        if(dto.cep() != null) this.cep = dto.cep();
        if(dto.numero() != 0) this.numero = dto.numero();
        if(dto.complemento() != null) this.complemento = dto.complemento();
        if(dto.cidade() != null) this.cidade = dto.cidade();
        if(dto.uf() != null) this.uf = dto.uf();
    }
}

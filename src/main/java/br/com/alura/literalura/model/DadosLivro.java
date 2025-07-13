package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DadosAutor> autor,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Double numeroDownloads) {

    @Override
    public String toString() {
        String formatacaoTitulo = (titulo != null) ? titulo : "Título Desconhecido.";
        String formatacaoAutor = "Autor Desconhecido.";
        if (autor != null && !autor.isEmpty()) {
            formatacaoAutor = autor.get(0).nomeAutor();
        }

        return "\nTítulo = " + formatacaoTitulo +
                "\nAutor = " + formatacaoAutor +
                "\nIdioma = " + (idiomas != null ? String.join(", ", idiomas) : "N/A") +
                "\nNúmero de Downloads = " + numeroDownloads ;
    }
}
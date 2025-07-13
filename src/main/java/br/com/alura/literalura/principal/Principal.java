package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.*;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private  ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";

    private LivroRepository repositorio;
    private AutorRepository autorRepositorio;

    public Principal(LivroRepository repositorio, AutorRepository autorRepositorio) {
        this.repositorio = repositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void exibeMenu(){
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    \n****************************************
                            BEM VINDO AO LITERALURA!
                    ****************************************
                    
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em determinado idioma
                    6 - Top 5 livros mais baixados
                    0 - Sair
                    
                    Escolha uma opção válida:
                    """;
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarTitulo();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosPorAno();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 6:
                    buscarTop5LivrosMaisBaixados();
                    break;
                case 0:
                    System.out.println("Finalizando a aplicação...");
                    break;
                default:
                    System.out.println("Opção inválida! Por favor, escolha novamente.");
            }
        }
    }

    private void buscarTitulo() {
        System.out.println("Digite o título do livro:");
        var nomeLivro = leitura.nextLine();

        Optional<Livro> livroEncontrado = repositorio.findByTituloContainingIgnoreCase(nomeLivro);

        if (livroEncontrado.isPresent()) {
            System.out.println(livroEncontrado.get());
        } else {
            System.out.println("Livro não encontrado no banco de dados. Buscando na API Gutendex...");
            DadosLivro dadosLivroApi = obterDadosApi(nomeLivro);

            if (dadosLivroApi != null) {
                Livro novoLivro = new Livro(dadosLivroApi);

                if (dadosLivroApi.autor() != null && !dadosLivroApi.autor().isEmpty()) {
                    DadosAutor dadosAutorApi = dadosLivroApi.autor().get(0);
                    Optional<Autor> autorExistente = autorRepositorio.findByNomeAutorIgnoreCase(dadosAutorApi.nomeAutor());
                    Autor autor;

                    if (autorExistente.isPresent()) {
                        autor = autorExistente.get();
                        System.out.println("Autor " + autor.getNomeAutor() + " já existe no banco de dados. Reutilizando...");
                    } else {
                        autor = new Autor(dadosAutorApi);
                        System.out.println("Novo autor " + autor.getNomeAutor() + " encontrado. Salvando...");
                    }
                    novoLivro.setAutor(autor);
                } else {
                    System.out.println("Nenhum autor encontrado para este livro na API. O livro será registrado sem autor.");
                }
                repositorio.save(novoLivro);
                System.out.println("\n*** Livro encontrado e salvo no banco de dados! ***");
                System.out.println(novoLivro);
                System.out.println("****************************************");
            } else {
                System.out.println("Livro não encontrado na API.");
            }
        }
    }
        private DadosLivro obterDadosApi(String nomeLivro) {
            var json = consumoApi.obterDados(ENDERECO + nomeLivro.replace(" ", "+"));
            Dados dados = conversor.obterDados(json, Dados.class);

            if (dados != null && !dados.resultados().isEmpty()) {
                return dados.resultados().get(0);
            }
            return null;
        }

    private void listarLivrosRegistrados() {
        List<Livro> livroBanco = repositorio.findAll();
            if (livroBanco.isEmpty()) {
            System.out.println("Nenhum livro registrado ainda.");
            return;
            }
        System.out.println("********** LIVROS REGISTRADOS **********\n");
        livroBanco.stream()
                .sorted(Comparator.comparing(Livro::getTitulo))
                .forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        List<Autor> autoresBanco = autorRepositorio.findAll();

        if (autoresBanco.isEmpty()) {
            System.out.println("Nenhum autor registrado ainda.");
            return;
        }

        Set<Autor> autoresUnicos = new TreeSet<>(Comparator.comparing(Autor::getNomeAutor));
        autoresUnicos.addAll(autoresBanco);

        System.out.println("\n********* AUTORES REGISTRADOS *********\n");
        autoresUnicos.forEach(System.out::println);

    }

    private void listarAutoresVivosPorAno() {
        System.out.println("Digite o ano para busca:");
        var anoBuscado = leitura.nextInt();
        leitura.nextLine();

        List<Autor> autoresVivos = autorRepositorio.buscarAutoresVivosNoAno(anoBuscado);

        if (autoresVivos.isEmpty()) {
            System.out.println("Nenhum autor vivo encontrado no ano " + anoBuscado + ".");
        } else {
            System.out.println("\n******** AUTORES VIVOS EM " + anoBuscado + " ********\n");
            autoresVivos.stream()
                    .sorted(Comparator.comparing(Autor::getNomeAutor))
                    .forEach(System.out::println);
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("Digite o idioma:");
        var idioma = leitura.nextLine().trim().toLowerCase();

        List<Livro> livrosIdiomas = repositorio.findByIdiomasContaining(idioma);

        if (livrosIdiomas.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o idioma '" + idioma.toUpperCase() + "'.");
        } else {
            System.out.println("\n******** LIVROS NO IDIOMA '" + idioma.toUpperCase() + "' ********\n");
            livrosIdiomas.stream()
                    .sorted(Comparator.comparing(Livro::getTitulo))
                    .forEach(System.out::println);
        }

    }

    private void buscarTop5LivrosMaisBaixados() {
        System.out.println("\n****** TOP 5 LIVROS MAIS BAIXADOS ******");
        List<Livro> topLivros = repositorio.findTop5ByOrderByNumeroDownloadsDesc();
        if (topLivros.isEmpty()) {
            System.out.println("Nenhum livro registrado para gerar o top 5.");
            return;
        }
        topLivros.forEach(l ->
                System.out.println("Livro: " + l.getTitulo() + " ->  Número de downloads: " + String.format("%.0f", l.getNumeroDownloads())));
        System.out.println("****************************************");

    }
}
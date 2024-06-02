package br.com.alura.fipe.principal;

import br.com.alura.fipe.model.Dados;
import br.com.alura.fipe.model.Veiculo;
import br.com.alura.fipe.model.Modelo;
import br.com.alura.fipe.service.ConsumoAPI;
import br.com.alura.fipe.service.ConverteDados;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner scan = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados converte = new ConverteDados();
    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    public void exibirMenu(){
        var menu = """
            *** OPÇÕES ***
            Carro
            Moto
            Caminhão
            Digite uma das opções para consulta:
            """;
        System.out.println(menu);
        var opcao = scan.nextLine();
        String endereco;
        if(opcao.toLowerCase().contains("carr")){
            endereco = URL_BASE+"carros/marcas/";
        }else if(opcao.toLowerCase().contains("mot")){
            endereco = URL_BASE+"motos/marcas/";
        }else{
            endereco = URL_BASE+"caminhoes/marcas/";
        }
        var json = consumo.obterDados(endereco);
        var dados = converte.obterLista(json, Dados.class);
        dados.stream()
                .sorted(Comparator.comparing(Dados::nome))
                .forEach(System.out::println);

        System.out.println("\nInforme o código:");
        var codigo = scan.nextInt();

        endereco = endereco+codigo+"/modelos/";
        json = consumo.obterDados(endereco);
        var modeloslista = converte.obterDados(json, Modelo.class);
        modeloslista.modelos().stream()
                .sorted(Comparator.comparing(Dados::nome))
                .forEach(System.out::println);

        System.out.println("\nDigite um trecho do nome do veículo para consulta:");
        scan.nextLine();
        var nomeDoVeiculo = scan.nextLine();
            List<Dados> modelosFiltrados = modeloslista.modelos().stream()
                    .filter(m -> m.nome().toLowerCase().contains(nomeDoVeiculo.toLowerCase()))
                    .collect(Collectors.toList());

            System.out.println("\nModelos Filtrados:");
            System.out.println();
            modelosFiltrados.stream()
                    .forEach(System.out::println);

        System.out.println("\nInforme o código do modelo:");
        Integer codigoDoModelo = scan.nextInt();

        endereco = endereco + codigoDoModelo + "/anos/";
        json = consumo.obterDados(endereco);
        var anosDoModelo = converte.obterLista(json, Dados.class);

        List<Dados> listaVeiculos= anosDoModelo.stream()
                .sorted(Comparator.comparing(Dados::codigo).reversed())
                .collect(Collectors.toList());

        System.out.println("\nTodos os veículos com os valores por ano");
        for (Dados dadosVeiculo : listaVeiculos){
            var anoVeiculo = dadosVeiculo.codigo();
            var enderecoAnos = endereco + anoVeiculo+"/";
            json = consumo.obterDados(enderecoAnos);
            var veiculo = converte.obterDados(json, Veiculo.class);
            System.out.println(veiculo);

        }
    }
}

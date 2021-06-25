import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class InfoSorter {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        List<Users> users = new ArrayList<>();

        System.out.println("Insira o caminho do arquivo a ser lido: ");
        String strPath = sc.next();

        File sourceFile = new File(strPath);

        //Fazendo a leitura do arquivo, pegando os dados do mesmo e atribuindo a um vetor e depois uma lista de objetos do tipo Users e lidando com exceptions de maneira simples
        while (strPath != null) {
            try (BufferedReader br = new BufferedReader(new FileReader(sourceFile))) {

                String line = br.readLine();

                while (line != null) {

                    if (line.isBlank()) {
                        line = br.readLine();
                    }

                    String[] values = line.split("\\s+");
                    String tel = values[0].trim();
                    String status = values[1].trim();
                    users.add(new Users(tel, status));
                    line = br.readLine();
                }
                break;

            } catch (IOException e) {
                System.out.println("Erro: " + e.getMessage());
                strPath = sc.next();
            } catch (ArrayIndexOutOfBoundsException e2) {
                System.out.println("Tipo invalido ");
                strPath = sc.next();
            }
        }
        //sorteando
        List<Users> numerosBrasileiros = users.stream()
                .filter(t -> t.getTelephone().startsWith("55"))
                .collect(Collectors.toList());

        List<Users> numerosChilenos = users.stream()
                .filter(t -> t.getTelephone().startsWith("56"))
                .collect(Collectors.toList());
        List<Users> numerosMexicanos = users.stream()
                .filter(t -> t.getTelephone().startsWith("52"))
                .collect(Collectors.toList());

        System.out.println("Agora insira o caminho de onde ele vai ser criado: ");
        String targetStr = sc.next();

        File targetFile = new File(targetStr);

        //Gravando no arquivo as informações sorteadas
        while (targetStr != null) {


            try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile))) {
                bw.write(Country.BRASIL + "," + numerosBrasileiros.size() + "," + numerosBrasileiros.stream()
                        .filter(n -> n.getStatus().equals("assinado")).count());
                bw.newLine();

                bw.write(Country.CHILE + "," + numerosChilenos.size() + "," + numerosChilenos.stream()
                        .filter(n -> n.getStatus().equals("assinado")).count());
                bw.newLine();

                bw.write(Country.MEXICO + "," + numerosMexicanos.size() + "," + numerosMexicanos.stream()
                        .filter(n -> n.getStatus().equals("assinado")).count());
                bw.newLine();
                System.out.println(targetStr + " ARQUIVO CRIADO!");
                break;

            } catch (IOException e) {
                System.out.println("Erro na criação do arquivo, insira novamente o caminho: " + e.getMessage());
                targetStr = sc.next();
            }
        }
    }
}







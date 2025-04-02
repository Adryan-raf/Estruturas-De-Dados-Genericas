import Arvores_Recursivas.AVL_Recursiva.AVLR;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
    
        AVLR<Integer> avlr = new AVLR<>();

        menu(avlr);        
    }

    public static void menu(AVLR<Integer> avlr){

        Scanner scan = new Scanner(System.in);
        int op;
        do{
            System.out.println("MENU:");
            System.out.println("1. Inserir valor da árvore");
            System.out.println("2. Remover valor da árvore");
            System.out.println("3. Realizar passeio em ordem");
            System.out.println("4. Realizar passeio por nível");
            System.out.println("0. Finalizar programa");

            op = scan.nextInt();

            switch (op){
                
                case 1: System.out.println("Digite o valor a ser inserido.");
                        avlr.insert(scan.nextInt());
                        break;
                
                case 2: System.out.println("Digite o valor a ser removido.");
                        avlr.remove(scan.nextInt());
                        break;

                case 3: System.out.println("\nPasseio em ordem:");
                        avlr.emOrdem();
                        break;
                
                case 4: System.out.println("\nPasseio por nível:");
                        avlr.porNivel();
                        break;

                case 0: break;

                default:System.out.println("Opção inválida, tente novamente.");
                        break;
            }

        }while(op!=0);

        scan.close();
    }
}

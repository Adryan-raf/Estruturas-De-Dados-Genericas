

public class AVL <T extends Comparable<T>> {

    private AVLNode<T> root;


    public AVLNode<T> getroot() {
        return this.root;
    }
    public void setroot(AVLNode<T> root) {
        this.root = root;
    }

    public boolean isEmpty(){
        return this.root == null;
    }

    public void height(T value){
        if(this.isEmpty()){
            System.out.println("Árvore Vázia");
        }
        else{
            int resultado = heightNode(value);
            if(resultado == -1)
                System.out.println("valor não encontrado!");
            
            else 
                System.out.println("A Altura do nó é: " + resultado);
        }

    }
    private int heightNode(T value){

        AVLNode<T> aux = this.root;
        int resultado;
        do { 
            resultado = value.compareTo(aux.getInfo());
            
            if(resultado<0)
                aux = aux.getLeft();
            
            else
                aux = aux.getRight();

        } while (aux != null && resultado != 0);
        
        if(aux != null)
            return aux.getHeight();

        else
            return -1;
    }

    public void insert(T value){
        if(this.isEmpty()){
            System.out.println("Árvore Vázia");
        }
        else{
            this.root = backInsert(this.root, value);
        }
    }

    private AVLNode<T> backInsert(AVLNode<T> r, T value){
        
        return r;
    }
    
}
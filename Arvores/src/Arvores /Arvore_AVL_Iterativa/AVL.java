import java.util.Stack;

public class AVL <T extends Comparable<T>> {

    private AVLNode<T> root;
    private boolean status;


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
            int result = heightNode(value);
            if(result == -1)
                System.out.println("valor não encontrado!");
            
            else 
                System.out.println("A Altura do nó é: " + result);
        }

    }
    private int heightNode(T value){

        AVLNode<T> aux = this.root;
        int result = value.compareTo(aux.getInfo());
        int count = 0;

        while (aux != null && result != 0) { 
            if(result<0)
                aux = aux.getLeft();
            
            else
                aux = aux.getRight();
            
            count++;
            result = value.compareTo(aux.getInfo());
        } 
        
        if(aux != null)
            return count;

        else
            return -1;
    }

    public void insert(T value){
        if(this.isEmpty()){
            this.root = new AVLNode<T>(value);
        }
        else{
            this.root = backInsert(this.root, value);
            this.status = false;
        }
    }
    private AVLNode<T> backInsert(AVLNode<T> r, T value) {
        Stack<AVLNode<T>> stack = new Stack<>();
        int result = 0;
    
        while (r != null) {
            result = value.compareTo(r.getInfo());
            stack.push(r);
    
            if (result < 0)
                r = r.getLeft();
            else
                r = r.getRight();
        }
    
        AVLNode<T> parent = stack.peek();
        AVLNode<T> newNode = new AVLNode<>(value);
    
        if (result < 0)
            parent.setLeft(newNode);
        else
            parent.setRight(newNode);
    
        this.status = true;
    
        while (!stack.isEmpty() && this.status) {
            r = stack.pop();
            result = value.compareTo(r.getInfo());
    
            if (result < 0) {
                switch (r.getBalFact()) {
                    case -1:
                        r.setBalFact(0);
                        this.status = false;
                        break;
                    case 0:
                        r.setBalFact(-1);
                        break;
                    case 1:
                        r = this.rotateRight(r);
                        this.status = false;
                        break;
                }
            } else {
                switch (r.getBalFact()) {
                    case 1:
                        r.setBalFact(0);
                        this.status = false;
                        break;
                    case 0:
                        r.setBalFact(1);
                        break;
                    case -1:
                        r = this.rotateLeft(r);
                        this.status = false;
                        break;
                }
            }
    
            if (!stack.isEmpty()) {
                AVLNode<T> parentNode = stack.peek();
                if (parentNode.getLeft() == r || result < 0)
                    parentNode.setLeft(r);
                else
                    parentNode.setRight(r);
            }
        }
    
        return r;
    }
    
    
}
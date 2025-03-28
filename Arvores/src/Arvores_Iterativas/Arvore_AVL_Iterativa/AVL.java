import java.util.Stack;

class AVL <T extends Comparable<T>> {

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
            this.root = new AVLNode<>(value);
        }
        else{
            this.root = backInsert(this.root, value);
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
        
        if (result < 0)
            parent.setLeft(new AVLNode<>(value));
        else
            parent.setRight(new AVLNode<>(value));
    
    
        while (!stack.isEmpty()) {
            r = stack.pop();
            result = value.compareTo(r.getInfo());
    
            
            if (result < 0) 
                r.setBalFact(r.getBalFact() - 1);
            else 
                r.setBalFact(r.getBalFact() + 1);
    
            
            if (Math.abs(r.getBalFact()) > 1) {
                r = this.rebalance(r); 
                if (!stack.isEmpty()) {
                    parent = stack.peek();
                    if (value.compareTo(parent.getInfo()) < 0) {
                        parent.setLeft(r);
                    } else {
                        parent.setRight(r);
                    }
                }
                break;
            } else if (r.getBalFact() == 0) {
                break;
            }
        }
    
        if(stack.isEmpty())
            return r;
        else
            return this.root;
    }

    private AVLNode<T> rebalance (AVLNode<T> node){
        if(node.getBalFact()==2)
            return rotateRight(node);
        
        else
            return rotateLeft(node);
    }

    private AVLNode rotateRight(AVLNode<T> A){
        AVLNode<T> B, C;
        B = A.getLeft();

        if(B.getBalFact() >= 0){

            A.setLeft(B.getRight());
            B.setRight(A);
            A.setBalFact(0);
            A=B;

        }
        else{
            C=B.getRight();
            B.setRight(C.getLeft());
            C.setLeft(B);
            A.setLeft(C.getRight());
            C.setRight(A);
            
            if(C.getBalFact()==-1)
                A.setBalFact(1);
            else
                A.setBalFact(0);

            if(C.getBalFact()==1)
                B.setBalFact(-1);
            else
                B.setBalFact(0);
        
        A=C;
        }

        A.setBalFact(0);

        return A;
    }

    private AVLNode rotateLeft(AVLNode<T> A){
        return null;
    }

    
}
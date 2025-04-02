package Arvores_Recursivas.AVL_Recursiva;

import Listas.Queue.*;

public class AVLR<T extends Comparable<T>>{

    private AVLRNode<T> root;    
    private boolean status;      

    // Métodos de acesso
    public AVLRNode getRoot (){return this.root;}
    public void setRoot(AVLRNode<T> root) {this.root = root;}
    public boolean getStatus() {return this.status;}
    public void setStatus(boolean status) {this.status = status;}
    
    // Verifica se a árvore está vazia
    public boolean isEmpty(){return this.root == null;}

    // Rotação simples à direita para inserção
    private AVLRNode<T> rotateRightInsert(AVLRNode<T> a){
        AVLRNode<T> b,c;
        b = a.getLeft();

        // Caso 1: Rotação simples
        if(b.getBalFact()==-1){
            a.setLeft(b.getRight());
            b.setRight(a);
            a.setBalFact(0);
            a = b;
        }
        // Caso 2: Rotação dupla
        else{
            c = b.getRight();
            b.setRight(c.getLeft());
            c.setLeft(b);
            a.setLeft(c.getRight());
            c.setRight(a);
            
            // Ajusta fatores de balanceamento após rotação dupla
            if(c.getBalFact()==-1)
                a.setBalFact(1);
            else
                a.setBalFact(0);    

            if(c.getBalFact()==1)
                b.setBalFact(-1);
            else    
                b.setBalFact(0);
            
            a = c;
        }
        a.setBalFact(0);
        this.status = false; // Altura não mudou após balanceamento
        return a;
    }


    // Rotação à esquerda para inserção
    private AVLRNode<T> rotateLeftInsert(AVLRNode<T> a){
        AVLRNode<T> b,c;
        b = a.getRight();

        // Caso 1: Rotação simples
        if(b.getBalFact()==1){
            a.setRight(b.getLeft());
            b.setLeft(a);
            a.setBalFact(0);
            a = b;
        }
        // Caso 2: Rotação dupla
        else{
            c = b.getLeft();
            a.setRight(c.getLeft());
            c.setLeft(a);
            b.setLeft(c.getRight());
            c.setRight(b);
            
            // Ajusta fatores de balanceamento após rotação dupla
            if(c.getBalFact() ==1)
                a.setBalFact(-1);
            else
                a.setBalFact(0);

            if (c.getBalFact()==-1) 
                b.setBalFact(1);
             else 
                b.setBalFact(0);

            a = c;    
        }
        a.setBalFact(0);
        this.status = false; // Altura não mudou após balanceamento
        return a;
    }

    
    // Insere um novo valor na árvore
    public void insert(T value){
        if(this.isEmpty())
            this.root = new AVLRNode<T>(value); // Árvore vazia, cria nova raiz
        else{
            this.root = insertNode(this.root, value); // Insere recursivamente
            this.status = false;
        }
    }

    
    // Método recursivo para inserção de valor na árvore
    private AVLRNode<T> insertNode(AVLRNode<T> r, T value){
        if(r==null){
            r = new AVLRNode<T>(value);
            this.status = true; // Altura mudou
        }
        // Inserção na subárvore esquerda
        else if(value.compareTo(r.getInfo())<0){
            r.setLeft(insertNode(r.getLeft(), value));
            
            if(this.status){
                switch(r.getBalFact()){
                    case 1: 
                        r.setBalFact(0);
                        this.status = false; // Árvore balanceada
                        break;
                    case 0:
                        r.setBalFact(-1); // Altura aumentou à esquerda
                        break;
                    case -1:
                        r = this.rotateRightInsert(r); // Necessário balanceamento
                        break;
                }
            }
        }
        // Inserção na subárvore direita
        else{
            r.setRight(insertNode(r.getRight(), value));
            
            switch (r.getBalFact()) {
                case -1:
                    r.setBalFact(0);
                    this.status = false; // Árvore balanceada
                    break;
                case 0:
                    r.setBalFact(1); // Altura aumentou à direita
                    break;
                case 1:
                    r = rotateLeftInsert(r); // Necessário balanceamento
                    break;
            }
        }
        return r;
    }

    // Rotação à direita para remoção
    private AVLRNode<T> rotateRightRemove(AVLRNode<T> a) {
        AVLRNode<T> b, c;
        b = a.getLeft();
    
        // Rotação simples
        if (b.getBalFact() <= 0) {
            a.setLeft(b.getRight());
            b.setRight(a);
            if (b.getBalFact() == 0) {
                a.setBalFact(-1);
                b.setBalFact(1);
                this.status = false; // Altura não mudou
            } else {
                a.setBalFact(0);
                b.setBalFact(0);
            }
            a = b;
        } 
        // Rotação dupla
        else {
            c = b.getRight();
            b.setRight(c.getLeft());
            c.setLeft(b);
            a.setLeft(c.getRight());
            c.setRight(a);
            
            // Ajusta fatores de balanceamento
            if (c.getBalFact() == -1)
                a.setBalFact(1);
            else
                a.setBalFact(0);
            
            if (c.getBalFact() == 1)
                b.setBalFact(-1);
            else
                b.setBalFact(0);
            
            a = c;
        }
        a.setBalFact(0);
        return a;
    }
    
    // Rotação à esquerda para remoção
    private AVLRNode<T> rotateLeftRemove(AVLRNode<T> a) {
        AVLRNode<T> b, c;
        b = a.getRight();
    
        // Rotação simples
        if (b.getBalFact() >= 0) {
            a.setRight(b.getLeft());
            b.setLeft(a);
            if (b.getBalFact() == 0) {
                a.setBalFact(1);
                b.setBalFact(-1);
                this.status = false; // Altura não mudou
            } else {
                a.setBalFact(0);
                b.setBalFact(0);
            }
            a = b;
        } 
        // Rotação dupla
        else {
            c = b.getLeft();
            a.setRight(c.getLeft());
            c.setLeft(a);
            b.setLeft(c.getRight());
            c.setRight(b);
            
            // Ajusta fatores de balanceamento
            if (c.getBalFact() == 1)
                a.setBalFact(-1);
            else
                a.setBalFact(0);
            
            if (c.getBalFact() == -1)
                b.setBalFact(1);
            else
                b.setBalFact(0);
            
            a = c;
        }
        a.setBalFact(0);
        return a;
    }
    

    // Remove um valor da árvore
    public void remove(T value) {
        if (this.isEmpty()) {
            System.out.println("Árvore Vazia");
        } else {
            this.root = removeNode(this.root, value);
            this.status = false;
        }
    }
    

    // Método recursivo para remoção que returna o nó atualizado
    private AVLRNode<T> removeNode(AVLRNode<T> nodeRemoved, T value) {
        if (nodeRemoved == null) {
            this.status = false;
            return null;
        }
        
        int result = value.compareTo(nodeRemoved.getInfo());
        
        // Busca na subárvore esquerda
        if (result < 0) {
            nodeRemoved.setLeft(removeNode(nodeRemoved.getLeft(), value));
            if (this.status) {
                switch (nodeRemoved.getBalFact()) {
                    case -1:
                        nodeRemoved.setBalFact(0);
                        this.status = true; // Altura mudou
                        break;
                    case 0:
                        nodeRemoved.setBalFact(1);
                        this.status = false; // Altura não mudou
                        break;
                    case 1:
                        nodeRemoved = this.rotateLeftRemove(nodeRemoved);
                        this.status = false; // Balanceado
                        break;
                }
            }
        } 
        // Busca na subárvore direita
        else if (result > 0) {
            nodeRemoved.setRight(removeNode(nodeRemoved.getRight(), value));
            if (this.status) {
                switch (nodeRemoved.getBalFact()) {
                    case 1:
                        nodeRemoved.setBalFact(0);
                        this.status = true; // Altura mudou
                        break;
                    case 0:
                        nodeRemoved.setBalFact(-1);
                        this.status = false; // Altura não mudou
                        break;
                    case -1:
                        nodeRemoved = this.rotateRightRemove(nodeRemoved);
                        this.status = false; // Balanceado
                        break;
                }
            }
        } 
        // Nó encontrado
        else {
            // Caso 1: Nó folha
            if (nodeRemoved.getLeft() == null && nodeRemoved.getRight() == null) {
                nodeRemoved = null;
            } 
            // Caso 2: Nó com um filho
            else if (nodeRemoved.getLeft() == null) {
                nodeRemoved = nodeRemoved.getRight();
            } else if (nodeRemoved.getRight() == null) {
                nodeRemoved = nodeRemoved.getLeft();
            } 
            // Caso 3: Nó com dois filhos
            else {
                AVLRNode<T> parent = nodeRemoved;
                AVLRNode<T> child = parent.getLeft();
                
                // Encontra o maior nó da subárvore esquerda
                if (child.getRight() != null) {
                    while (child.getRight() != null) {
                        parent = child;
                        child = child.getRight();
                    }
                    parent.setRight(child.getLeft());
                } else {
                    parent.setLeft(child.getLeft());
                }
                nodeRemoved.setInfo(child.getInfo()); // Substitui o valor
            }
            this.status = true; // Altura mudou
        }
        return nodeRemoved;
    }
    
    
    // Realiza percurso em ordem (esquerda, raiz, direita)
    public void emOrdem(){
        if(this.isEmpty())
            System.out.println("Árvore vázia!");
        else
            this.passeioEmOrdem(this.root);
    }
    private void passeioEmOrdem(AVLRNode<T> aux){
        if (aux == null)
            System.out.println("null");
        else{
            passeioEmOrdem(aux.getLeft());
            System.out.println(aux.getInfo());
            passeioEmOrdem(aux.getRight());
        }
    }

    // Realiza passeio por nível
    public void porNivel(){
        if(this.isEmpty())
            System.out.println("Árvore vázia!");
        else
            this.passeioPorNivel(this.root);
    } 
    private void passeioPorNivel(AVLRNode<T> aux){
        Queue_<AVLRNode<T>> fila;
        fila = new Queue_<AVLRNode<T>>();
        fila.enQueue_(aux);

        while(!fila.isEmpty()){
            aux = fila.deQueue_();

            if(aux!=null){
                System.out.println(aux.getInfo());
                fila.enQueue_(aux.getLeft());
                fila.enQueue_(aux.getRight());
            }
            else
                System.out.println("null");
        }
    }
}
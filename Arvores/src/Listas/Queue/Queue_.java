package Listas.Queue;

public class Queue_<T> {
    private QueueNode_<T> inicio;
    private QueueNode_<T> fim;   
    
    public boolean isEmpty () {
        return this.inicio == null;
    }

    public boolean isFull () {
        return false;
    }

    public void enQueue_ (T info) {
        QueueNode_<T> novo = new QueueNode_<T>(info);
        if (this.isEmpty() == true) {
            this.inicio = novo;
            this.fim = novo;
        }
        else {
            this.fim.setProx(novo);
            this.fim = novo;
        }
    }
     
    public T deQueue_ () {
        QueueNode_<T> aux = this.inicio;
        this.inicio = this.inicio.getProx();
        return aux.getInfo();
    }

    public T head () {
        return this.inicio.getInfo();
    }
}
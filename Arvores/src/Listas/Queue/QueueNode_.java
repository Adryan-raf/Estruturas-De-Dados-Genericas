package Listas.Queue;

class QueueNode_<T> {
    private T info;
    private QueueNode_<T> prox;

    QueueNode_ (T info) {
        this.info = info;
    }
    
    T getInfo() {
        return info;
    }
    void setInfo(T info) {
        this.info = info;
    }
    QueueNode_<T> getProx() {
        return prox;
    }
    void setProx(QueueNode_<T> prox) {
        this.prox = prox;
    }
}
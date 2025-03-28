class ABBNode <T extends Comparable<T>> {
    private ABBNode<T> left;
    private T info;
    private ABBNode<T> right;

    public ABBNode<T> getLeft() {
        return this.left;
    }

    public void setLeft(ABBNode<T> left) {
        this.left = left;
    }

    public T getInfo() {
        return this.info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public ABBNode<T> getRight() {
        return this.right;
    }

    public void setRight(ABBNode<T> right) {
        this.right = right;
    }

    public ABBNode(T info){
        this.info = info;
    }
}

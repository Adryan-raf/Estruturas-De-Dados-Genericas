class AVLNode<T extends Comparable<T>> {

    private AVLNode<T> left;
    private T info;
    private AVLNode<T> right;
    private int balFact;

    public AVLNode(T info){
        this.info = info;
        this.balFact = 0;
    }

    public AVLNode<T> getLeft() {
        return this.left;
    }
    public void setLeft(AVLNode<T> left) {
        this.left = left;
    }

    public T getInfo() {
        return this.info;
    }
    public void setInfo(T info) {
        this.info = info;
    }

    public AVLNode<T> getRight() {
        return this.right;
    }
    public void setRight(AVLNode<T> right) {
        this.right = right;
    }

    public int getBalFact() {
        return this.balFact;
    }
    public void setBalFact(int balFact) {
        this.balFact = balFact;
    }

}

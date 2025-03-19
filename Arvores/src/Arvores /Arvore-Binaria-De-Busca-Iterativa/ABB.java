public class ABB <T extends Comparable<T>> {
    private ABBNode<T> root;

    public ABB(){
        this.root =null;
    }

    public ABBNode<T> getRoot() {
        return this.root;
    }

    public void setRoot(ABBNode<T> root) {
        this.root = root;
    }

    public boolean isEmpty(){
        return this.root == null;
    }

    public void insert(T valor){
        if(this.isEmpty())
            this.root = new ABBNode<T>(valor);
        
        else
            backInsert(this.root, valor);
        
    }
    private void backInsert(ABBNode<T> r, T valor){
        ABBNode<T> parent= null;
        ABBNode<T> child = r;
                
        while (child != null){

            int result= valor.compareTo(child.getInfo());

            if (result == 0){
                System.out.println("Elemento repetido!"); 
                return;
            }
               
            parent = child;

            if(result<0)
                child = child.getLeft();
            else
                child = child.getRight();
    
        }
        
        child = new ABBNode<T>(valor);
        if(valor.compareTo(parent.getInfo()) < 0)
            parent.setLeft(child);
            
        else
            parent.setRight(child);
    }
            
    public void search(T valor){      
        if(this.isEmpty()){
            System.out.println("Árvore Vázia!");
        }
        else{
            ABBNode<T> aux = backSearch(this.root, valor);
            if(aux!= null)
                System.out.println(aux);

            else
                System.out.println("Elemento não encontrado!");
        }
    }
    private ABBNode<T> backSearch(ABBNode<T> r, T valor){
        while (r!=null) {
            int result = valor.compareTo(r.getInfo());
            
            if(result == 0)
                return r;

            else if(result <0) 
                r = r.getLeft();

            else
                r = r.getRight();                    
            
        }

        return null;
    }
       
    public void remove(T valor){
        if(this.isEmpty()){
            System.out.println("Árvore Vázia!");
        }
        else{
            this.setRoot(backRemove(this.root, valor));
        }
    }
    private ABBNode<T> backRemove(ABBNode<T> r, T valor){
    ABBNode<T> parent;
    int result;
    do{ 
        result = valor.compareTo(r.getInfo());
        parent = r;

        if(result<0)
            r = r.getLeft();

        else if(result>0)    
            r = r.getRight();            
    
        }while (r!=null && result != 0);
       
        if(result==0){
            if(r.getLeft()==null && r.getRight()==null){
                if(parent.getInfo().compareTo(r.getInfo())<0)
                    parent.setLeft(null);

                else 
                    parent.setRight(null);

            }
            else if(r.getLeft()==null){
                if(parent.getInfo().compareTo(r.getInfo())<0)
                    parent.setLeft(r.getRight());

                else 
                    parent.setRight(r.getRight());
            }
            else if(r.getRight()==null){
                if(parent.getInfo().compareTo(r.getInfo())<0)
                    parent.setLeft(r.getLeft());

                else 
                    parent.setRight(r.getLeft());
            }else{
                ABBNode<T> p;
                ABBNode<T> c; 
            }

        }
        
        return null;
    }    
}

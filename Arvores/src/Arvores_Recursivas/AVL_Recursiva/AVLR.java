class AVLR<T extends Comparable<T>>{

    private AVLRNode<T> root;
    private boolean status;

    public AVLRNode getRoot (){return this.root;}
    public void setRoot(AVLRNode<T> root) {this.root = root;}

    public boolean getStatus() {return this.status;}
    public void setStatus(boolean status) {this.status = status;}
    
    public boolean isEmpty(){return this.root == null;}

    private AVLRNode<T> rotateRightInsert(AVLRNode<T> a){
        AVLRNode<T> b,c;
        b = a.getLeft();

        if(b.getBalFact()==-1){
            a.setLeft(b.getRight());
            b.setRight(a);
            a.setBalFact(0);
            a = b;
        }
        else{
            c = b.getRight();
            b.setRight(c.getLeft());
            c.setLeft(b);
            a.setLeft(c.getRight());
            c.setRight(a);
            if(a.getBalFact()==-1)
                a.setBalFact(1);
            else
                a.setBalFact(0);    

            if(b.getBalFact()==1)
                b.setBalFact(-1);
            else    
                b.setBalFact(0);
            
            a = c;
        }
        a.setBalFact(0);
        this.status = false;
        return a;
    }
    private AVLRNode<T> rotateLeftInsert(AVLRNode<T> a){
        AVLRNode<T> b,c;
        b = a.getRight();

        if(b.getBalFact()==1){
            a.setRight(b.getLeft());
            b.setLeft(a);
            a.setBalFact(0);
            b = a;
        }
        else{
            c = b.getLeft();
            a.setRight(c.getLeft());
            c.setLeft(a);
            b.setLeft(c.getRight());
            c.setRight(b);
            if(c.getBalFact() ==1)
                a.setBalFact(-1);
            else
                a.setBalFact(0);

            if (c.getBalFact()==-1) 
                b.setBalFact(1);
             else 
                b.setBalFact(0);

            c = a;    

        }
        a.setBalFact(0);
        this.status = false; 
        return a ;
    }

    public void insert(T value){
        if(this.isEmpty())
            this.root = new AVLRNode<T>(value);
        
        else{
            this.root = insertNode(this.root, value); 
            this.status = false;
        }
    }
    private AVLRNode<T> insertNode(AVLRNode<T> r, T value){
        if(r==null){
            r = new AVLRNode<T>(value);
            this.status = true;
        }
        
        else if(value.compareTo(r.getInfo())<0){

            r.setLeft(insertNode(r.getLeft(), value));
            
            if(this.status){
                switch(r.getBalFact()){
                    case 1: 
                        r.setBalFact(0);
                        this.status = false;
                        break;
                    case 0:
                        r.setBalFact(-1);
                        break;
                    case -1:
                        r = this.rotateRightInsert(r);
                        break;
                }
            }
        }
        else{

            r.setRight(insertNode(r.getRight(), value));
            
            switch (r.getBalFact()) {
                case -1:
                    r.setBalFact(0);
                    this.status = false;
                    break;
                case 0:
                    r.setBalFact(1);
                    break;
                case 1:
                    r = rotateLeftInsert(r);
                    break;
            }
        }
        return r;
    }

    private AVLRNode<T> rotateRightRemove(AVLRNode<T> a){
        AVLRNode<T> b,c;
        b = a.getLeft();

        if(b.getBalFact()<=0){
            a.setLeft(b.getRight());
            b.setRight(a);
            a.setBalFact(0);
            a = b;
        }
        else{
            c = b.getRight();
            b.setRight(c.getLeft());
            c.setLeft(b);
            a.setLeft(c.getRight());
            c.setRight(a);
            if(a.getBalFact()==-1)
                a.setBalFact(1);
            else
                a.setBalFact(0);    

            if(b.getBalFact()==1)
                b.setBalFact(-1);
            else    
                b.setBalFact(0);
            
            a = c;
        }
        a.setBalFact(0);
        this.status = false;
        return a;
    }
    private AVLRNode<T> rotateLeftRemove(AVLRNode<T> a){
        AVLRNode<T> b,c;
        b = a.getRight();

        if(b.getBalFact() >= 0){
            a.setRight(b.getLeft());
            b.setLeft(a);
            a.setBalFact(0);
            b = a;
        }
        else{
            c = b.getLeft();
            a.setRight(c.getLeft());
            c.setLeft(a);
            b.setLeft(c.getRight());
            c.setRight(b);
            if(c.getBalFact() ==1)
                a.setBalFact(-1);
            else
                a.setBalFact(0);

            if (c.getBalFact()==-1) 
                b.setBalFact(1);
             else 
                b.setBalFact(0);

            c = a;    

        }
        a.setBalFact(0);
        this.status = false; 
        return a ;
    }


    public void remove(T value){
        if(this.isEmpty())
            System.out.println("Árvore Vázia");
        else{
            this.root = removeNode(this.root, value);
            this.status = false;
        }

    }
    private AVLRNode<T> removeNode(AVLRNode<T> nodeRemoved, T value){
        
        int result = value.compareTo(nodeRemoved.getInfo());
        
        if(result<0){
            nodeRemoved.setLeft(removeNode(nodeRemoved, value));
            switch(nodeRemoved.getBalFact()){
                case -1:
                    nodeRemoved.setBalFact(0);
                    break;
                case 0:
                    nodeRemoved.setBalFact(1);
                    break;
                case 1:
                    nodeRemoved = this.rotateLeftRemove(nodeRemoved);
                    break;
            }
            

        }else if(result>0){
            nodeRemoved.setLeft(removeNode(nodeRemoved, value));
            switch(nodeRemoved.getBalFact()){
                case 1: 
                    nodeRemoved.setBalFact(0);
                    break;
                case 0:
                    nodeRemoved.setBalFact(-1);
                    break;
                case -1:
                    nodeRemoved = this.rotateRightRemove(nodeRemoved);
                    break;
            }
        }else{
            
            if(nodeRemoved.getLeft() == null && nodeRemoved.getRight() == null){
                nodeRemoved = null;
            }
            else if(nodeRemoved.getLeft()==null){
                nodeRemoved = nodeRemoved.getRight();

            }
            else if(nodeRemoved.getRight()==null){
                nodeRemoved = nodeRemoved.getLeft();
            }
            else{
                AVLRNode<T> parent = nodeRemoved, child = parent.getLeft();
                
                if(child.getRight()!=null){
                    while(child!= null){
                        parent = child; 
                        child = child.getRight();
                    }
                    parent.setRight(child.getLeft());
                }
                else{
                    parent.setLeft(child.getLeft());
                }
                
                nodeRemoved = parent;
                
            }
            this.status = true;
        }

        return nodeRemoved;
    }


}
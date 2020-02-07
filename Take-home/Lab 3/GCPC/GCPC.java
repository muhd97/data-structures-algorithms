import java.util.*;
import java.io.*;

public class GCPC{
    public static void main(String[] args) throws Exception {

        AVLTree avl = new AVLTree();
        Kattio k = new Kattio (System.in, System.out);
        int noOfTeams = k.getInt();
        int noOfEvents = k.getInt();
        Team[] teams = new Team[noOfTeams +1]; 
        teams[0] = null; //so that the index is same as the teamNo

        for(int i=1; i<=noOfTeams;i++){
            avl.insert(new Team (i,0,0)); 
            teams[i]= (new Team(i,0,0)); //set up the avl and array with teams solving 0 problems and having 0 penalties
        }

        for(int j=1; j<= noOfEvents; j++){
            int t = k.getInt();
            int p = k.getInt();

            AVLVertex tempT = avl.search(teams[t]);
            int e =  tempT.value.problemsSolved;
            int tempP = tempT.value.penaltiesIncurred;
            e++;
            tempP += p;
            avl.remove(tempT.value);
            avl.insert(new Team(t, e, tempP));
            teams[t] =(new Team(t,e,tempP));
            int r = avl.rank(teams[1]);
            k.println(noOfTeams -r +1);
        }
        k.flush();
    }
}

class Team implements Comparable<Team>{
    public int teamNo; 
    public  int problemsSolved; 
    public int penaltiesIncurred; 

    public Team(int teamNo, int problemsSolved, int penaltiesIncurred) {
        this.teamNo = teamNo;
        this.problemsSolved = problemsSolved ;
        this.penaltiesIncurred = penaltiesIncurred;
    }

    @Override
        public int compareTo(Team other) {
            if (this.problemsSolved != other.problemsSolved) return this.problemsSolved - other.problemsSolved;
            if (this.penaltiesIncurred != other.penaltiesIncurred) return other.penaltiesIncurred - this.penaltiesIncurred;
            return other.teamNo - this.teamNo;
        }

    @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            Team t = (Team) o;

            if (teamNo != 0 ? !(teamNo == (t.teamNo)) : t.teamNo != 0) return false;

            return true;
        }
}

class AVLVertex{
    AVLVertex left;
    AVLVertex right;
    AVLVertex parent;
    Team value; 
    int size;
    int height;
    int balanceFactor;

    public AVLVertex(Team value){
        this.value = value;
        this.size = 1;
        this.height = 0;
        this.balanceFactor = 0;
        left = null;
        right = null;
        parent = null;
    }
}

class AVLTree{
    AVLVertex root;

    public AVLTree(){
        root = null;
    }

    public void insert(Team element){
        AVLVertex newAVLVertex = new AVLVertex(element);
        insert(root, newAVLVertex);
    }

    private void insert(AVLVertex start, AVLVertex element){
        if(root == null) {
            root = element;
        }else {
            while(true) {
                boolean insertLeft = start.value.compareTo(element.value) > 0;
                AVLVertex parent = start;
                if(insertLeft){
                    start = start.left;
                }else{
                start = start.right;
                }
                if (start == null) {
                    if (insertLeft)
                        parent.left = element;
                    else parent.right = element;
                    element.parent = parent;
                    AVLVertex temp = parent;
                    while(temp!= null){
                        temp.size = temp.size + 1;
                        temp = temp.parent;
                    }
                    balanceTree(parent);
                    break;
                }
            }
        }
    }

    public AVLVertex search(Team item){
        return search(root, item);
    }

    private AVLVertex search(AVLVertex start, Team item){
        if(start.value.equals(item)) {
            return start;
        }else if(start.value.compareTo(item) < 0){
            if(start.right==null)
                return null;
            else return search(start.right, item);
            
        }else {
            if(start.left==null)
                return null;
            else return search(start.left, item);
        }
    }

    public void remove(Team element){
        if(root==null) return;
        AVLVertex nodeToRemove = search(root, element);
        remove(nodeToRemove);
    }

    public void remove(AVLVertex nodeToRemove){
        if(nodeToRemove == null) return;
        if(nodeToRemove.right == null && nodeToRemove.left == null){
            if(nodeToRemove.parent != null){
                AVLVertex parent = nodeToRemove.parent;
                if(parent.left == nodeToRemove)
                    parent.left = null;
                else parent.right = null;
                balanceTree(parent);
            }
            else{
                root = null;
            }
            return;
        }
        if(nodeToRemove.left==null){
            AVLVertex child = nodeToRemove.right;
            while (child.left!=null) child = child.left;
            nodeToRemove.value = child.value;
            remove(child);
        }else{
            AVLVertex child = nodeToRemove.left;
            while (child.right!=null) 
                child = child.right;
            nodeToRemove.value = child.value;
            remove(child);
        }
    }
    
    private boolean isLeftChildOfParent(AVLVertex child){
        if(child.parent == null)
            return false;
        else return child.parent.left==child;
    }

    private boolean isRightChildOfParent(AVLVertex child){
        if(child.parent == null)
            return false;
        else return child.parent.right==child;
    }

    public AVLVertex successor(AVLVertex node){
        if (node.right == null){
            if(node.parent==null) return null;
            else if(isLeftChildOfParent(node)) {
                return node.parent;      
            }
            else return successor(node.parent);
        }
        else{
            return findMin(node.right);
        }
    }

    public AVLVertex predecessor(AVLVertex node){
        if (node.left != null){
            if(node.parent==null) return null;
            else if(isRightChildOfParent(node)) {
                return node.parent;      
            }
            else return predecessor(node.parent);
        } 
        else {
          return findMax(node.left);   
        }
    }

    public AVLVertex findMin(){
        return findMin(root);
    }

    private AVLVertex findMin(AVLVertex start) {
        if(start.left!=null) return findMin(start.left);
        else return start;
    }

    public AVLVertex findMax(){
        return findMax(root);
    }

    private AVLVertex findMax(AVLVertex start){
        if(start.right!=null) return findMax(start.right);
        else return start;
    }


    public int rank(Team element){
        if (!(element.equals(null))) return rank(element, root);
        return 0;
    }

    private int rank(Team element, AVLVertex start){
        int rank = 1;
        while(start!=null){
            if(element.compareTo(start.value) > 0){
                if(start.left==null) rank += 1;
                else rank += 1 + start.left.size;
                start = start.right;
            }
            else if(element.compareTo(start.value) < 0) {
                start = start.left;;
            }else {
                if(start.left!=null) return rank+start.left.size;
                else return rank;
            }
        }
        return rank;
    }

    public boolean isEmpty(){
        return root==null;
    }

    private int height(AVLVertex node){
        if(node!=null) return node.height;
        else return -1;
    }

    public void setBalance(AVLVertex... nodes){
        for (AVLVertex node : nodes){
            setHeight(node);
            setSize(node);
            node.balanceFactor = height(node.left) - height(node.right);
        }
    }

    private void setHeight(AVLVertex node){
        if(node != null)
            node.height = Math.max(height(node.left), height(node.right)) +1;
    }

    private void setSize(AVLVertex node){
        if(node != null)
            node.size = size(node.left) + size(node.right) + 1;
    }

    private int size(AVLVertex node){
        if(node!=null) return node.size;
        else return 0;
    }

    public void balanceTree(AVLVertex start){
        setBalance(start); 
        if(start.balanceFactor==-2){
            if(start.right.balanceFactor==1)
                start = rotateRightThenLeft(start);
            else if (start.right.balanceFactor<=0 && start.right.balanceFactor>=-1)
                start = rotateLeft(start);
        }else if(start.balanceFactor==2){
            if(start.left.balanceFactor==-1)
                start = rotateLeftThenRight(start);
            else if (start.left.balanceFactor<=1 && start.left.balanceFactor>=0)
                start = rotateRight(start);
        }if (start.parent != null)
        balanceTree(start.parent);
        else root = start;
    }

    private AVLVertex rotateRight(AVLVertex node){
        AVLVertex leftAVLVertex = node.left;
        leftAVLVertex.parent = node.parent;
        node.left = leftAVLVertex.right;
        if(node.left != null) node.left.parent = node;
        leftAVLVertex.right = node;
        node.parent = leftAVLVertex;
        if (leftAVLVertex.parent != null) {
            if (leftAVLVertex.parent.right != node)
                leftAVLVertex.parent.left = leftAVLVertex;
            else leftAVLVertex.parent.right = leftAVLVertex;
        }
        setBalance(node, leftAVLVertex);
        return leftAVLVertex;
    }

    private AVLVertex rotateLeft(AVLVertex node){
        AVLVertex rightAVLVertex = node.right;
        rightAVLVertex.parent = node.parent;
        node.right = rightAVLVertex.left;
        if(node.right != null)
            node.right.parent = node;
        rightAVLVertex.left = node;
        node.parent = rightAVLVertex;
        if(rightAVLVertex.parent != null){
            if(rightAVLVertex.parent.right != node)
                rightAVLVertex.parent.left = rightAVLVertex;
            else rightAVLVertex.parent.right = rightAVLVertex;
        }
        setBalance(node, rightAVLVertex);
        return rightAVLVertex;
    }

    private AVLVertex rotateLeftThenRight(AVLVertex node){
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

    private AVLVertex rotateRightThenLeft(AVLVertex node) {
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }

}

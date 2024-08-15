import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnionFind {
    // TODO: Instance variables

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    int[] disjointSet;
    public UnionFind(int N) {
        // set up an array of length N with -1 as the value
        // TODO: YOUR CODE HERE
        this.disjointSet = new int[N];
        for(int i = 0; i < disjointSet.length; i++){
            this.disjointSet[i] = -1;
        }

    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) throws  IllegalArgumentException {
        // TODO: YOUR CODE HERE
        int parent = parent(v);
        if(parent < 0){
            return parent*-1;
        }
        return sizeOf(parent);
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) throws  IllegalArgumentException {
        // TODO: YOUR CODE HERE
        if(v < 0 || v > this.disjointSet.length - 1){
            throw new  IllegalArgumentException("item is not in disjoint set");
        }
        // remember v is the index the index is the item
        return this.disjointSet[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) throws IllegalArgumentException{
        // TODO: YOUR CODE HERE
        // the root has a negative parent
        List<Integer> cache = new ArrayList<>();
        int parentNode = parent(v);
        while (parentNode >= 0){
            cache.add(v);
            v = parentNode;
            parentNode = parent(v);
        }
//        for(int c: cache){
//            if(c != v){
//                this.disjointSet[c] = v;
//            }
//
//        }
        return v;


    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if(!connected(v1, v2)){
            if(sizeOf(v1) <= sizeOf(v2)){
                // connect v1 to v2
                int root1 = find(v1);
                int root2 = find(v2);
                this.disjointSet[root1] = root2;
                this.disjointSet[root2] = -(sizeOf(root1) + sizeOf(root2));

            } else {
                int root1 = find(v1);
                int root2 = find(v2);
                this.disjointSet[root2] = root1;
                this.disjointSet[root1] = -(sizeOf(root1) + sizeOf(root2));
                // connect v2 to v1
            }
        }
        System.out.println("set");
        System.out.println(Arrays.toString(disjointSet));
    }

}

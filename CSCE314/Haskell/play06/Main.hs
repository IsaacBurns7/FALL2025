module Main (main) where
import Tree (Tree (Leaf, Branch), fringe)
import qualified Fringe ( fringe )
import TreeADT (Tree, leaf, branch, cell, left, right, isLeaf) 
a = isLeaf (branch 3 (leaf 1) (leaf 2))
b = isLeaf (right(branch 3 (leaf 1) (leaf 2)))
c = cell (branch 3 (leaf 1) (leaf 2))
main = do 
    print (fringe (Branch (Leaf 1) (Branch (Leaf 3) (Leaf 2))))
    print (Fringe.fringe (Branch (Leaf 'a') (Branch (Leaf 'c') (Leaf 'b'))))
    print a
    print b
    print c

module TreeADT (Tree, leaf, branch, cell, left, right, isLeaf) where
data Tree a = Leaf a | Branch a (Tree a) (Tree a)
leaf = Leaf
branch = Branch
cell (Leaf a) = a
cell (Branch a _ _) = a
left (Branch _ l _) = l 
right (Branch _ _ r) = r
isLeaf (Leaf _) = True 
isLeaf _ = False
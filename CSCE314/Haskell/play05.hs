data Tree a = Tnil | Node a (Tree a) (Tree a)

treeFold f g Tnil = f
treeFold f g (Node x l r) = 
    g x (treeFold f g l) (treeFold f g r)

tt = 
    Node 1 
        (Node 2 Tnil Tnil)
        (Node 3 Tnil 
            (Node 4 Tnil Tnil))

-- (Node 3 Tnil (Node 4 Tnil Tnil))

data Nat = Zero | Succ Nat

add Zero n = n
add (Succ m) n = Succ (add m n)

mult m Zero = Zero
mult m (Succ n) = add m (mult m n)
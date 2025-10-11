import Prelude hiding (Functor, fmap)
class Functor f where
    fmap:: (a->b) -> f a -> f b

instance Main.Functor [] where
    fmap = map

instance Main.Functor Maybe where 
    fmap _ Nothing = Nothing
    fmap g (Just x) = Just (g x)

data Tree a = Leaf a | Node (Tree a) (Tree a)
    deriving Show
instance Functor Tree where
    fmap g (Leaf x) = Leaf (g x)
    fmap g (Node l r) = Node (fmap g l) (fmap g r)  
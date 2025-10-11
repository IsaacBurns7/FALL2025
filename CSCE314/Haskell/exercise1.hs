n = a `div` length xs
    where
        a = 10
        xs = [1,2,3,4,5]
f [] = []
f (x:xs) = f ys ++ [x] ++ f zs
    where 
        ys = [a | a <- xs, a <= x]
        zs = [b | b <- xs, b > x]
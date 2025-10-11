quadruple x = double (double x)
factorial n = product [1..n]
average ns = sum ns `div` length ns
n = a `div` length xs
    where 
        {a = 10; xs = [1,2,3,4,5]}
last2 ns = drop (length ns - 1) ns
last3 xs = xs !! (length xs - 1)
last4 [x] = x
last4 (_:xs) = last4 xs
init2 ns = take (length ns - 1) ns
init3 ns = reverse(tail (reverse ns))
bools = [False, True]
nums = [[1 :: Int]]
add (x :: Int) y z = x+y+z
copy a = (a,a)
apply a b = a b
second xs = head (tail xs)
swap (x,y) = (y,x)
pair x y = (x,y)
double x = x*2
palindrome xs = reverse xs == xs
twice f x = f (f x)\
otherwise = True
odds :: Int -> [Int]
odds n  | n >= 0 = map (\x -> x*2 + 1) [0..n-1]
        | otherwise = map (\x -> x*2 - 1) [0.. abs n-1]

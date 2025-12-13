take2 m ys = case (m, ys) of 
    (n, _) | n <= 0 -> []
    (_, [])         -> []
    (n, x:xs)       -> x : take2 (m-1) xs

x = take2 2 [1,2,3]

reserved s = 
    let keywords = words "if then else for while"
        relops = words "== != < > <= >="
        elemInAny w [] = False 
        elemInAny w (l:ls) = w `elem` l || elemInAny w ls 
    in elemInAny s [keywords, relops]

x1 = reserved "abc"
x2 = reserved "if"

main = do 
    putStrLn (show x)
    putStrLn (show x1)
    putStrLn (show x2)
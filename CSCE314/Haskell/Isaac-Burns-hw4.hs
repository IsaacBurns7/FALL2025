

-- CSCE 314 [Sections 200, 502] Programming Languages Fall 2025
-- Homework Assignment 4 (Total 100 points)
-- Due on Friday, 10/10/2025

-- Problem 1 (5 points)
-- Student Name: Isaac Burns
-- UIN: 935007106
-- (ACKNOWLEDGE ANY HELP RECEIVED HERE)
-- On my honor, as an Aggie, I have neither given nor received any unauthorized
-- aid on any portion of the academic work included in this assignment.

module Main where

import Test.HUnit
import System.Exit

-- *** Read Chapters 8 and 16 ***

data Tree a b = Leaf a | Branch b (Tree a b) (Tree a b)

---- Tree objects to be used to test your functions in Problems 2 and 3
-- Use tree1 to show the step-by-step of your function in Problem 3.2
tree1 :: Tree Int String
tree1 = Branch "*" 
            (Branch "+"
               (Branch "*" (Leaf 6) (Leaf 7))
               (Branch "+" (Leaf 9) (Leaf 2)))
            (Branch "*"
               (Branch "+"
                  (Branch "*" (Leaf 8) (Leaf 3))
                  (Leaf 9))
               (Branch "+" (Leaf 5) (Leaf 4)))

-- Another example Tree object
tree2 :: Tree Int String 
tree2 = Branch "+"  
            (Leaf 21)
            (Branch "*" (Leaf 12) (Leaf 7))

-- Yet another Tree object
tree3 :: Tree Int String
tree3 = Branch "+" 
            (Branch "*" 
               (Leaf 2)
               (Leaf 5))
            (Branch "+"
               (Branch "*" (Leaf 4) (Leaf 1))
               (Leaf 3))

-- Yet another...
tree4 :: Tree Int String
tree4 = Branch "B" 
            (Branch "C" 
               (Leaf 3) 
               (Leaf 2)) 
            (Leaf 4) 
---------------

-- Problem 2 (15 points)
instance (Show a, Show b) => Show (Tree a b) where
   show t = showTree 0 t
      where 
         showTree indent (Leaf x) = 
            replicate indent '\t' ++ show x ++ "\n"
         showTree indent (Branch y left right) = 
            replicate indent '\t' ++ show y ++ "\n"
            ++ showTree (indent + 1) left
            ++ showTree (indent + 1) right


-- Problem 3 (10 + 10 = 20 points)
---- Problem 3.1 (5 + 5 = 10 points)

inorder   :: (a -> c) -> (b -> c) -> Tree a b -> [c]  -- 5 points
inorder f g (Leaf x) = [f x]
inorder f g (Branch y left right) = inorder f g left ++ g y : inorder f g right


postorder  :: (a -> c) -> (b -> c) -> Tree a b -> [c]  -- 5 points
postorder f g (Leaf x) = [f x]
postorder f g (Branch y left right) = postorder f g left ++ postorder f g right ++ [g y]


---- Problem 3.2 (10 points)
{-- Explain the step-by-step when you evaluate the expression (given in hw4.pdf)
    Your answer must be in detail step-by-step using your definition for
    the function.
   inorder always evaluates left, then center, then right. 
   f is the function being applied to leaf, g is applied to branch.
   
   leftmost subtree: 
   inorder (Branch "*" (Leaf 6) (Leaf 7))
   = inorder (Leaf 6) ++ ("*" : inorder (Leaf 7))
   = [show 6] ++ ("*" : [show 7])
   = ["6"] ++ ("*" : ["7"])
   = ["6","*","7"]

   right sibling of leftmost subtree: 
   inorder (Branch "+" (Leaf 9) (Leaf 2))
   = inorder (Leaf 9) ++ ("+" : inorder (Leaf 2))
   = [show 9] ++ ("+" : [show 2])
   = ["9"] ++ ("+" : ["2"])
   = ["9","+","2"]

   combine to form
   left1 = ["6","*","7"] ++ ("+" : ["9","+","2"])
      = ["6","*","7","+","9","+","2"]

   now right subtree of root, start with its left side
   inorder (Branch "*" (Leaf 8) (Leaf 3))
   = inorder (Leaf 8) ++ ("*" : inorder (Leaf 3))
   = [show 8] ++ ("*" : [show 3])
   = ["8"] ++ ("*" : ["3"])
   = ["8","*","3"]

   the node's parent
   inorder (Branch "+" ~ (Leaf 8))
   = ["8","*","3"] ++ ("+" : ["*"])

   the right side
   inorder (Branch "+" (Leaf 5) (Leaf 4))
   = ["5"] ++ ("+" : ["4"])
   = ["5","+","4"]

   right1 = ["8","*","3","+","9"] ++ ("*" : ["5","+","4"])
       = ["8","*","3","+","9","*","5","+","4"]

   inorder tree1
   = left1 ++ ("*" : right1)
   = ["6","*","7","+","9","+","2"] ++ ("*" : ["8","*","3","+","9","*","5","+","4"])
   = ["6","*","7","+","9","+","2","*","8","*","3","+","9","*","5","+","4"]

--}


-- Problem 4 (5 + 10 = 15 points) Given the data type Tree1:
data Tree1 = Leaf1 Int | Node Tree1 Tree1  deriving (Show)

---- and the four Tree1 objects:
t1 = Node (Node (Node (Leaf1 2) (Leaf1 4)) (Leaf1 5)) (Leaf1 3)
t2 = Node (Node (Node (Leaf1 2) (Leaf1 4)) (Leaf1 5)) (Leaf1 3)
t3 = Node (Node (Node (Leaf1 4) (Leaf1 2)) (Leaf1 3)) (Leaf1 5)
t4 = Node (Leaf1 2) (Node (Leaf1 3) (Node (Leaf1 5) (Leaf1 4)))

---- Problem 4.1 (5 points) Define preorder
preorder :: Tree1 -> [Int]
preorder (Leaf1 x) = [x]
preorder (Node l r) = preorder l ++ preorder r

qsort1 :: Ord a => [a] -> [a]
qsort1 [] = []
qsort1 (x:xs) = qsort1 ys ++ [x] ++ qsort1 zs
  where
    ys = [a | a <- xs, a > x]
    zs = [b | b <- xs, b <= x]
---- Problem 4.2 (5 + 5 = 10 points) Make Tree1 an instance of Eq
instance Eq Tree1 where         -- Definition of (==) 5 points
   (==) t1 t2 = (qsort1 (preorder t1)) == (qsort1 (preorder t2))

{-- Justification of the functions you are using:  -- 5 points
   problem specified that two trees are equal if the values of their leaves are equal, regardless of structure.
   for this, we use preorder to return all the values of their leaves, and qsort1,
   from a previous assignment, to ensure that structure is not taken into account.
--}


-- Problem 5 (30 points) Chapter 8, Exercise 9 Modified
data Expr = Val Int | Add Expr Expr | Subt Expr Expr | Mult Expr Expr

type Cont = [Op]

data Op = EVALA Expr | ADD Int | EVALS Expr | SUBT Int | EVALM Expr | MULT Int

eval :: Expr -> Cont -> Int    
-- Give four definitions for eval.
-- First two definitions,
-- 1) for (Val n) and c as arguments and
-- 2) for (Add x y) and c as arguments
-- are already given in the text Section 8.7, but
-- you need to modify the second definition slightly
-- and give the third and fourth definitions for
-- (Subt x y) and (Mult x y)
eval (Val n) c = exec c n
eval (Add x y) c = eval x (EVALA y : c)
eval (Subt x y) c = eval x (EVALS y : c)
eval (Mult x y) c = eval x (EVALM y : c)


exec :: Cont -> Int -> Int
-- Give seven definitions for exec, one for an empty list and
-- one for each of the six constructors of the data type Op
-- Some of these are already given in the text Section 8.7.
exec [] n = n
exec (EVALA y : c) n = eval y (ADD n : c)
exec (ADD n : c) m = exec c (n+m)
exec (EVALS y : c) n = eval y (SUBT n : c)
exec (SUBT n : c) m = exec c (n-m)
exec (EVALM y : c) n = eval y (MULT n : c)
exec (MULT n : c) m = exec c (n*m)


value :: Expr -> Int
value e = eval e []

-- Expressions to test your eval and exec definitions
-- (2 + 3) + 4 = 9
e1 = (Val 3)    -- 3
e2 = (Add (Val 4) (Val 2))  -- 4 + 2 = 6
e3 = (Mult (Val 4) (Val 3))  -- 4 * 3 = 12
e4 = (Add (Subt (Val 5) (Val 3)) (Val 4))  -- (5 - 3) + 4 = 6
e5 = (Mult (Mult (Val 2) (Val 3)) (Val 4))  -- (2 * 3) * 4 = 24
e6 = (Mult (Add (Val 2) (Val 3)) (Val 4))  -- (2 + 3) * 4 = 20
e7 = (Mult (Subt (Val 3) (Val 1)) (Val 4))  -- (3 - 1) * 4 = 8
e8 = (Add (Mult (Val 2) (Val 3)) (Val 4))  -- (2 * 3) + 4 = 10
e9 = (Add (Subt (Val 9) (Val 6)) (Mult (Val 3) (Val 5))) -- (9 - 6) + (3 * 5) = 18

e10 = (Subt (Mult (Val 4) (Val 5)) (Add (Val 2) (Val 3))) -- (4 * 5) - (2 + 3) = 15

e11 = (Mult (Subt (Val 10) (Val 3)) (Add (Val 4) (Val 5))) -- (10 - 3) * (4 + 5) = 63
e12 = (Add (Mult (Add (Val 2) (Val 3)) (Mult (Val 4) (Val 5))) (Mult (Val 3) (Subt (Val 4) (Val 7)))) -- ((2 + 3) * (4 * 5)) + (3 * (4 - 7)) = 91


-- Problem 6 (15 points)
-- Show the step-by-step of the following application of value.
-- > value e10
{-- Your answer goes here. Your answer must be in detail step-by-step showing
    every function call according to your implementation.
   value e10 = eval e10 []
   eval e10 [] = eval (Subt (Mult (Val 4) (Val 5)) (Add (Val 2) (Val 3))) []
   eval (SUBT (Mult (Val 4) (Val 5)) (Add (Val 2) (Val 3))) [] 
      = eval (Mult (Val 4) (Val 5)) (EVALS (Add (Val 2) (Val 3)) : [])
   eval (MULT (Val 4) (Val 5)) [] = eval (Val 4) (EVALM (Val 5) : [])
   eval (Val 4) (EVALM (Val 5) : []) = exec (EVALM (Val 5) : []) 4
   exec (EVALM (Val 5) : []) 4 = eval (Val 5) (MULT 4 : [])
   eval (Val 5) (MULT 4 : []) = exec (MULT 4 : []) 5
      = exec [] (4 * 5) = exec [] 20 = 20
   
   eval 20 (EVALS (Add (Val 2) (Val 3)) : []) [] = exec (EVALS (Add (Val 2) (Val 3)): []) 20
   exec (EVALS (Add (Val 2) (Val 3)) : []) 20 = eval (Add (Val 2) (Val 3)) (SUBT 20 : [])
   eval (Add (Val 2) (Val 3)) (SUBT 20 : []) = eval (Val 2) (EVALA (Val 3) : (SUBT 20 : []))
   = exec (EVALA (Val 3) : SUBT 20 : []) 2
   = eval (Val 3) (ADD 2 : SUBT 20 : [])
   = exec (ADD 2 : SUBT 20 : []) 3
   = exec (SUBT 20 : []) 5
   = exec [] 15
   = 15
--}



myTestList = 
  TestList [
  
    "inorder 1"   ~: (concat (inorder show id tree1))  ~=? "6*7+9+2*8*3+9*5+4"
  , "postorder 1" ~: (concat (postorder show id tree1)) ~=? "67*92++83*9+54+**"
  , "inorder 2"   ~: (concat (inorder show id tree2))  ~=? "21+12*7"
  , "postorder 2" ~: (concat (postorder show id tree2))  ~=? "21127*+"
  , "inorder 3"   ~: (concat (inorder show id tree3))  ~=? "2*5+4*1+3"
  , "postorder 3" ~: (concat (postorder show id tree3))  ~=? "25*41*3++"

  , "preorder 1"  ~: preorder t1 ~=? [2,4,5,3]
  , "preorder 2"  ~: preorder t3 ~=? [4,2,3,5]
  , "preorder 3"  ~: preorder t4 ~=? [2,3,5,4]

  , "value 1"  ~: value e1 ~=? 3
  , "value 2"  ~: value e2 ~=? 6
  , "value 3"  ~: value e3 ~=? 12
  , "value 4"  ~: value e4 ~=? 6
  , "value 5"  ~: value e5 ~=? 24
  , "value 6"  ~: value e6 ~=? 20
  , "value 7"  ~: value e7 ~=? 8
  , "value 8"  ~: value e8 ~=? 10
  , "value 9"  ~: value e9 ~=? 18
  , "value 10"  ~: value e10 ~=? 15
  , "value 11" ~: value e11 ~=? 63
  , "value 12" ~: value e12 ~=? 91
  
    ]

main = do c <- runTestTT myTestList
          putStrLn $ show c
          let errs = errors c
              fails = failures c
          exitWith (codeGet errs fails)
          
codeGet errs fails
 | fails > 0       = ExitFailure 2
 | errs > 0        = ExitFailure 1
 | otherwise       = ExitSuccess



-- CSCE 314 [Sections 200, 502] Programming Languages, Fall 2025
-- Homework Assignment 1 (Total 105 points)
-- Due Monday, 9/8/2025

-- Problem 1 (5 points)
-- This is head comment (single line comment should be preceded by two dashes)
-- Student Name: Isaac Burns
-- UIN: 935007106
-- Resources: Course Textbook(Programming in Haskell), (ACKNOWLEDGE ANY HELP RECEIVED HERE)

-- On my honor, as an Aggie, I have neither given nor received any unauthorized
-- aid on any portion of the academic work included in this assignment.

module Main where

import Test.HUnit  -- if this line causes compile error, try the following
                   -- command at the terminal prompt > cabal install HUnit
import System.Exit


-- Example:
-- Write a recursive function mySum that sums all the numbers
-- in a list without using the prelude function sum.
mySum :: [Int] -> Int  -- type signature of mySum. mySum accepts a list of Ints
                       -- as its argument and returns an Int
mySum []     = 0  -- def.1
mySum (x:xs) = x + mySum xs -- def.2

{- Block comment over multiple lines is enclosed within {- and -}
Explanation:
The type of mySum tells us that mySum takes a list of Ints as an argument
and returns an Int that is the sum of all the Ints in the argument list.

Def.1 of mySum is the base case of the recursion, that is,
the argument list is empty, for which case the function value is 
defined as zero (summation identity).

Def.2 is when the argument list contains at least one element, 
namely x, in which case the function is defined as the sum of x 
and the result of the recursive call of mySum applied to the rest of 
the argument list, namely xs.
-}


-- Problem 2 (5+15 = 20 points)
qsort1 :: Ord a => [a] -> [a]
---- Question 2.1 (5 points)
qsort1 [] = []
qsort1 (x:xs) = qsort1 ys ++ [x] ++ qsort1 zs
  where
    ys = [a | a <- xs, a > x]
    zs = [b | b <- xs, b <= x]

---- Question 2.2 (15 points)
{- Write your answer for Question 2.2 within this block comment.
  [27, 6, 12, 9, 3]
  What will happen is qsort1 splits each input list into 3 lists, of which 2 have to be computed. 
  Initially, [27, 6, 12, 9, 3] turns into qsort1 ys ++ [27] ++ qsort2 zs. 
  qsort1 ys = [], qsort1 zs = [6,12,9,3]
  qsort1 zs now needs to be sorted, so it is resolved into [12, 9] ++ [6] ++ [3]
  [12, 9] resolves into [] ++ [12] ++ [9].
  added together again, you get [27] ++ [12] ++ [9] ++ [6] ++ 3.

  tree
  qsort1 [27, 6, 12, 9, 3] -> f [] ++ [27] ++ f [6, 12, 9, 3]
  qsort1 [6, 12, 9, 3] -> f [12, 9] ++ [6] ++ f [3]
  qsort1 [6] -> f [] ++ [6] ++ f []
  qsort1 [3] -> f [] ++ [3] ++ f []
  qsort1 [12, 9] -> f [] ++ [12] ++ f [9]
  qsort1 [9] -> f [] ++ [9] ++ f []
  
  Number of recursive calls depends on whether or not f [] calls are optimized away by compiler. 
  If not, there are 8 (f []) + 4 = 12 recursive calls. If f [] is optimized away, there are 4 recursive calls
-}


-- Problem 3 (5+5 = 10 points)
lucas :: Int -> Int
---- Question 3.1 (5 points)
lucas 0 = 2
lucas 1 = 1
lucas n = lucas (n-1) + lucas (n-2)

---- Question 3.2 (5 points)
{- Write your answer for Question 3.2 within this block comment.
  lucas 4 -> lucas (3) + lucas (2)
  lucas 3 -> lucas (2) + lucas (1)
  lucas 2 -> lucas (1) + lucas(0)
  lucas 2 -> lucas (1) + lucas(0)
  lucas 1 -> 1
  lucas 0 -> 2
  lucas 1 -> 1 
  lucas 0 -> 2
-}

-- Problem 4 (10 points)
factorial :: Int -> Int
factorial 0 = 1
factorial n = factorial(n-1) * n


-- Problem 5 (5+10+10=25 points)
---- Question 5.1 (5 points)
semifactorial :: Int -> Int
semifactorial 0 = 1
semifactorial 1 = 1
semifactorial n = n * semifactorial (n-2)

---- Question 5.2 (10 points)
{- Write your answer for Question 5.2 within this block comment.
  semifactorial 13 -> 13 * semifactorial (n-2)
  semifactorial 11 -> 11 * semifactorial (n-2)
  semifactorial 9 -> 9 * semifactorial (n-2)
  semifactorial 7 -> 7 * semifactorial (n-2)
  semifactorial 5 -> 5 * semifactorial (n-2)
  semifactorial 3 -> 3 * semifactorial (n-2)
  semifactorial 1 -> 1
  There are 6 recursive calls.
-}

---- Question 5.3 (10 points)
myfactorial :: Int -> Int
myfactorial 0 = 1
myfactorial n = n * semifactorial (n-1) * semifactorial (n-2)

{- Reasoning
  n! = n * (n-1) * (n-2) etc
  semifactorial n = n * (n-2) * (n-4) etc
  semifactorial n-1 = n-1 * n-3 * n-5 etc
  semifactorial n-2 = n-2 * n-4 etc
  n * semifactorail n-1 * semifactorial n-2 = n * n-1 * n-2 * n-3 * n-4 etc = n! 
-}



-- Problem 6 (10+15+10=35 points)
---- Question 6.1 (10 points)
term :: Num a => Int -> a -> a
term 1 x = x
term n x = x * term (n-1) x 
{- Reasoning
  Term takes in n and then x 
  term automatically returns x of type "Num" if n (integer exponent) is 1
  If n is not 1, it returns x * term (n-1) x, which at the end of the recursive stack returns x^n 
-}

---- Question 6.2 (15 points)
polynaive :: Num a => [a] -> Int -> a -> a
polynaive [] _ _ = 0
polynaive (a:as) n x = a * x^n + polynaive as (n-1) x 
-- if x^n is replaced by term n x it crashes the program,
-- because term (n < 1) x is not defined / supported.

---- Question 6.3 (10 points)
{- Write your answer for Question 6.3 within this block comment.
  polynaive [3, -2, -4, 5] 3 2 -> 3 * (2 ^ 3) + polynaive [-2, -4, 5] 2 2
  polynaive [-2, -4, 5] 2 2 -> -2 * (2 ^ 2) + polynaive [-4, 5] 1 2
  polynaive [-4, 5] 1 2 -> -4 * (2 ^ 1) + polynaive [5] 0 2
  polynaive [5] 0 2 -> 5 * (2 ^ 0) + polynaive [] -1 2
  polynaive [] -1 2 -> 0
-}



myTestList = 
  TestList [

      "qsort1 1" ~: qsort1 [3, 2, 5, 1, 8] ~=? [8,5,3,2,1]
    , "qsort1 2" ~: qsort1 "howdy" ~=? "ywohd"
    , "qsort1 3" ~: qsort1 "Oh, happy day!" ~=? "yypphhdaaO,!  "
    , "qsort1 4" ~: qsort1 [27,6,12,9,3] ~=? [27,12,9,6,3]

    , "lucas 1" ~: lucas 0 ~=? 2
    , "lucas 2" ~: lucas 1 ~=? 1    
    , "lucas 3" ~: lucas 4 ~=? 7
    , "lucas 4" ~: lucas 14 ~=? 843
    
    , "factorial 1" ~: factorial 0 ~=? 1
    , "factorial 2" ~: factorial 3 ~=? 6
    , "factorial 3" ~: factorial 5 ~=? 120
    , "factorial 4" ~: factorial 10 ~=? 3628800

    , "semifactorial 1" ~: semifactorial 3 ~=? 3
    , "semifactorial 2" ~: semifactorial 5 ~=? 15
    , "semifactorial 3" ~: semifactorial 10 ~=? 3840
    , "semifactorial 4" ~: semifactorial 13 ~=? 135135

    , "myfactorial 1" ~: myfactorial 0 ~=? 1
    , "myfactorial 2" ~: myfactorial 3 ~=? 6
    , "myfactorial 3" ~: myfactorial 5 ~=? 120
    , "myfactorial 4" ~: myfactorial 10 ~=? 3628800

    , "term 1" ~: term 3 2 ~=? 8
    , "term 2" ~: term 4 5 ~=? 625
    , "term 3" ~: term 10 3 ~=? 59049

    , "polynaive 1" ~: polynaive [2,-1,3,5] 3 2 ~=? 23
    , "polynaive 2" ~: polynaive [1,3,0,7,2] 4 5 ~=? 1037
    , "polynaive 3" ~: polynaive [(1/3),1,-2,0,2,1,(1/2)] 6 3 ~=? 345.5
    , "polynaive 4" ~: polynaive [3,-2,-4,5] 3 2 ~=? 13

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


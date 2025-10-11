
-- CSCE 314 [Sections 200, 502] Programming Languages, Fall 2025
-- Hyunyoung Lee
-- Homework Assignment 2 (Total 100 points)
-- Due at 11:59 p.m. Wednesday, 9/17/2025

-- Problem 1 (5 points)
-- This is head comment (single line comment should be preceded by two dashes)
-- Student Name: (Isaac Burns)
-- UIN: (935007106)
-- Resources: (ACKNOWLEDGE ANY HELP RECEIVED HERE)

-- On my honor, as an Aggie, I have neither given nor received any unauthorized
-- aid on any portion of the academic work included in this assignment.

module Main where

import Test.HUnit 

import System.Exit


type Set a = [a]
-- otherwise = True

-- Problem 2 (5+5=10 points)
---- Question 2.1 (5 points)
isElem :: Eq a => a -> [a] -> Bool
isElem _ [] = False
isElem target (x:xs) = target == x || isElem target xs
-- isElem target = any(==target)

---- Question 2.2 (5 points)
{- Write your answer for Question 2.2 within this block comment.
  Step 1: p == h || isElem p "appy"
  Step 2: p == a || isElem p "ppy"
  Step 3: p == p || isElem p "py"
  Stops evaluating because the first part is true. Returns true.
-}



-- Problem 3 (10+10=20 points)
---- Question 3.1 (10 points)
---- Using isElem (from Problem 2) in the definition is required
makeSet :: Eq a => [a] -> Set a
makeSet [] = []
makeSet (x:xs) | isElem x xs = makeSet xs
               | otherwise = x : makeSet xs

---- Question 3.2 (10 points)
{- Write your answer for Question 3.2 within this block comment.
  Step 1: | (isElem z "igzag") = True -> RETURN makeSet "igzag"
  Step 2: | (isElem i "gzag") = False -> RETURN i : makeSet "gzag"
  Step 3: | (isElem g "zag") = True -> RETURN makeSet "zag"
  Step 4: | (isElem z "ag") = False -> RETURN z : makeSet "ag"
  Step 5: | (isElem a "g") = False -> RETURN a : makeSet "g" 
  Step 6: | (isElem g []) = False -> RETURN g : makeSet [] 
  Step 7: return []

-}



-- Problem 4 (10+5=15 points)
---- Question 4.1 (10 points)
---- Using isElem (from Problem 2) in the definition is required
subset :: Eq a => Set a -> Set a -> Bool
subset a b = length [x | x <- a, isElem x b] == length a

---- Question 4.2 (5 points)
---- Using makeSet and subset in the definition is required
isSubset :: Eq a => [a] -> [a] -> Bool
isSubset a b = subset (makeSet a) (makeSet b)



-- Problem 5 (10 points)
-- Using subset (from Problem 4) in the definition is required
setEqual :: Eq a => Set a -> Set a -> Bool
setEqual a b = (subset a b) && (subset b a)



-- Problem 6 (15+10 = 25 points)
---- Question 6.1  (15 points)
---- Using direct recursion and list comprehenson is required
powerset :: Set a -> Set (Set a)
powerset [] = [[]]
powerset (x:xs) = [[x] ++ ys | ys <- powerset xs] ++ powerset xs

---- Question 6.2 (10 points)
{- Write your answer for Question 6.2 within this block comment.
  Step 1: powerset (5:[7]) = [[5] ++ ys | ys <- powerset [7]] ++ powerset [7]
  Step 2: powerset (7: []) = [[7] ++ ys | ys <- powerset []] ++ powerset []
  Step 3: powerset [] = [[]]
  Step 4: Plug in powerset [] -> powerset (7 : []) = [[7]] ++ [[]] -> [[], [7]]
  Step 5: Plug in powerset [7] -> powerset [5 : [7]] = [[5] ++ [7], 5 ++ []] ++ [[], [7]] -> [[5,7], [5], [7], []]
-}



-- Problem 7 (5+10 = 15 points)
---- Question 7.1  (5 points)
---- Using list comprehenson and the zip prelude function is required
scalarproduct :: [Int] -> [Int] -> Int
scalarproduct a b = sum [x * y | (x,y) <- (zip a b)]

---- Question 7.2 (10 points)
{- Write your answer for Question 7.2 within this block comment.
  scalarproduct [2..] [5,2,4,10] = sum [x * y | (x,y) <- (zip [2..] [5,2,4,10])]
  Because of lazy evaluation, [2..] will only get evaluated until it has enough elements for scalarproduct, which is 4 elements.
  [2..] becomes [2,3,4,5]
  scalarproduct [2,3,4,5] [5,2,4,10] = sum [x * y | (x,y) <- (zip [2,3,4,5] [5,2,4,10])]
  zip [2,3,4,5] [5,2,4,10] = [(2,5),(3,2),(4,4),(5,10)]
  [x * y | (x,y) <- [(2,5),(3,2),(4,4),(5,10)]] -> [10,6,16,50]
  sum [10,6,16,50] -> 82 
  scalarproduct [2..] [5,2,4,10] = 82
-}



myTestList = 
  TestList [
      "isElem 1" ~: (isElem 'h' "happy") ~=? True
    , "isElem 2" ~: (isElem 'o' "happy") ~=? False
    , "isElem 3" ~: (isElem 'p' "happy") ~=? True

    , "makeSet 1" ~: length (makeSet "mississippi") ~=? 4
    , "makeSet 2" ~: length (makeSet "chihuahua") ~=? 5
    , "makeSet 3" ~: length (makeSet "abba") ~=? 2
    , "makeSet 4" ~: length (makeSet "based") ~=? 5
    , "makeSet 5" ~: length (makeSet "zigzag") ~=? 4

    , "subset 1" ~: subset [] [1,2] ~=? True
    , "subset 2" ~: subset [1,2] [] ~=? False
    , "subset 3" ~: subset [2,3] [1,2] ~=? False
    , "subset 4" ~: subset [2,3] [3,1,2] ~=? True
    , "subset 5" ~: subset [2,3] [2,1,4] ~=? False

    , "isSubset 1" ~: isSubset "aha" "chihuahua" ~=? True
    , "isSubset 2" ~: isSubset [1,1] [] ~=? False
    , "isSubset 3" ~: isSubset "achoo" "chihuahua" ~=? False
    , "isSubset 4" ~: isSubset [1,2,2,3,2] [3,2,1,3,1,2] ~=? True
    , "isSubset 5" ~: isSubset [2,3] [2,1,2,1,4] ~=? False
    , "isSubset 6" ~: isSubset [2,3] [2,1,2,1,3,3,4] ~=? True

    , "setEqual 1" ~: setEqual "abc" "bca" ~=? True
    , "setEqual 2" ~: setEqual [1,2] [2,1] ~=? True
    , "setEqual 3" ~: setEqual [1,2,3] [1,2,3,4] ~=? False
    , "setEqual 4" ~: setEqual [2,3,1] [1,2,3] ~=? True
    , "setEqual 5" ~: setEqual "orca" "arco" ~=? True
    , "setEqual 6" ~: setEqual "orca" "arch" ~=? False
    , "setEqual 7" ~: setEqual "orch" "arch" ~=? False
    
    , "powerset 1" ~: length (powerset ([]::[Int])) ~=? 1
    , "powerset 2" ~: length (powerset [5]) ~=? 2
    , "powerset 3" ~: length (powerset [3,2,5,1,4]) ~=? 32
    , "powerset 4" ~: length (powerset "abc") ~=? 8
    , "powerset 5" ~: length (powerset [1,2]) ~=? 4
    , "powerset 6" ~: length (powerset "Howdy") ~=? 32
    , "powerset 7" ~: length (powerset [1..7]) ~=? 128 
    , "powerset 8" ~: setEqual (powerset "bona") ["","a","n","na","o","on","oa","ona","b","ba","bn","bona","bo","boa","bon","bna"] ~=? True
    , "powerset 9" ~: setEqual (powerset "Hi") ["","i","H","Hi"] ~=? True

    , "scalarproduct 1" ~: scalarproduct [4,5,6] [1,2,3] ~=? 32
    , "scalarproduct 2" ~: scalarproduct [2,3] [1,-1] ~=? -1
    , "scalarproduct 3" ~: scalarproduct [1..10] [1..5] ~=? 55
    , "scalarproduct 4" ~: scalarproduct [2..] [5,2,4,10] ~=? 82
    , "scalarproduct 5" ~: scalarproduct [7,2,10] [3..] ~=? 79

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


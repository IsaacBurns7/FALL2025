
-- CSCE 314 [Sections 200, 502] Programming Languages Fall 2025
-- Hyunyoung Lee
-- Homework Assignment 3 (Total 100 points) 
-- Due on Friday 9/26/25 at 11:59 p.m.

-- Problem 1 (5 points)
-- Student Name: Isaac Burns
-- UIN: 935007106
-- Resources: 

-- On my honor, as an Aggie, I have neither given nor received any unauthorized
-- aid on any portion of the academic work included in this assignment.

module Main where

import Test.HUnit
import System.Exit
import Data.List
import Data.Char

-- *** Read textbook Chapters 5, 6, and 7, and the problem specifications
-- *** and requirements in hw3.pdf carefully!

-- Problem 2 (Chapter 6, Exercise 7) (10 points)
merge :: Ord a => [a] -> [a] -> [a]
merge [] ys = ys
merge xs [] = xs
merge (x:xs) (y:ys) 
  | x <= y = x : merge xs (y:ys)
  | otherwise = y :  merge (x:xs) ys



-- Problem 3 (Chapter 6, Exercise 8) (5+10=15 points)
---- Question 3.1 (5 points)
halve :: [a] -> ([a], [a])
-- halve xs = (take (length xs `div` 2) xs, drop (length xs `div` 2) xs)
halve xs = splitAt (length xs `div` 2) xs


---- Question 3.2 (10 points)
msort :: Ord a => [a] -> [a]
msort [] = []
msort [x] = [x]
--the below is kind of slow and inefficient, not sure how to make better
--msort xs 
--  | y < z = y : msort (ys ++ (z:zs))
--  | otherwise = z : msort ((y:ys) ++ zs)
--  where ((y:ys), (z:zs)) = halve xs
msort xs = merge (msort l1) (msort l2)
  where (l1, l2) = halve xs


-- Problem 4 (10+10+10=30 points)
---- Question 4.1 (10 points) 
mergeBy :: (a -> a -> Bool) -> [a] -> [a] -> [a]
mergeBy _ [] ys = ys
mergeBy _ xs [] = xs
mergeBy f (x:xs) (y:ys) 
  | f x y = x : mergeBy f xs (y:ys)
  | otherwise = y : mergeBy f (x:xs) ys


---- Question 4.2 (10 points) 
msortBy :: (a -> a -> Bool) -> [a] -> [a]
msortBy _ [] = []
msortBy _ [x] = [x]
msortBy f xs = mergeBy f (msortBy f l1) (msortBy f l2)
  where (l1, l2) = halve xs


---- Question 4.3 (10 points)
{- Write your answer for Question 4.3 within this block comment.
-- Should be detailed step-by-step.
  msortBy (>) [4,8,5,10,2] = mergeBy (>) (msortBy (>) [4,8]) (msortBy (>) [5, 10, 2])
  Step 1: split [4,8] -> [4], [8]; split [5, 10, 2] -> [5], [10,2];
  Step 2: recursively sort halves
  msortBy (>) [4,8] = mergeBy (>) [4] [8] = [8,4]
  msortBy (>) [5,10,2] = mergeBy (>) [5] (msortBy (>) [10, 2]) = [10, 5, 2]
  Step 3: mergeBy (>) [8,4] [10,5,2]
  lazy evaluation dictates only evaluates heads
  8 > 10 -> not true -> take 10
  8 > 5 -> true -> take 8
  4 > 5 -> not true -> take 5
  4 > 2 -> true -> take 4
  [2] -> take 2
  [10, 8, 5, 4, 2]

-}


-- Problem 5 (10+5+10=25 points)
---- Question 5.1 (10 points)
myInsert :: Ord a => a -> [a] -> [a]
myInsert a [] = [a]
myInsert a (x:xs)
  | a <= x = a : (x:xs)
  | otherwise = x : myInsert a xs

---- Question 5.2 (5 points)
mySort :: Ord a => [a] -> [a]
mySort xs = foldr myInsert [] xs

---- Question 5.3 (10 points)
{- Write your answer for Question 5.3 within this block comment.
-- Should be detailed step-by-step.
  foldr myInsert [] [10,6,5,9,2]
  myInsert 10 (myInsert 6 (myInsert 5 (myInsert 9 (myInsert 2 []))))
  myInsert 2 [] -> [2] by defn
  myInsert 9 [2] -> 2 : myInsert 9 [] = [2,9]
  myInsert 5 [2, 9] -> 2 : 5 : [9]
  myInsert 6 [2,5,9] -> 2 : 5 : 6 : [9]
  myInsert 10 [2,5,6,9] -> 2 : 5 : 6 : 9 : [10]
  [2,5,6,9,10]
-}



-- Problem 6 (Chapter 7, Exercise 9) (10+5=15 points)
---- Question 6.1 (10 points)
altMap :: (a -> b) -> (a -> b) -> [a] -> [b]
altMap _ _ [] = []
altMap f _ [a] = [f a]
altMap f g (a:b:xs) = f a : g b : altMap f g xs

---- Question 6.2 (5 points)
{- Write your answer for Question 6.2 within this block comment.
-- Should be detailed step-by-step.
  altMap (*4) (`div` 3) [5:6:7..11] = (*4) 5 : (`div` 3) 6 : altMap (*4) (`div` 3) [7..11]
  altMap (*4) (`div` 3) [7:8:9..11] = (*4) 7 : (`div` 3) 8 : altMap (*4) (`div` 3) [9..11]
  altMap (*4) (`div` 3) [9:10:11] = (*4) 9 : (`div` 3) 10 : altMap (*4) (`div` 3) [11]
  altMap (*4) (`div` 3) [11] = [11*4] = [44] via defn 
  20 : (6 `div` 3) : 28 : (8 `div` 3) : 36 : (10 `div` 3) : 44 = [20, 2, 28, 2, 36, 3, 44]
-}




    
myTestList =
  TestList [

      "merge 1" ~: merge "EGG" "ABCDEFGH" ~=? "ABCDEEFGGGH" 
    , "merge 2" ~: merge "Hello" "e" ~=? "Heello"
    , "merge 3" ~: merge [1,3,5,7,9] [2,4,6] ~=? [1,2,3,4,5,6,7,9]

    , "halve 1" ~: halve "" ~=? ("","")
    , "halve 2" ~: halve "halves" ~=? ("hal","ves")
    , "halve 3" ~: halve "halve" ~=? ("ha","lve")

    , "msort 1" ~: msort "Howdy all!" ~=? " !Hadllowy"
    , "msort 2" ~: msort "" ~=? ""
    , "msort 3" ~: msort "Mississippi" ~=? "Miiiippssss"
    , "msort 4" ~: msort [3,2,1,5,4] ~=? [1,2,3,4,5]

    , "mergeBy 1" ~: mergeBy (>) "FED" "GBA" ~=? "GFEDBA"
    , "mergeBy 2" ~: mergeBy (<) "Howdy" "Maui" ~=? "HMaouiwdy"
    , "mergeBy 3" ~: mergeBy (>) [5,1] [6,4,3] ~=? [6,5,4,3,1]
      
    , "msortBy 1" ~: msortBy (<) "gig 'em" ~=? " 'eggim" 
    , "msortBy 2" ~: msortBy (>) "Jack be nimble" ~=? "nmlkieecbbaJ  "
    , "msortBy 3" ~: msortBy (<) "" ~=? ""
    , "msortBy 4" ~: msortBy (>) [3,2,1,5,4] ~=? [5,4,3,2,1]

    , "myInsert 1" ~: myInsert 'o' "Hw are you?" ~=? "How are you?"
    , "myInsert 2" ~: myInsert 'c' "abdefg" ~=? "abcdefg"
    , "myInsert 3" ~: myInsert 3 [2,4,6] ~=? [2,3,4,6]

    , "mySort 1" ~: mySort "Jack be quick" ~=? "  Jabcceikkqu"
    , "mySort 2" ~: mySort "Howdy all!" ~=? " !Hadllowy"

    , "altMap 1" ~: altMap (* 10) (* 100) [1,2,3,4,5] ~=? [10,200,30,400,50]
    , "altMap 2" ~: and (altMap even odd [1..10]) ~=? False
    , "altMap 3" ~: altMap toLower toUpper "Haskell IS fun!" ~=? "hAsKeLl iS FuN!"
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

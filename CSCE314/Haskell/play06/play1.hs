module Stack ( StkType, push, pop, top, empty ) where

 

data StkType a = EmptyStk | Stk a (StkType a)  deriving Show  -- line (10)

push x s       = Stk x s

pop (Stk _ s)  = s   -- line (11)

top (Stk x _)  = x 

empty          = EmptyStk

 

-- objects stack1 and stack2 are constructed using the same (familiar) interface for stack

 

stack1 = push (1::Int) . push 2 . push 7 $ empty

stack2 = pop stack1




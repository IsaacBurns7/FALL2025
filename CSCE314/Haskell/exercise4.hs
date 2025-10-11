module Stack ( StkType, push, pop, top, empty ) where

 

data StkType a = EmptyStk | Stk a (StkType a)  deriving Show  -- line (10)

push x s       = Stk x s

pop (Stk _ s)  = s      -- line (11)

top (Stk x _)  = x      -- line (12)

empty          = EmptyStk

 

-- how to construct objects stack1 and stack2 is exactly the same as before

-- because the interface is the same

 

stack1 = push (3::Int) . push 4 . push 5 $ empty

stack2 = pop stack1

 
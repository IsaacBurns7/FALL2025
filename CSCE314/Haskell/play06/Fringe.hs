module Fringe (fringe) where
import Tree ( Tree(..) )
fringe (Leaf x) = [x]
fringe (Branch x y) = fringe x

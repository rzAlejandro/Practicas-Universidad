--ALEJANDRO RAMIREZ RODRIGUEZ

--Ejercicio1
--a)
last1 :: [a]->a
last1 [] = error("lista vacía")
last1 (a:xs) = foldl (\x y->y)  a xs

--b)
reverse1 ::[a]->[a]
reverse1 [] =[]
reverse1 xs= foldl (\x y->[y]++x) [] xs

--c)

all1 :: (a->Bool)->[a]->Bool
all1 p xs = foldl (\x y ->(&&) x (p y)) True xs 

--d)
minimum1 :: Ord a=>[a]->a
minimum1 [] = error("lista vacía")
minimum1 (a:xs) = foldl (\x y-> min x y) a xs
minimum2 (a:xs)=foldl min a xs

--e)
map1 :: (a->b)->[a]->[b]
--map1 f xs =foldr (\x y-> [f x] ++ y) [] xs
map1 f xs=foldl (\x y->x ++[f y]) [] xs

--f)
filter1 :: (a->Bool)->[a]->[a]
filter1 p xs = foldl (\x y-> x ++ (if p y then [y] else [])) [] xs

--g)
takeWhile1 :: (a->Bool)->[a]->[a]
takeWhile1 p xs = foldr (\x y-> if p x then [x]++y else []) [] xs


--Ejercicio2
foldr1 f xs=foldr f (last xs) (init xs)
foldl1 f (x:xs)=foldl f x xs


--Ejercicio3
--a)
list1 = [if mod x 2==0 then (- div x 2) else (1 + (div x 2))|x<-[1..10]]
--lit1 = concat (map (\(a,b)->[a,b]) [(n,-n)|n<-[1..]])

--b)
list2 = [(x,n-x)|n<-[0..10],x<-[0..n]]
--list2 = [zip [0..n] [n..0] | n<-[0..10]]


--Ejercicio4
--a)
sufijos :: [a]->[[a]]
sufijos xs=[drop x xs|x<-[0..length xs]]

--b)
sublistas::[a]->[[a]]
sublistas xs=[[]]++ [take x (drop a xs) | x<-[1..length xs],a<-[0..(length xs)-x]]
--sublistas xs=[[]] ++ [take x ys|x<-[1..length xs],ys<-sufijos xs,x<=length ys]

--c)
sublistaTot a (x:xs)=if a==x then xs else [x]++sublistaTot a xs
permutaciones []=[[]]
permutaciones xs=[[a]++ys| a<-xs,ys<-permutaciones(sublistaTot a xs)]

--d)
sumandosAux 0 a=[[]]
sumandosAux n y=[[x]++ys|x<-[y..n],ys<-sumandosAux(n-x) x]
sumandos n=sumandosAux n 1

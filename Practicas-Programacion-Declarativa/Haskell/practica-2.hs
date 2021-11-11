--Alejandro Ramírez Rodríguez
--Ejercicio1
--a)
lista1 :: Int -> [Int]
lista1 0=[0]
lista1 n 
	|n<0 = error "numero negativo"
	|otherwise = lista1 (n-1) ++ [(n^2)]
	
--b)
lista2 :: Int -> [(Int,Int)]
lista2 0=[(0,0)]
lista2 n 
	|n<0 = error "numero negativo"
	|otherwise = (n,(n^2)) : lista2 (n-1)

--c)
valAbs :: Float -> Float
valAbs x
	|x<=0 = -x
	|otherwise = x


suma1 :: Float -> Float --He probado con Int -> Float y usando fromInteger dentro del cos, pero no funciona
suma1 0=0
suma1 n
	|n<0 = error "numero negativo"
	|otherwise = suma1 (n-1) + n*(valAbs(cos n))

--d)
suma2 :: Int -> Int
suma2 0=0
suma2 n
	|n<0 = error "numero negativo"
	|mod (n-1) 3 == 0 || mod (n-1) 5 == 0 = suma2 (n-1) + (n-1) 
	|otherwise = suma2 (n-1)
	
--Tambien he hecho el e y el f
{-
--e)
finaliza :: Int-> Bool
finaliza n = (mod n 100) == 43

num1 ::Int-> Int
num1 0=0
num1 n
	|n<0 = error "numero negativo"
	|mod (n-1) 3 == 0 && (finaliza (n-1)) = 1 + num1 (n-1)
	|otherwise = num1(n-1)
	
-}
--f)
esPrimo n = if length (filter p [2..n-1]) == 0 then True else False where p a = if mod n a == 0 then True else False  
	
--Ejercicio2
--a)
lista1b :: Int -> [Int]
lista1b n = map (^2) [0..n]

--b)
lista2b :: Int -> [(Int,Int)]
lista2b n = zip [n,n-1..0] (map (^2) [n,n-1..0])

--c)
suma1b :: Float -> Float
suma1b n = sum (zipWith (*) [0..n] (map valAbs (map (cos) [0..n])))

--d)
--suma2b n =

--e)
pot n=length(filter (p1) (map (^3) [0..n]))
	where p1 a = (mod a 100 == 43) && a<n
--f)
--esprimo n= not (any (==0) (zipWith (mod) [n,n..] [1..(sqrt n)]))


--Ejercicio3
--a) 
{- Esta solucion no sigue los requisitos del tipado de n y m , pues son Eq, no Enum
iguales f g n m
	|f n == g n = if (n<m) then iguales f g (n+1) m else True
	|otherwise = False
-}
iguales :: (Enum a,Eq b) => (a->b)->(a->b)->a->a->Bool
iguales f g n m = (map f [n..m])==(map g [n..m])

--b)
menorA:: Enum a => a -> a -> (a -> Bool) -> a
menorA n m p = head (filter p [n..m])

--c)
mayor :: (Num a,Enum a)=> a-> (a-> Bool)->a
mayor n p = head(filter p [n, n-1..])

--d)
ex:: Enum a => a->a->(a->Bool)->Bool
ex n m p=any p [n..m]


--Ejercicio 4
--a)
filter2 :: [a]->(a->Bool)->(a->Bool)->([a],[a])
filter2 xs p q = (filter p xs,filter q xs)

--b)        
filters :: [a]->[a->Bool]->[[a]]
filters xs []= []
filters xs ps=[(filter (head ps) xs)] ++ filters xs (tail ps)
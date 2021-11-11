--Alejandro Ramírez Rodríguez

--Ejercicio1
-- a)
anos=let x=10^6 in (((x/60)/60)/24)/365

-- b)
anosBien=let { s=10^6; m=div s 60; h= div m 60; d=div h 24; a=div d 365}in (s-m*60,m-h*60,h-d*24,d-a*365,a) --Se devuelve una tupla: (seg,min,hor,dias,anos)

-- c)
anos2 :: Fractional a => a->a
anos2 x= (((x/60)/60)/24)/365

anosBien2 :: Int -> (Int,Int,Int,Int,Int)
anosBien2 s = let { m=div s 60; h= div m 60; d=div h 24; a=div d 365}in (s-m*60,m-h*60,h-d*24,d-a*365,a)


--Ejercicio2
media :: [Float] -> Float
media []=0
media lista= (sum lista)/(fromIntegral(length lista))


--Ejercicio3
-- a)
valor_abs :: Int -> Int
valor_abs x
	|x<0 = -x
	|otherwise = x
	
num_digitos :: Int-> Int
num_digitos x
	|(valor_abs x)<=9	= 1
	|otherwise = 1 + num_digitos (div x 10)
	
-- b)
suma_digitos :: Int->Int
suma_digitos x
	|x<=9 = x
	|otherwise = (mod x 10) + suma_digitos(div x 10)
	
reduccion :: Int -> Int
reduccion x
	|x<0 = reduccion (-x)
	|x<=9 = x
	|otherwise = reduccion (suma_digitos x)

-- c)
comb :: Int -> Int -> Int
comb n m
	|(n<0 || m<0) = error "numero negativo"
	|n==0 = 0
	|m==0 = 1
	|n==m = 1
	|otherwise = (comb (n-1) (m-1))+(comb (n-1) (m))

--Ejercicio4
c :: Bool->Bool->Bool
c False x = False
c x False = False
c x True = x
c True x = x


--Estricta para el primer argumento (no para el segundo)
c1 :: Bool->Bool->Bool
c1 True x =x
c1 False x= False
c1 x y = x

--Estricta para el segundo argumento (no para el primero)
c2 :: Bool->Bool->Bool
c2 x True = x
c2 x False = False
c2 x y = y

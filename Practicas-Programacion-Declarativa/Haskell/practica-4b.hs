--Alejandro Ramírez Rodríguez

--Ejercicio1
type Punto=(Int,Int)
data Direccion=Arriba | Abajo | Izquierda | Derecha deriving (Eq,Ord,Show)

avanzar::Punto->Direccion->Punto
avanzar (a,b) m
	|m==Arriba = (a,b+1)
	|m==Abajo=(a,b-1)
	|m==Izquierda = (a-1,b)
	|m==Derecha=(a+1,b)
	
destino::Punto->[Direccion]->Punto
destino (a,b) xs = foldl (avanzar) (a,b) xs

trayectoria :: Punto->[Direccion]->[Punto]
trayectoria (a,b) movs = foldl (\x y-> x ++ [(avanzar (last x) y)]) [(a,b)] movs

--Trasladando el origen a cualquier punto del plano n*n, la condición de las trayectorias permanece invariante, por lo que el punto inicial
--va a ser el (0,0). 
inferior :: Int->[Direccion]->[Direccion]->Bool
inferior n movs movs2 = and (zipWith (\x y -> snd (x) <= snd (y)) (trayectoria (0,0) movs)(trayectoria (0,0) movs2))


--Ejercicio2
data Arbol a = Hoja a | Nodo a [Arbol a] deriving Eq

listaHojas :: Arbol a->[a] 
listaHojas (Hoja x)=[x]
listaHojas (Nodo x subArbol)= foldl (\x y -> x ++ listaHojas y) [] subArbol

listaNodos :: Arbol a->[a] 
listaNodos (Hoja x)=[x]
listaNodos (Nodo z subArbol)= foldl (\x y -> x ++ listaNodos y) [z] subArbol


reconstruirArbol :: Arbol a -> a -> Arbol a
reconstruirArbol (Hoja y) x=Hoja x
reconstruirArbol (Nodo y subArbol) x = Nodo x [reconstruirArbol nodo x|nodo<-subArbol]

repMax :: Ord a =>Arbol a -> Arbol a
repMax t= let xs=(listaNodos t) in (let maximo=(foldl (\x y->if x<=y then y else x) (head xs) xs) in reconstruirArbol t maximo)

instance Ord a => Ord (Arbol a) where
	(Hoja a) <= (Hoja b) = a<=b
	(Hoja a) <= (Nodo b subArbol) = True
	(Nodo b subArbol)<=(Hoja a)=False
	(Nodo a subArbol1) <= (Nodo b subArbol2) 
		|length(subArbol1)==0 = a<=b 
		|length(subArbol2)==0 = a<b || False 
		|otherwise = a<b || (a==b && ((head subArbol1)<=(head subArbol2)))


instance Show a => Show (Arbol a) where
	show (Hoja a)= show a
	show (Nodo a subArbol)= (show a) ++ "(" ++ (foldl (\x y-> x ++ ";" ++ (show y)) (show (head subArbol)) (tail subArbol))++")"

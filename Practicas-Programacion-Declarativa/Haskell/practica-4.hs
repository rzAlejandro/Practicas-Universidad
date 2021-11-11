--Alejandro Ramírez Rodríguez
--Ejercicio1
type Coordenada=Int
type Punto=(Coordenada,Coordenada)
data Direccion=Arriba | Abajo | Izquierda | Derecha deriving (Eq,Ord,Show)

destino::Punto->[Direccion]->Punto
destino (x,y) []=(x,y)
destino (x,y) (m:ms)
	|m==Arriba = destino (x,y+1) ms
	|m==Abajo = destino (x,y-1) ms
	|m==Izquierda = destino (x-1,y) ms
	|m==Derecha = destino (x+1,y) ms

avanzar::Punto->Direccion->Punto
avanzar (a,b) m
	|m==Arriba = (a,b+1)
	|m==Abajo=(a,b-1)
	|m==Izquierda = (a-1,b)
	|m==Derecha=(a+1,b)
	
destino2::Punto->[Direccion]->Punto
destino2 (a,b) xs = foldl (avanzar) (a,b) xs

	
--Ejercicio2
data Nat=Cero | Suc Nat deriving (Eq,Ord)

natToInt:: Nat->Int
natToInt Cero = 0
natToInt (Suc x) = (natToInt x) + 1

infixr +.
Cero +. a= a
(Suc x) +. y = x +. (Suc y)

infixr *.
Cero *. a = Cero
(Suc x) *. y = (+.) ((+.) y Cero) ((*.) x y)  -- y + (x-1)*y


instance Show Nat where
	show n= show (natToInt (n))
	

--Ejercicio3
data Complejos = C Int Int

instance Show Complejos where
	show (C 0 1) = "i"
	show (C 0 (-1))="-i"
	show (C x 1)= (show x) ++ "i"
	show (C x (-1))=(show x) ++ "-i"
	show (C x y)= (show x)++ (if y>0 then "+" else "") ++ (show y) ++ "i"

instance Eq Complejos where
	C x y == C u v = x==u && y==v

instance Num Complejos where
	(C x y) + (C u v)= C (x+u) (y+v)
	(C x y) - (C u v)= C (x-u) (y-v)
	(C x y) * (C u v)= C (x*u-y*v) (x*v + y*u)

--Ejercicio4
class Medible a where
	medida::a ->Int

instance Medible Bool where
	medida False=0
	medida True=1

instance Medible Int where
	medida x=x

instance (Medible a,Medible b) => Medible (a,b) where
	medida (x,y)=(medida x) + (medida y)

instance Medible a => Medible [a] where
	--medida xs=sum [medida x|x<-xs]
	medida xs= foldl (\x y->x+(medida y)) 0 xs


--Alejandro Ramirez Rodriguez


data Rel a = R [(a,a)] deriving Read

--Definimos relaciones para poder operar con ellas:
r1:: Rel Int
r1 = (R [(i,2*i)|i<-[0..10]])

r2:: Rel Int
r2 = (R [(x,y)|x<-[0..3],y<-[0..3]])

r3= (R [(1,1),(2,2),(3,3),(1,2),(2,1),(1,3),(3,1),(2,3),(3,2),(5,5),(5,6),(6,5),(6,6)])

r4= (R [(1,1),(3,3),(1,3),(3,1),(3,2),(5,5),(5,6),(6,6)])

r5= (R [(1,2),(2,4),(5,3),(3,1),(4,1)])

r6 = (R [(2,3),(2,5),(4,1),(1,2),(3,1)])

--1
nVeces::Eq a=> [a]->a->Int
nVeces [] x=0
nVeces (x:xs) y = if x==y then 1 + (nVeces xs y) else nVeces xs y

esRelacion:: Eq a => Rel a ->Bool
esRelacion (R [])=True
esRelacion (R xs)= if sum(map(nVeces xs)xs)> length xs then False else True

--2
instance Eq a=> Eq (Rel a) where
	(R [])== (R [])=True
	(R [])==(R ys)= False
	(R xs)==(R [])= False
	(R xs) ==(R ys) = length xs == (length ys) && and [or (map (\z->x==z) ys)| x<-xs ]
	--Compruebo la longitud para ganar en eficiencia

instance Show a => Show (Rel a) where
	show (R [])= ""
	show (R xs)=(foldl (\x y->x ++ show (fst y) ++ " ~ " ++ show (snd y)++ " , ") "" (init xs)) ++ show (fst (last xs)) ++ " ~ " ++ show (snd(last xs))
	
--Read va a utilizar las funciones por defecto.


--3
dominio :: (Eq a) => Rel a->[a]
--Con la comprobacion del segundo tipo de esRelación, solo tenemos que coger todos los primeros elementos. 
--Con la comprobación estándar, es necesario eliminar repetidos para que sea un conjunto

dominio (R [])=[]
dominio (R xs)= if not (esRelacion (R xs)) then error("relación no admitida") else foldl (\x y-> x ++ (if elem (fst y) x then [] else [fst y])) [] xs

soporte:: (Eq a)=> Rel a-> [a]
soporte (R [])=[]
soporte (R xs)= if not (esRelacion (R xs)) then error("relación no admitida") else foldl (\x y-> x++ (if elem (snd y) x then [] else [snd y])) (dominio (R xs)) xs



reflexiva::(Eq a)=>Rel a->Bool
reflexiva (R [])=True
reflexiva (R xs)= all (\x->elem(x,x) xs)  (soporte (R xs))

simetrica::(Eq a)=> Rel a -> Bool
simetrica (R [])= True
simetrica (R xs)= all (\x->elem(snd x,fst x) xs) xs

transitiva :: (Eq a)=> Rel a-> Bool
transitiva (R [])= True
transitiva (R xs)=and [if elem (fst x, snd y) xs then True else False|x<-xs,y<-xs,(snd x == (fst y))]

relEquivalencia::(Eq a)=> Rel a-> Bool
relEquivalencia (R xs)= reflexiva (R xs) && (simetrica (R xs)) && (transitiva (R xs))


cociente::(Eq a)=>Rel a->[a]
cociente (R xs) = foldl (\y z->y ++ if (or ([if elem (k,z) xs then True else False|k<-y])) then [] else [z]) [] (soporte (R xs)) 

conjCociente::(Eq a)=> Rel a-> [a]
conjCociente (R [])=[]
conjCociente (R xs)= if relEquivalencia (R xs) then cociente (R xs) else error("la relacion no es de equivalencia")

generaDiv::(Ord a,Integral a)=>a->a->Rel a
generaDiv n m=if n>m then error("n ha de ser menor que m") else  R [(x,y)|y<-[1..m],x<-[n..y],mod y x==0]

generaGE :: (Ord a)=> [a]->Rel a
generaGE xs= R [(x,y)|x<-xs,y<-xs,x>=y]


cierreR :: (Eq a)=>Rel a->[(a,a)]
cierreR (R xs)= xs ++ [(x,x)|x<-soporte (R xs),not (elem (x,x) xs)]

cierreS :: (Eq a)=>[(a,a)]->[(a,a)]
cierreS xs = foldl (\x y-> x ++ if elem (snd y,fst y) xs then [] else [(snd y,fst y)]) xs xs

cierreRST :: (Eq a)=> Rel a->Rel a
cierreRST (R [])=R []
cierreRST (R xs)= let ys = (cierreS (cierreR  (R xs))) in R (ys ++ [(fst x,snd y)|x<-ys,y<-ys,(snd x == (fst y)),not (elem (fst x,snd y) ys)])


compararSoportes::(Eq a)=> [a]->[a]->Bool
compararSoportes xs ys= all(\x-> elem x ys) xs && (all (\x->elem x xs) ys)

composicion :: (Eq a)=>Rel a->Rel a->Rel a
composicion (R xs) (R ys)= if compararSoportes (soporte (R ys)) (soporte (R ys)) then R ([(fst x,snd y)|x<-xs,y<-ys, (snd x)==(fst y)]) else error("no coinciden los soportes")

--4

--Solo acepta relaciones de tipo Char

anadirPar:: Rel Char-> (Char,Char)->Rel Char
anadirPar (R xs) (x,y)= R (xs++[(x,y)])


leerRel::Rel Char-> IO(Rel Char)
leerRel r= do
	line <- getLine
	if line == "0" then 
		return r
		else do
			let par = read line :: (Char,Char)
			r<-return (anadirPar r par)
			leerRel r


introRel::IO(Rel Char)
introRel = do
	r <- return (R [])
	putStrLn "Introduzca una relacion de tipo Char. Cada par debe ir en una línea diferente. Cuando haya finalizado inserte 0: "
	leerRel r



mostrarElem:: Char->Int->[(Char,Char)]->[Char]->String
mostrarElem x j xs ss 
	|j==length ss = ""
	|j== (length ss)-1 = (if elem (x,(ss !! j)) xs then "x " else "  ") ++  (mostrarElem x (j+1) xs ss)
	|otherwise = (if elem (x,(ss !! j)) xs then "x  " else "   ") ++  (mostrarElem x (j+1) xs ss)

mostrarRel:: Rel Char-> Int->[Char] ->IO()
mostrarRel (R xs) i ss = do 
	if i == length ss then
		return ()
		else do
			let x = ss !! i
			putStrLn (" "++(show x)++"| "++ (mostrarElem x 0 xs ss)++"|")
			mostrarRel (R xs) (i+1) ss

muestraRel::IO()
muestraRel=do
	r <- introRel
	if not (esRelacion r) then
		--putStrLn "Introdujo una relación errónea. "
		return ()
		else do
			putStrLn "Relación introducida: "
			let ss = soporte r
			putStrLn " "
			putStrLn("     "++ (foldl (\x y-> x++ (show y)) "" ss))
			putStrLn("     "++ (foldl (\x y-> x++"---") "" ss))
			mostrarRel r 0 ss
			putStrLn("     "++ (foldl (\x y-> x++"---") "" ss))
			return ()
			
		
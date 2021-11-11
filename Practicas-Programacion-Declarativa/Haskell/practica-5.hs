--Ejercicio1
adivina :: Int -> IO ()
adivina n = do
	putStrLn "Escriba un número:"
	sx<-getLine
	x<-return(read sx::Int)
	if x==n then
		print ("Acertaste")
		else do
			if x<n then print("Introduciste numero menor") else print("Introduciste numero mayor")
			adivina n
			

--Ejercicio2
aux::[String]->Int->[String]
aux y k= let n=(div k ((length y) - 1)) in let {l1=foldl (\x z->x++" ") "" [1..n]; l2=foldl (\x z->x++" ") "" [1..k - n*((length y)-1)]} in (foldl (\x z->x ++ [z++l1])[] (init y))++[l2]++ [last y]

putSpaces::[[String]]->Int->[[String]]
putSpaces w n= foldl(\x y->x ++ [if (length y)< n then aux y (n - length y) else y])  [] w 




formatea:: String -> String -> Int -> IO ()
formatea fileIn fileOut n=do
	contentLines <- return (lines fileIn)
	wordsPerLines <- return (foldl(\x y -> x ++ [words y]) [] contentLines)
	wordsFixedPerLines <- return (putSpaces wordsPerLines n)
	contentLinesFixed <- return ([unwords s| s<-wordsFixedPerLines])
	contentFileFixed <- return (unlines contentLinesFixed)
	writeFile fileOut contentFileFixed
	
	



--Ejercicio3
data Matrix = M Int Int [[Float]] deriving Show

comprobarCorrecta::Matrix->Bool
comprobarCorrecta (M f c xs)= if (f==(length xs) && (foldl (\x y-> x && c==length(y)) True xs)) then True else False

trasponer::Int->[[Float]]->[[Float]]
trasponer c xs = [[x!!i|x<-xs]|i<-[0..(c-1)]]

transp :: Matrix -> Maybe Matrix
transp (M f c xs) = if comprobarCorrecta (M f c xs) then Just (M c f (trasponer c xs)) else Nothing

sumarMatrix::[[Float]]->[[Float]]->[[Float]]
sumarMatrix xs ys = zipWith(\x y->zipWith(+)x y) xs ys

sumaMat::Matrix->Matrix->Maybe Matrix
sumaMat (M f1 c1 xs) (M f2 c2 ys)= if comprobarCorrecta (M f1 c1 xs) && comprobarCorrecta (M f2 c2 ys) && f1==f2 && c1==c2 then Just (M f1 c1 (sumarMatrix xs ys)) else Nothing

productoMatrix::Int->Int->[[Float]]->[[Float]]->[[Float]]
productoMatrix c1 c2 xs ys= [[sum[(x!!i)*((ys!!i)!!j)|i<-[0..(c1-1)]]|j<-[0..(c2-1)]]|x<-xs]

prodMat::Matrix->Matrix->Maybe Matrix
prodMat (M f1 c1 xs) (M f2 c2 ys)= if comprobarCorrecta (M f1 c1 xs) && comprobarCorrecta (M f2 c2 ys) && c1==f2 then Just (M f1 c2 (productoMatrix c1 c2 xs ys)) else Nothing

dibujar a f c xs=do
	if a==f then
		return ()
		else do
			putStrLn( (foldl(\e x->e++(show x)++" ")("| ")(xs!!a)) ++ "|")
			dibujar (a+1) f c xs
	
dibujarMatriz (M f c xs)=do
	if comprobarCorrecta (M f c xs) then 
		dibujar 0 f c xs
		else do
			print("Matriz incorrecta")
			
% Alejandro Ramirez Rodriguez
%1
elimina1([ ],X,[ ]).
elimina1([X|R],Y,NR) :- Y == X, elimina1(R,Y, NR).
elimina1([X|R],Y,[X|NR]) :- Y \== X, elimina1(R,Y,NR).

elimina2([ ],X,[ ]).
elimina2([X|R],Y,NR) :- Y = X, elimina2(R,Y, NR).
elimina2([X|R],Y,[X|NR]) :- Y \= X, elimina2(R,Y,NR).

elimina3([ ],X,[ ]).
elimina3([X|R],X,NR) :- elimina3(R,X,NR).
elimina3([X|R],Y,[X|NR]) :- Y \== X, elimina3(R,Y,NR).

% En la primera prueba, que se pide únicamente averiguar la lista, las tres dan la misma salida. Tiene sentido pues en este caso la var Y se estaría unificando.
% Sin embargo, en el segundo caso en el que la letra que se elimina es otra var, elimina1 da como salida la lista entera, pues X no se unifica y en la segunda 
% clausula tenemos Y==X, que siempre va a fallar.
% elimina2 si que ofrece una salida que buscamos, pues en ese caso Y unifica con X, lo que significa que Y pasa a valer a en el primer paso. Además es la única 
% solución, pues no es posible que Y no unifique con a.
% elimina3 muestra todas las soluciones porque puede unificar en ambas ramas.

%2
sumatree(void,0).
sumatree(arbol(Elemento,Izq,Der),X):-sumatree(Izq,X1),sumatree(Der,X2),X is Y+X1+X2.

maximoN(X1,X2,X3,X3):- X3>=X2,X3>=X1.
maximoN(X1,X2,X3,X2):- X2>=X1,X2>=X3.
maximoN(X1,X2,X3,X1):- X1>=X2,X1>=X3.

maximo(void,0).
maximo(arbol(Elemento,Izq,Der),M):-maximo(Izq,M1),maximo(Der,M2),maximoN(M1,M2,M3,M).

%3
%sublista(X,L):X es sublista de L.

prefijo([],_).
prefijo([X|Xs],[X|Ys]) :- prefijo(Xs,Ys).
	
sufijo(Xs,Xs).
sufijo(Xs,[_|Ys]) :- sufijo(Xs,Ys).
	
sublista([],_).
sublista([X|Xs],L) :- prefijo(L1,L),sufijo([X|Xs],L1).

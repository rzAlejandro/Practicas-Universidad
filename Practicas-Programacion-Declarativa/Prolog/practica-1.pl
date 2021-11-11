%Ejercicio1
nat(c).
nat(s(X)) :- nat(X).

sum(X,c,X) :- nat(X)
sum(X,s(Y),s(Z)) :- 
	sum(X,Y,Z),
	

prod(X,c,c) :- nat(x)
prod(X,s(Y),Z):-
	prod(X,Y,W),
	sum(X,W,Z).

pot(X,0,1).
pot(X,N,Y*X) :-
	pot(X,N-1,Y),
	X\=0,
	nat(N-1),
	nat(Y).

fact(0,1).
fact(X,Y*X) :-
	fact(X-1,X),
	nat(X),
	nat(Y).


	



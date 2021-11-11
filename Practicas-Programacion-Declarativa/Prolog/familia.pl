%% Relaciones familiares como programa lógico %%


% Unos cuantos hechos definen la relación hombre/1
hombre(javier).
hombre(pedro).
hombre(jorge).
hombre(alfonso).
hombre(juan).

% Unos cuantos hechos definen la relación mujer/1
mujer(maria).
mujer(carmen).
mujer(teresa).
mujer(alicia).


% Unos cuantos hechos definen la relación progenitor/2
% progenitor(X,Y) <-> X es progenitor de Y 
progenitor(javier,pedro).
progenitor(javier,teresa).
progenitor(maria,pedro).
progenitor(maria,teresa).
progenitor(pedro,alfonso).
progenitor(pedro,juan).
progenitor(carmen,juan).
progenitor(carmen,alfonso).
progenitor(jorge,alicia).
progenitor(teresa,alicia).

% Las relaciones padre/2, madre/2, hijo/2, etc, definidas mediante 
% reglas o cláusulas condicionales.
padre(X,Y) :-
  progenitor(X,Y),
  hombre(X).
madre(X,Y) :-
  progenitor(X,Y),
  mujer(X).

hijo(X,Y) :-
  progenitor(Y,X),
  hombre(X).

abuelo(X,Y) :-
  progenitor(Z,Y),
  padre(X,Z).

hermano(X,Y) :-
  progenitor(Z,X),
  progenitor(Z,Y).
% ¿Qué pasa con el objetivo hermano(pedro,pedro)?
% Una variante de hermano para que una persona no resulte hermana de sí misma
hermano1(X,Y) :-
  progenitor(Z,X),
  progenitor(Z,Y),
  distinto(X,Y).

distinto(X,Y) :- X \= Y.

tio(X,Y) :-
  progenitor(Z,Y),
  hermano(Z,X).

descendiente(X,Y) :-
  progenitor(Y,X).
descendiente(X,Y) :-
  progenitor(Y,Z),
  descendiente(X,Z).





















// Nombre del alumno Alejandro Ramírez
// Usuario del Juez DG24


#include <iostream>
#include <iomanip>
#include <fstream>
#include <algorithm>
#include "bintree_eda.h"


// función que resuelve el problema
struct tProp {
	int alt;
	int max;
	int min;
	bool ok;
};
int mod(int x) {
	if (x < 0)
		return -x;
	else return x;
}

// función que resuelve el problema
tProp resolver(bintree<int> const &  arbol) {
	if (arbol.empty()) {
		tProp r = { 0,265463,0,true };
		return r;
	}
	if (arbol.left().empty() && arbol.right().empty()) {
		tProp r = { 1,arbol.root(),arbol.root(),true };
		return r;
	}
	else {
		tProp rD = resolver(arbol.right());
		tProp rI = resolver(arbol.left());
		tProp r;
		r.alt = std::max(rD.alt, rI.alt) + 1;
		if (arbol.right().empty()){
			r.max = arbol.root();
			r.min = rI.min;
			r.ok = mod(rD.alt - rI.alt) <= 1 && rD.ok && rI.ok && arbol.root() > rI.max;
		}
		else {
			if (arbol.left().empty()) {
				r.max = rD.max;
				r.min = arbol.root();
				r.ok = mod(rD.alt - rI.alt) <= 1 && rD.ok && rI.ok  && arbol.root() < rD.min;
			}
			else {
				r.max = rD.max;
				r.min = rI.min;
				r.ok = mod(rD.alt - rI.alt) <= 1 && rD.ok && rI.ok && arbol.root() > rI.max && arbol.root() < rD.min;
			}
				
		}
		return r;
	}
}

// Resuelve un caso de prueba, leyendo de la entrada la
// configuración, y escribiendo la respuesta
void resuelveCaso() {
	// leer los datos de la entrada
	bintree<int> arbol = leerArbol(-1);

	tProp sol = resolver(arbol);
	// escribir sol
	if (sol.ok)
		std::cout << "SI";
	else std::cout << "NO";
	std::cout << '\n';

}


int main() {
	// Para la entrada por fichero.
	// Comentar para acepta el reto
#ifndef DOMJUDGE
     std::ifstream in("datos.txt");
     auto cinbuf = std::cin.rdbuf(in.rdbuf()); //save old buf and redirect std::cin to casos.txt
#endif 


	int numCasos;
	std::cin >> numCasos;
	for (int i = 0; i < numCasos; ++i)
		resuelveCaso();


	// Para restablecer entrada. Comentar para acepta el reto
#ifndef DOMJUDGE // para dejar todo como estaba al principio
     std::cin.rdbuf(cinbuf);
	system("PAUSE");
#endif

	return 0;
}
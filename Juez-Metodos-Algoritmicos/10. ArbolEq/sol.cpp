// Nombre del alumno Alejandro Ramírez
// Usuario del Juez DG24


#include <iostream>
#include <iomanip>
#include <fstream>
#include <algorithm>
#include "bintree_eda.h"

struct tProp {
	int alt;
	bool eq;
};
int mod(int x) {
	if (x < 0)
		return -x;
	else return x;
}

// función que resuelve el problema
tProp resolver(bintree<char> const &  arbol) {
	if (arbol.empty()) {
		tProp r = { 0,true };
		return r;
	}
	else {
		tProp r1 = resolver(arbol.right());
		tProp r2 = resolver(arbol.left());
		tProp r;
		r.alt = std::max(r1.alt, r2.alt) + 1;
		r.eq = mod(r1.alt - r2.alt) <= 1 && r1.eq && r2.eq;
		return r;
	}
}

// Resuelve un caso de prueba, leyendo de la entrada la
// configuración, y escribiendo la respuesta
void resuelveCaso() {
	// leer los datos de la entrada
	bintree<char> arbol = leerArbol('.');

	tProp sol = resolver(arbol);
	// escribir sol
	if (sol.eq)
		std::cout << "SI";
	else std::cout << "NO";
	std::cout << '\n';

}

int main() {
	// Para la entrada por fichero.
	// Comentar para acepta el reto
#ifndef DOMJUDGE
//     std::ifstream in("datos.txt");
//     auto cinbuf = std::cin.rdbuf(in.rdbuf()); //save old buf and redirect std::cin to casos.txt
#endif 


	int numCasos;
	std::cin >> numCasos;
	for (int i = 0; i < numCasos; ++i)
		resuelveCaso();


	// Para restablecer entrada. Comentar para acepta el reto
#ifndef DOMJUDGE // para dejar todo como estaba al principio
//     std::cin.rdbuf(cinbuf);
	system("PAUSE");
#endif

	return 0;
}
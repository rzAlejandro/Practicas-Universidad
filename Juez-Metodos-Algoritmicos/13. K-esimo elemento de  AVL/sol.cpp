// Nombre del alumno Alejandro Ramírez
// Usuario del Juez DG24


#include <iostream>
#include <iomanip>
#include <fstream>
#include <algorithm>
#include <vector>
#include "TreeMap_AVL.h"



// función que resuelve el problema


// Resuelve un caso de prueba, leyendo de la entrada la
// configuración, y escribiendo la respuesta
bool resuelveCaso() {
	// leer los datos de la entrada
	int N, num,M;
	std::cin >> N;
	if (N == 0)
		return false;
	map <int, int> arbol;
	std::vector<int> consultas;
	for (int i = 0; i < N; ++i) {
		std::cin >> num;
		arbol.insert(num);
	}
	std::cin >> M;
	for (int i = 0; i < M; ++i) {
		std::cin >> num;
		consultas.push_back(num);
	}
	arbol.resolver(consultas);
	// escribir sol
	std::cout <<"---"<<'\n';
	return true;

}

int main() {
	// Para la entrada por fichero.
	// Comentar para acepta el reto
#ifndef DOMJUDGE
	std::ifstream in("datos.txt");
	auto cinbuf = std::cin.rdbuf(in.rdbuf()); //save old buf and redirect std::cin to casos.txt
#endif 


	while (resuelveCaso())
		;


	// Para restablecer entrada. Comentar para acepta el reto
#ifndef DOMJUDGE // para dejar todo como estaba al principio
	std::cin.rdbuf(cinbuf);
	system("PAUSE");
#endif

	return 0;
}
// Nombre del alumno Alejandro Ramírez
// Usuario del Juez DG24


#include <iostream>
#include <iomanip>
#include <fstream>
#include <algorithm>
#include <vector>
#include "PriorityQueue.h"

// función que resuelve el problema
bool myfunction(std::pair<int, int> p1, std::pair<int, int> p2) {
	return p1.first < p2.first;
}
bool perteneceInt(std::pair<int, int> p1, std::pair<int, int> p2) {
	return (p1.first >= p2.first && p1.first < p2.second);
}
// Resuelve un caso de prueba, leyendo de la entrada la
// configuración, y escribiendo la respuesta
bool resuelveCaso() {
	// leer los datos de la entrada
	int N;
	long int ini, fin;
	std::cin >> N;
	if (N==0)
		return false;
	std::vector<std::pair<int, int>>v1;
	PriorityQueue <int> interv;
	for (int i = 0; i < N; ++i) {
		std::cin >> ini >> fin;
		v1.push_back({ ini,fin });
	}
	int sol = 1;
	std::sort(v1.begin(), v1.end(), myfunction);
	interv.push(v1[0].second);
	for (int i = 1; i < v1.size(); ++i) {
		if (v1[i].first < interv.top()) {
			interv.push(v1[i].second);
		}
		else {
			interv.pop();
			interv.push(v1[i].second);
		}
	}

	// escribir sol
	std::cout << interv.size() - 1 << '\n';
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
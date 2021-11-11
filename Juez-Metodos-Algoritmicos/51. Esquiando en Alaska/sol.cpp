// Nombre del alumno Alejandro Ramírez
// Usuario del Juez DG24


#include <iostream>
#include <iomanip>
#include <fstream>
#include <vector>
#include "PriorityQueue.h"
int valAbs(int x) {
	if (x < 0)
		return -x;
	else return x;
}

// función que resuelve el problema


// Resuelve un caso de prueba, leyendo de la entrada la
// configuración, y escribiendo la respuesta
bool resuelveCaso() {
	// leer los datos de la entrada
	int N, n1;
	std::cin >> N;
	if (N==0)
		return false;
	PriorityQueue <int> alt;
	PriorityQueue <int> longitudes;
	for (int i = 0; i < N; ++i) {
		std::cin >> n1;
		alt.push(n1);
	}
	for (int i = 0; i < N; ++i) {
		std::cin >> n1;
		longitudes.push(n1);
	}
	int sol = 0;
	for (int i = 0; i < N; ++i) {
		int x = alt.top();
		int y = longitudes.top();
		sol += valAbs(x - y);
		alt.pop();
		longitudes.pop();
	}

	std::cout << sol << '\n';
	// escribir sol

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
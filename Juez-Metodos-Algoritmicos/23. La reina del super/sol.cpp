// Nombre del alumno Alejandro Ramírez
// Usuario del Juez DG24


#include <iostream>
#include <iomanip>
#include <fstream>
#include<utility>

#include "PriorityQueue.h"

struct comparator {
	bool operator() (std::pair<int,int> const & p1, std::pair<int,int> const & p2) {
		return p1.second<p2.second || (p1.second==p2.second && p1.first<p2.first);
	}
};

// función que resuelve el problema


// Resuelve un caso de prueba, leyendo de la entrada la
// configuración, y escribiendo la respuesta
bool resuelveCaso() {
	// leer los datos de la entrada
	int numCajas, numClientes, tiempo;
	std::cin >> numCajas >> numClientes;
	if (numCajas==0 && numClientes ==0)
		return false;
	std::pair<int, int> info;
	PriorityQueue <std::pair<int, int>, comparator> monticulo;
	for (int i = 0; i < numCajas; ++i)
		monticulo.push({ i + 1,0 });
	for (int i = 0; i < numClientes; ++i) {
		std::cin >> tiempo;
		info = monticulo.top();
		info.second += tiempo;
		monticulo.pop();
		monticulo.push(info);
	}
	info = monticulo.top();
	// escribir sol
	std::cout << info.first << '\n';
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
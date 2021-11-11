// Nombre del alumno Alejandro Ramírez
// Usuario del Juez DG24


#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
#include<utility>
#include "PriorityQueue.h"

struct comparator {
	bool operator() (std::pair<std::string, std::pair<long int, int>> const & p1, std::pair<std::string, std::pair<long int, int>> const & p2) {
		return p1.second.first > p2.second.first || (p1.second.first == p2.second.first && p1.second.second < p2.second.second);
	}
};

// función que resuelve el problema


// Resuelve un caso de prueba, leyendo de la entrada la
// configuración, y escribiendo la respuesta
bool resuelveCaso() {
	// leer los datos de la entrada
	int n;
	long int gravedad;
	char opc;
	std::string nombre;
	std::cin >> n;
	if (n == 0)
		return false;
	std::pair<std::string, std::pair<long int, int>> info_enfermo;
	PriorityQueue <std::pair<std::string, std::pair<long int, int>>,comparator> monticulo;
	for (int i = 0; i < n; ++i) {
		std::cin >>opc;
		switch (opc) {
		case 'I':
			std::cin >> nombre >> gravedad;
			info_enfermo = { nombre,{gravedad,i} };
			monticulo.push(info_enfermo);
			break;
		case 'A':
			info_enfermo = monticulo.top();
			std::cout << info_enfermo.first << '\n';
			monticulo.pop();
			break;
		}
	}
	std::cout << "---\n";
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